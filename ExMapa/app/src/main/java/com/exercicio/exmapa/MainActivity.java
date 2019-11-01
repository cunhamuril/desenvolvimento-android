package com.exercicio.exmapa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public GoogleMap mapa;
    private Location mLastKnownLocation; // guarda a última localização
    public LatLng localizacao = new LatLng(-23.951137, -46.339025); // pega uma localização inicial
    private Button btMinhaPosicao;
    private GeoDataClient mGeoDataClient; // objeto que irá iniciar o client da geolocalizacao
    private FusedLocationProviderClient mFusedLocationProviderClient; // objeto que guardará o client da trangulação da geolocalização
    private boolean permissaoConcedida; // booleano se permitiu ou não a geolocalização

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nossoMapa);
        // Atribui o mapa criado no layout do tipo "MapFragment"
        mapFragment.getMapAsync(MainActivity.this);
        // Habilita o cliente para pegar sua localização.
        mGeoDataClient = Places.getGeoDataClient(MainActivity.this, null);
        // Habilita o client para pegar os serviços da triangulação de antenas e gps no client
       mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        //metodoBotao();
    }

    //metodo para verificar se o mapa foi carregado no fragment
    private void updateLocationUI() {
        // caso o mapa não for encontrado sair
        if (mapa == null) {
            return;
        }

        try {
            if (permissaoConcedida) {
                getDeviceLocation(); // pega o dispositivo da localização
                mapa.setMyLocationEnabled(true); // seta o "ponto" da minha localização
                mapa.getUiSettings().setMyLocationButtonEnabled(true);
                // habilita o botão para setar minha localização
            } else {
                mapa.setMyLocationEnabled(false);
                mapa.getUiSettings().setMyLocationButtonEnabled(false);
                localizacao = null;
                enableMyLocation();
                // busca no dispositivo a ultima localizacao
                getDeviceLocation();
            }
        } catch (SecurityException e) {
            Log.e("Ocorreu um erro: %s", e.getMessage());
        }
    }

    private void getDeviceLocation() {
        try { // se a permissao de acesso foi concedido então
            if (permissaoConcedida) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                // pega a última localização

            }
        }
    }

    private void enableMyLocation() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Toast.makeText(this, "Passou pelas permissões", Toast.LENGTH_LONG).show();

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // case as permissões forem concedidas então seta a variável
                    permissaoConcedida = true;
                } else {
                    Toast.makeText(this, "Conceda as permissões para abrir a sua geolocalização",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void metodoBotao() {
        btMinhaPosicao = findViewById(R.id.btnPosicao);
        btMinhaPosicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableMyLocation();
                // ativa minha localizacao;
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mapa = googleMap;
        // atribui o mapa recebido ao objeto mapa de sua aplicação
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // tipo de mapa
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(localizacao, 18);
        // ajusta a altura da visualização do mapa conforme uma geolocalização informada
        mapa.animateCamera(update);
        // atribui um efeito na mudança da camera
        mapa.addMarker(new MarkerOptions().position(localizacao).title("Santos Futebol Clube"));
        // adiciona um marcador conforme a geolocalização apontada com um texto fixo
    }
}
