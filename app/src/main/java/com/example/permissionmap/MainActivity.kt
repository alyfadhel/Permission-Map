package com.example.permissionmap

import android.Manifest
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : BaseActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (isPermissionGranted()){
            showUserLocation()
        }else{
            requestedPermissionGranted()
        }
    }
    val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    showUserLocation()
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(this,"Access Denied",Toast.LENGTH_LONG).show()
                }
            }

    private fun requestedPermissionGranted() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                showDialoge(message = "App needs to granted permission to find nearest driver",
                posActionName = "OK",posAction = DialogInterface.OnClickListener { dialog, which ->
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    dialog.dismiss()
                },negActionName = "NO",negAction = DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        }else{
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    private fun showUserLocation() {
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    if (location!=null){
                        Log.e("lat",""+location.latitude)
                        Log.e("long",""+location.longitude)
                    }
                }
        Toast.makeText(this,"Show User Location",Toast.LENGTH_LONG).show()
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
}