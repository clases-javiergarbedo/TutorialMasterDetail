package es.javiergarbedo.tutorialmasterdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapFragment extends SupportMapFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        // El servicio de Google Maps v2 requiere Google Play Services instalado en el dispositivo.
        // Si no se tiene instalado, se mostrará un mensaje de aviso y un botón para la descarga
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (isGooglePlayServicesAvailable == ConnectionResult.SUCCESS) {
            // Habilitar el botón de Mi localización en el mapa
            getMap().setMyLocationEnabled(true);

            // Posicionar el mapa en una localización y con un nivel de zoom
            LatLng latLng = new LatLng(36.679582, -5.444791);
            // Un zoom mayor que 13 hace que el emulador falle, pero un valor deseado para
            // callejero es 17 aprox.
            float zoom = 13;
            getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

            // Colocar un marcador en la misma posición
            getMap().addMarker(new MarkerOptions().position(latLng));
            // Más opciones para el marcador en:
            // https://developers.google.com/maps/documentation/android/marker

            // Otras configuraciones pueden realizarse a través de UiSettings
            // UiSettings settings = getMap().getUiSettings();
        }

        return rootView;
    }


}
