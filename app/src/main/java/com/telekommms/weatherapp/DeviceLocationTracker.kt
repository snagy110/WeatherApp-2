package com.telekommms.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.provider.Settings
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.IOException
import java.lang.Exception
import java.lang.ref.WeakReference
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*
import kotlin.coroutines.CoroutineContext

/**
 * Use GPS or Network Provider to get device Location
 */
@Suppress("DEPRECATION")
class DeviceLocationTracker(
    private val context: Context,
    deviceLocationListener: DeviceLocationListener
) : LocationListener, CoroutineScope {
    private var deviceLocation: android.location.Location? = null
    private val weakContext: WeakReference<Context> = WeakReference(context)
    private var locationManager: LocationManager? = null
    private var deviceLocationListener: DeviceLocationListener
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    var currentAddressLine: String = ""
    private val internetMutex = Mutex()

    init {
        this.deviceLocationListener = deviceLocationListener
        initializeLocationProviders()
    }

    @SuppressLint("MissingPermission")
    fun initializeLocationProviders(): android.location.Location? {
        //Init Location Manager if not already initialized
        var mLocation: android.location.Location? = null
        if (null == locationManager) {
            locationManager = weakContext.get()
                ?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
        locationManager?.apply {
            // flag for GPS status
            val isGPSEnabled = isProviderEnabled(LocationManager.GPS_PROVIDER)
            // flag for network status
            val isNetworkEnabled = isProviderEnabled(LocationManager.PASSIVE_PROVIDER)
            //If we have permission
            if (context.checkSelfPermission(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED &&
                context.checkSelfPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                //First Try GPS
                if (isGPSEnabled) {
                    try {
                        requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            UPDATE_FREQUENCY_TIME,
                            UPDATE_FREQUENCY_DISTANCE.toFloat(),
                            this@DeviceLocationTracker
                        )
                        mLocation = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    } catch (exception: Exception) {
                        println(exception.printStackTrace())
                    }
                } else {
                    // Show alert to open GPS
                    weakContext.get()?.apply {
                        AlertDialog.Builder(this)
                            .setTitle("Enable GPS")
                            .setMessage("GPS is not enabled. Do you want to go to settings menu?")
                            .setPositiveButton(
                                "Open Settings"
                            ) { dialog, which ->
                                val intent = Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS
                                )
                                startActivity(intent)
                            }.setNegativeButton("Cancel")
                            { dialog, which -> dialog.cancel() }.show()
                    }
                    println("initializeLocationProviders: GPS is not enabled")
                }
                //If failed try using NetworkManger
                if (null == deviceLocation && isNetworkEnabled) {
                    try {
                        requestLocationUpdates(
                            LocationManager.PASSIVE_PROVIDER,
                            0,
                            0f,
                            this@DeviceLocationTracker
                        )
                        mLocation = locationManager!!
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    } catch (exception: Exception) {
                        println(exception.printStackTrace())
                    }
                }
            } else {
                println("initializeLocationProviders: Location Permission not granted")
            }
        }
        return mLocation
    }

    private suspend fun isInternetConnection() = flow {
        internetMutex.withLock {
            try {
                val ipAddress: InetAddress =
                    InetAddress.getByName("google.com")
                emit(!ipAddress.equals(""))
            } catch (e: UnknownHostException) {
                emit(false)
            }
        }
    }

    /**
     * Stop using GPS listener
     * Call this function to stop using GPS
     */
    fun stopUpdate() {
        if (locationManager != null) {
            locationManager!!.removeUpdates(this@DeviceLocationTracker)
        }
    }

    override fun onLocationChanged(newDeviceLocation: android.location.Location) {
//        if (tempShowCurrentLocation.value) {
        deviceLocation = newDeviceLocation
        launch {
//            logger.info(tag, "onLocationChanged safeLaunch")
            if (deviceLocation == null) {
                withContext(Dispatchers.Main) {
                    initializeLocationProviders()
                }
            }
            withContext(Dispatchers.IO) {
                val addressList: List<Address?>?
                try {
                    if (isInternetConnection().first()) {
                        addressList = weakContext.get()?.let {
                            Geocoder(
                                it,
                                Locale.ENGLISH
                            ).getFromLocation(
                                newDeviceLocation.latitude,
                                newDeviceLocation.longitude,
                                1
                            )
                        }
                        val currentLocation = addressList?.get(0)
                        currentLocation?.apply {
                            for (i in 0..this.maxAddressLineIndex) {
                                currentAddressLine += this.getAddressLine(i)
                            }
                        }
                        if (currentAddressLine == "") {
                            initializeLocationProviders()
                            println("addressList?.get(0) == null")
                        }
                        deviceLocationListener.onDeviceLocationChanged(addressList)
//                        logger.info(tag, "Fetch address list$addressList")
                    }
                } catch (e: IOException) {
                    println(
                        e.printStackTrace()
                    )
                }
            }
        }.apply {
            invokeOnCompletion {
                this.cancel()
            }
        }
    }

    override fun onProviderDisabled(provider: String) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    interface DeviceLocationListener {
        fun onDeviceLocationChanged(results: List<Address>?)
    }

    companion object {
        // The minimum distance to change Updates in meters
        private const val UPDATE_FREQUENCY_DISTANCE: Long = 1 // 1 = 1 meters

        // The minimum time between updates in milliseconds
        private const val UPDATE_FREQUENCY_TIME: Long = 0 // 1 = 1 minute

    }
}