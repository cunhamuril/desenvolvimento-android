package com.exercicio.swipecamera;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TelaDireitaActivity extends SwipeAc {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_direita);

        retiraFoto();
    }

    @Override
    protected void onSwipeLeft() {}

    @Override
    protected void onSwipeRight() {
        Intent telaEsquerda = new Intent(TelaDireitaActivity.this, MainActivity.class);
        finish(); //encerra a activity atual
        startActivity(telaEsquerda);
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
            Bitmap bitmap = (Bitmap) bundle.get("data"); // Pega o bitmap do bundle
            img.setImageBitmap(bitmap);
        }
    }
}
