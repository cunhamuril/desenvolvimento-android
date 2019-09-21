package com.exercicio.swipecamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MainActivity extends SwipeAc {
    String[] permissoes = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Boolean permissaook = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtils.validate(MainActivity.this, 0, permissoes);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(getApplicationContext(), "Permissão Negada!",
                        Toast.LENGTH_LONG).show();
                return;
            } else {
                permissaook = true;
                Toast.makeText(getApplicationContext(), "Permissão Concedida!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onSwipeRight() {
        Intent telaDireita = new Intent(MainActivity.this, TelaEsquerdaActivity.class);
        finish(); //encerra a activity atual
        startActivity(telaDireita);
    }

    @Override
    protected void onSwipeLeft() {
        Intent telaDireita = new Intent(MainActivity.this, TelaDireitaActivity.class);
        finish(); //encerra a activity atual
        startActivity(telaDireita);
    }
}
