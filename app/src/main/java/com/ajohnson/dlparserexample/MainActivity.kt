package com.ajohnson.dlparserexample

import android.Manifest
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.SurfaceView
import android.view.View
import com.ajohnson.dlparserkotlin.models.License
import com.ajohnson.dlparserkotlin.parsers.DLParser
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector


class MainActivity : AppCompatActivity(), Detector.Processor<Barcode> {

    var preview: SurfaceView? = null
    var cameraSource: CameraSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Populate view references
        preview = findViewById(R.id.view_surface)

        // Request camera permissions and start scanner
        requestCameraPermissionsIfNeeded()
    }

    fun setupCameraSource() {
        val barcodeDetector = BarcodeDetector.Builder(applicationContext).build()
        barcodeDetector.setProcessor(this)
        cameraSource = CameraSource.Builder(applicationContext, barcodeDetector)
                .setAutoFocusEnabled(true)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1600, 1024)
                .setRequestedFps(30.0f).build()
    }

    fun requestCameraPermissionsIfNeeded() {
        val requestCode = 2
        val permissions = arrayOf(Manifest.permission.CAMERA)
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, requestCode)
            return
        }

        if (checkCallingPermission(Manifest.permission.CAMERA).equals(PermissionChecker.PERMISSION_GRANTED)) {
            activateCamera()
            return
        }

        val listener = { _: View ->
            ActivityCompat.requestPermissions(this, permissions, requestCode)
        }

        val preview = preview ?: return
        Snackbar.make(preview.rootView, R.string.permission_camera,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, listener)
                .show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        activateCamera()
    }

    fun activateCamera() {
        setupCameraSource()
        startCamera()
    }

    fun startCamera() {
        // check that the device has play services available.
        val code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext)
        if (code != ConnectionResult.SUCCESS) {
            val dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, code, 9001)
            dialog.show()
        }

        try {
            cameraSource?.start(preview!!.holder)
        } catch(e: SecurityException) {
            Log.e("DLParser", "Missing camera permissions")
        }
    }

    fun processBarcode(barcode: Barcode): License? {
        val license = DLParser(barcode.rawValue).parse()
        return if (license.isAcceptable) license else kotlin.run {
            requestCameraPermissionsIfNeeded()
            return null
        }
    }

    override fun release() {
        // TODO
    }

    override fun receiveDetections(detector: Detector.Detections<Barcode>?) {
        val barcodes = detector?.detectedItems ?: return
        for (i in 0 until barcodes.size()) {
            val barcode = barcodes.valueAt(i)
            val result = processBarcode(barcode) ?: continue
            print("Detected driver license: \n $result \n")
        }
    }
}
