package com.example.arplacementappforandroid

import android.Manifest
import android.content.pm.PackageManager
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.ar.core.*
import com.google.ar.core.exceptions.*

class ARActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1001
    }

    private lateinit var surfaceView: GLSurfaceView
    private lateinit var statusText: TextView
    private lateinit var instructionsText: TextView
    private var arSession: Session? = null
    private var installRequested = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "AR View"
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        initializeViews()

        if (!hasCameraPermission()) {
            requestCameraPermission()
        }
    }

    private fun initializeViews() {
        surfaceView = findViewById(R.id.arSurfaceView)
        statusText = findViewById(R.id.arStatusText)
        instructionsText = findViewById(R.id.arInstructions)
        surfaceView.visibility = View.GONE
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupARCore()
            } else {
                showError("Camera permission is required for AR functionality")
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (hasCameraPermission()) {
            setupARCore()
        }

        arSession?.let { session ->
            try {
                session.resume()
                surfaceView.onResume()
            } catch (e: CameraNotAvailableException) {
                e.printStackTrace()
                showError("Camera not available: ${e.message}")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        arSession?.let {
            surfaceView.onPause()
            it.pause()
        }
    }

    override fun onDestroy() {
        arSession?.close()
        super.onDestroy()
    }

    private fun setupARCore() {
        if (arSession != null) return

        var message: String? = null

        try {
            when (ArCoreApk.getInstance().requestInstall(this, !installRequested)) {
                ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                    installRequested = true
                    return
                }
                ArCoreApk.InstallStatus.INSTALLED -> {
                    // Continue setup
                }
            }

            arSession = Session(this).apply {
                val config = Config(this)
                config.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
                configure(config)
            }

            showSuccess("ARCore initialized successfully!")
            setupRenderer()

        } catch (e: Exception) {
            e.printStackTrace()
            message = when (e) {
                is UnavailableArcoreNotInstalledException -> "Please install ARCore"
                is UnavailableUserDeclinedInstallationException -> "User declined ARCore install"
                is UnavailableApkTooOldException -> "Please update ARCore"
                is UnavailableSdkTooOldException -> "Please update this app"
                is UnavailableDeviceNotCompatibleException -> "This device does not support AR"
                else -> "Failed to create AR session"
            }
            showError(message)
        }
    }

    private fun setupRenderer() {
        surfaceView.visibility = View.VISIBLE
        statusText.visibility = View.GONE
        instructionsText.visibility = View.VISIBLE
        instructionsText.text = "AR is ready! Move around to detect surfaces."
    }

    private fun showError(message: String) {
        statusText.text = "Error: $message"
        statusText.visibility = View.VISIBLE
        instructionsText.visibility = View.GONE
        surfaceView.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showSuccess(message: String) {
        statusText.text = message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
