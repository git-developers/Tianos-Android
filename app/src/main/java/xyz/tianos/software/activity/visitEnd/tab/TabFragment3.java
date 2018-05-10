package xyz.tianos.software.activity.visitEnd.tab;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import xyz.tianos.software.activity.R;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.utils.Const;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class TabFragment3 extends Fragment implements OnMapReadyCallback {

    private static final String TAG = TabFragment1.class.getSimpleName();
    private MapView mapView;
    private GoogleMap googleMap;
    private PointOfSale pointOfSale;
    private double latitude;
    private double longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.start_visit_tab_3, container, false);

        pointOfSale = (PointOfSale) getArguments().getSerializable(Const.DATA_POINT_OF_SALE);

        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume(); // needed to get the map to display immediately
        mapView.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;

        this.latitude = pointOfSale.getLatitude();
        this.longitude = pointOfSale.getLongitude();
        String namePdv = pointOfSale.getName();

        // Add a marker and move the camera.
        LatLng sydney = new LatLng(this.latitude, this.longitude);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("PDV:: " + namePdv));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private boolean checkPermission() {

        if (ContextCompat.checkSelfPermission(getActivity(), ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}