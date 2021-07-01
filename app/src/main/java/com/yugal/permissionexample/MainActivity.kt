package com.yugal.permissionexample

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCameraPermission.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this@MainActivity, "You already have access",
                    Toast.LENGTH_LONG).show()
            }else{
                // Request Permission
                ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE)
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this@MainActivity,
                    "Permission granted for camera.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Oops you just denied the permission for camera. You can also allow it from settings.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        // We can give any number to permission code.
        private const val CAMERA_PERMISSION_CODE = 1
        private const val FINE_LOCATION_PERMISSION_CODE = 2
    }
}