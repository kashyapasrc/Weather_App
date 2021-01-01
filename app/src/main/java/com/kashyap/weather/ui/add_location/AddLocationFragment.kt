package com.kashyap.weather.ui.add_location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kashyap.weather.R
import com.kashyap.weather.domain.databases.DatabaseBuilder
import com.kashyap.weather.domain.databases.DatabaseHelperImpl
import com.kashyap.weather.domain.databases.entity.BookMarks
import com.kashyap.weather.ui.MainActivity
import com.kashyap.weather.ui.base.BaseFragment
import com.kashyap.weather.utils.DisplaySnackMessage
import com.kashyap.weather.utils.ISnackBarAction
import com.kashyap.weather.utils.initViewModel
import com.mindorks.example.coroutines.utils.Status
import java.util.*

class AddLocationFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var rootView: View
    private var locationPermissionGranted = false
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var lastKnownLocation: Location? = null
    private val defaultLocation = LatLng(-33.8523341, 151.2106085)
    private lateinit var viewModel: AddLocationViewModel
    private var mLastClickTime : Long = 0

    companion object {
        private val TAG = AddLocationFragment::class.java.simpleName
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1

        fun getInstance(): AddLocationFragment {
            val fragment = AddLocationFragment();
            return fragment;
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_add_loaction, container, false)
        initViews()
        return rootView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModels()
        setupObserver()
    }

    private fun setupViewModels() {
        viewModel = initViewModel()
    }


    private fun setupObserver() {
        viewModel.getRecordStatus().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    navigateToHomeFragment()
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    //Handle Error
                    dispalyErrorMsg(it.message)
                }
            }

        })
    }

    private fun navigateToHomeFragment() {
        if (activity is MainActivity) {
            (activity as MainActivity).setHomeFragment();
        }
    }

    private fun initViews() {
        fusedLocationProviderClient = activity?.let {
            LocationServices.getFusedLocationProviderClient(
                it
            )
        }!!

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            mMap = googleMap

            mMap.getUiSettings().setZoomControlsEnabled(true)
            setMapClickListener(mMap)
            getLocationPermission()
        }
    }

    private fun getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (this.context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
            getDeviceLocation()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        locationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    locationPermissionGranted = true
                }
            }
        }
        updateLocationUI()

        getDeviceLocation()
    }

    // [START maps_current_place_get_device_location]
    private fun getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {

            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                activity?.let {
                    locationResult.addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.result
                            if (lastKnownLocation != null) {
                                mMap.moveCamera(
                                    CameraUpdateFactory.newLatLngZoom(
                                        LatLng(
                                            lastKnownLocation!!.latitude,
                                            lastKnownLocation!!.longitude
                                        ), DEFAULT_ZOOM.toFloat()
                                    )
                                )
                            }
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.")
                            Log.e(TAG, "Exception: %s", task.exception)

                            mMap.moveCamera(
                                CameraUpdateFactory
                                    .newLatLngZoom(
                                        defaultLocation, DEFAULT_ZOOM.toFloat()
                                    )
                            )
                            mMap.uiSettings?.isMyLocationButtonEnabled = false
                        }
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    private fun updateLocationUI() {
        try {
            if (locationPermissionGranted) {
                mMap.isMyLocationEnabled = true
                mMap.uiSettings?.isMyLocationButtonEnabled = true
            } else {
                mMap.isMyLocationEnabled = false
                mMap.uiSettings?.isMyLocationButtonEnabled = false
                lastKnownLocation = null
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }


    private fun setMapClickListener(mMap: GoogleMap) {
        mMap.setOnMapClickListener { latLng ->


            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return@setOnMapClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime();


// A Snippet is Additional text that's displayed below the title.
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(getString(R.string.dropped_pin))
                    .snippet(snippet)

            )

            DisplaySnackMessage.dipslaySnackBarWithButton(
                rootView,
                getString(R.string.create_bookmark),
                getString(R.string.ok),
                object : ISnackBarAction {
                    override fun onSnackButtonClicked() {
                        insertIntoDatabase(latLng)
                    }

                }
            )
        }
    }


    private fun insertIntoDatabase(latLng: LatLng) {
        try {
            Log.d(TAG, "" + latLng.toString())

            val geocoder: Geocoder
            val addresses: List<Address>
            geocoder = Geocoder(context, Locale.getDefault())

            addresses = geocoder.getFromLocation(
                latLng.latitude,
                latLng.longitude,
                1
            ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            if (addresses == null) {
                dispalyErrorMsg(getString(R.string.unable_to_fetch_address));
            } else {
                val address: String =
                    addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                val city: String = addresses[0].getLocality()
                val state: String = addresses[0].getAdminArea()
                val country: String = addresses[0].getCountryName()
                val postalCode: String = addresses[0].getPostalCode()
                val knownName: String =
                    addresses[0].getFeatureName() // Only if available else return NULL


                val bookMarks =
                    BookMarks(city, true, latitude = latLng.latitude, longitude = latLng.longitude);
                viewModel.insertBookMarkRecord(bookMarks)
            }
        } catch (e: Exception) {
            e.printStackTrace()

        }

    }


}
