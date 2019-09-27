package com.exercicio.prova1murilocunha;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        tiraFoto();
    }

    private void tiraFoto() {
        Button btnTiraFoto = findViewById(R.id.btnTiraFoto);

        btnTiraFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView img = findViewById(R.id.imagem);
        TextView txtDataHora = findViewById(R.id.txtDataHora);
        Bundle bundle = data.getExtras();

        if (data != null) {
            Bitmap bitmap = (Bitmap)bundle.get("data");
            img.setImageBitmap(bitmap);

            SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatHour = new SimpleDateFormat("HH:mm:ss");

            Date dataAtual = new Date();
            Date horaAtual = Calendar.getInstance().getTime();

            String dataFormatada = formatDate.format(dataAtual);
            String horaFormatada = formatHour.format(horaAtual);

            txtDataHora.setText("Data: " + dataFormatada + " Hora: " + horaFormatada);
        }
    }
}
