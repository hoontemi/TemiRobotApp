package com.ibtech.temirobotapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.robotemi.sdk.Robot
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener
import com.robotemi.sdk.listeners.OnRobotReadyListener

class MainActivity : AppCompatActivity(),
    OnRobotReadyListener,
    OnGoToLocationStatusChangedListener {

    private lateinit var robot: Robot
    private lateinit var statusText: TextView
    private lateinit var loadPoiButton: Button
    private lateinit var poiRecyclerView: RecyclerView
    private lateinit var poiAdapter: PoiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        robot = Robot.getInstance()

        statusText = findViewById(R.id.statusText)
        loadPoiButton = findViewById(R.id.loadPoiButton)
        loadPoiButton.setOnClickListener { loadAllPois() }

        // POI 카드 그리드 구성: 넓은 화면은 3열, 좁은 화면은 2열.
        poiAdapter = PoiAdapter(onPoiClick = { name -> goToPoi(name) })
        poiRecyclerView = findViewById(R.id.poiRecyclerView)
        poiRecyclerView.layoutManager = GridLayoutManager(this, resolveSpanCount())
        poiRecyclerView.adapter = poiAdapter
        val spacing = resources.getDimensionPixelSize(R.dimen.poi_card_spacing)
        poiRecyclerView.addItemDecoration(GridSpacingItemDecoration(spacing))

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }
    }

    /** 화면 폭이 충분하면 3열, 좁으면 2열. */
    private fun resolveSpanCount(): Int {
        val widthDp = resources.configuration.screenWidthDp
        return if (widthDp >= 600) 3 else 2
    }

    override fun onStart() {
        super.onStart()
        robot.addOnRobotReadyListener(this)
        robot.addOnGoToLocationStatusChangedListener(this)
    }

    override fun onStop() {
        super.onStop()
        robot.removeOnGoToLocationStatusChangedListener(this)
        robot.removeOnRobotReadyListener(this)
    }

    override fun onRobotReady(isReady: Boolean) {
        if (isReady) {
            try {
                val activityInfo =
                    packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
                robot.onStart(activityInfo)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }

        loadPoiButton.isEnabled = isReady
        statusText.text = if (isReady) "준비 완료" else "로봇에 연결되지 않았습니다"

        // 준비되면 Figma처럼 곧바로 카드 그리드가 보이도록 자동으로 POI를 읽어온다.
        if (isReady) loadAllPois()
    }

    private fun loadAllPois() {
        // temi 지도에 저장된 모든 POI(위치) 이름을 읽어온다. (관리자 페이지 등록 구조 그대로 사용)
        val pois = robot.locations

        if (pois.isEmpty()) {
            statusText.text = "저장된 POI가 없습니다"
            poiAdapter.submit(emptyList())
            Toast.makeText(this, "지도에 저장된 POI가 없습니다", Toast.LENGTH_SHORT).show()
            return
        }

        statusText.text = "POI ${pois.size}개를 읽었습니다"
        poiAdapter.submit(pois)
    }

    private fun goToPoi(name: String) {
        setControlsEnabled(false)
        statusText.text = "$name(으)로 이동을 시작합니다…"
        robot.goTo(name)
    }

    // 이동 중에는 모든 컨트롤(POI 읽기 버튼 + 모든 POI 카드)을 비활성화한다.
    private fun setControlsEnabled(enabled: Boolean) {
        loadPoiButton.isEnabled = enabled
        poiAdapter.setEnabledState(enabled)
    }

    override fun onGoToLocationStatusChanged(
        location: String,
        status: String,
        descriptionId: Int,
        description: String
    ) {
        when (status) {
            OnGoToLocationStatusChangedListener.START,
            OnGoToLocationStatusChangedListener.CALCULATING,
            OnGoToLocationStatusChangedListener.GOING ->
                statusText.text = "$location(으)로 이동 중…"

            OnGoToLocationStatusChangedListener.COMPLETE -> {
                statusText.text = "$location 에 도착했습니다"
                setControlsEnabled(true)
            }

            OnGoToLocationStatusChangedListener.ABORT -> {
                statusText.text = "이동이 중단되었습니다"
                setControlsEnabled(true)
            }
        }
    }
}
