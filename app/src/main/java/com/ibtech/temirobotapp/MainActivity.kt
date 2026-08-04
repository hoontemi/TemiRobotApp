package com.ibtech.temirobotapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ibtech.temirobotapp.navigation.AppNavHost
import com.ibtech.temirobotapp.ui.theme.TemiRobotAppTheme
import com.robotemi.sdk.Robot
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener
import com.robotemi.sdk.listeners.OnRobotReadyListener

/**
 * Temi SDK 연동 로직은 이번 단계에서 별도 Manager로 분리하지 않고 이 안에 그대로 유지한다.
 * 아직 Compose 화면(HomeScreen)과는 연결되어 있지 않으며, 다음 단계에서
 * TemiRobotManager로 이전해 화면과 StateFlow로 연결할 예정이다.
 */
class MainActivity : ComponentActivity(),
    OnRobotReadyListener,
    OnGoToLocationStatusChangedListener {

    private lateinit var robot: Robot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        robot = Robot.getInstance()

        setContent {
            TemiRobotAppTheme {
                AppNavHost()
            }
        }
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

        Log.d(TAG, if (isReady) "Temi 준비 완료" else "Temi 연결되지 않음")

        // 아직 화면과 연결되지 않았지만, 준비되면 POI 조회는 그대로 수행한다.
        if (isReady) loadAllPois()
    }

    /** temi 지도에 저장된 모든 POI(위치) 이름을 읽어온다. 다음 단계에서 TemiRobotManager로 이전 예정. */
    private fun loadAllPois() {
        val pois = robot.locations

        if (pois.isEmpty()) {
            Toast.makeText(this, "지도에 저장된 POI가 없습니다", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d(TAG, "POI ${pois.size}개를 읽었습니다")
    }

    /** 목적지로 이동을 요청한다. 다음 단계에서 TemiRobotManager로 이전 예정. */
    private fun goToPoi(name: String) {
        robot.goTo(name)
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
                Log.d(TAG, "$location(으)로 이동 중…")

            OnGoToLocationStatusChangedListener.COMPLETE ->
                Log.d(TAG, "$location 에 도착했습니다")

            OnGoToLocationStatusChangedListener.ABORT ->
                Log.d(TAG, "이동이 중단되었습니다")
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
