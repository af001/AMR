package technology.xor.amr.MapViews;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import technology.xor.amr.R;

public class MapView extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Do Nothing
            return;
        }
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        mMap.setMyLocationEnabled(true);

        // Add a marker in Sydney and move the camera
        SetMarkers();
    }

    public void SetMarkers() {

        List<Marker> markers = new ArrayList<Marker>();
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.88275,-77.0668759)).title("ALABASTER").snippet("Arlington Cemetery").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8892686,-77.050176)).title("AMBER").snippet("Lincoln Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8893913,-77.0425553)).title("AMETHYST").snippet("World War II Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8840102,-77.0440763)).title("AQUAMARINE").snippet("Franklin D. Roosevelt Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8796066,-77.0413983)).title("BERYL").snippet("George Mason Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.891133,-77.0450954)).title("CITRINE").snippet("56 Signers").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8951037,-77.0387466)).title("CORAL").snippet("Zero Milestone").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8813959,-77.0386509)).title("BLOODSTONE").snippet("Thomas Jefferson Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8897762,-77.015118)).title("DIAMOND").snippet("Ulysses S. Grant Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8912933,-77.0499072)).title("EMERALD").snippet("Vietnam Veterans Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8881601,-77.0220619)).title("GARNET").snippet("National Air and Space Museum").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8945661,-77.0247258)).title("GRANITE").snippet("U.S. Navy Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8912662,-77.0282594)).title("JADE").snippet("National Museum of Natural History").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8904365,-77.0719153)).title("JASPER").snippet("Iwo Jima Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8927958,-77.0252875)).title("OBSIDIAN").snippet("National Archives").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8879859,-77.0151417)).title("OPAL").snippet("Botanic Gardens").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8980972,-77.0104318)).title("PERIDOT").snippet("National Postal Museum").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8960835,-77.0409061)).title("QUARTZ").snippet("First Division Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8867076,-77.0348014)).title("ONYX").snippet("U.S. Holocaust Memorial Museum").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8878912,-77.049869)).title("SAPPHIRE").snippet("Korean War Veterans Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8966372,-77.0195973)).title("ZIRCON").snippet("National Law Enforcement Officers Memorial").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(38.8881683,-77.0281673)).title("SUNSTONE").snippet("Haupt Garden").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }

        LatLngBounds bounds = builder.build();

        int padding = 16; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);
    }
}

