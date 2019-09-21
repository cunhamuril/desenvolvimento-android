package com.exercicio.excamera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] permissoes = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };
    Boolean permissaook = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtils.validate(MainActivity.this, 0, permissoes);

        // com o if tava dando erro
        retiraFoto();
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

    private void retiraFoto() {
        Button btTiraFoto = findViewById(R.id.btnTiraFoto);

        btTiraFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instancia a intent para abrir a camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ImageView img = findViewById(R.id.imagem);
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras(); // O tipo bundle permite que se transfira dados entre
        // a intent e a aplicação

        if (data != null) {
            Bitmap bitmap = (Bitmap)bundle.get("data"); // Pega o bitmap do bundle
            img.setImageBitmap(bitmap);
        }
    }
}
