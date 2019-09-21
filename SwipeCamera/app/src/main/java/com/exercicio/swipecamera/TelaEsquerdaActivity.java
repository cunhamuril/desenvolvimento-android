package com.exercicio.swipecamera;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class TelaEsquerdaActivity extends SwipeAc {
    EditText edtCodigo;
    Button btnLerCodigo;

    Intent intent = new Intent("com.google.zxing.client.android.SCAN");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_esquerda);

        loadWidgets();
        start();
    }

    @Override
    protected void onSwipeRight() {}

    @Override
    protected void onSwipeLeft() {
        Intent tela = new Intent(TelaEsquerdaActivity.this, MainActivity.class);
        finish();
        startActivity(tela);
    }

    public void loadWidgets() {
        edtCodigo = findViewById(R.id.edtCodigo);
        btnLerCodigo = findViewById(R.id.btnLerCodigo);
    }

    private void start() {
        btnLerCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            edtCodigo.setText(intent.getStringExtra("SCAN_RESULT"));
        }
    }
}
