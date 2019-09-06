package com.exercicio.exactionbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalculadoraActivity extends AppCompatActivity {
    Button btnMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        loadWidgets();
        showMsg();
    }

    public void loadWidgets() {
        btnMsg = findViewById(R.id.btnMsg);
    }

    public void showMsg() {
        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensagemSimples("opa", "iaí");
            }
        });
    }

    public void mensagemSimples(String titulo, String texto){
        AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
        mensagem.setMessage(texto);
        mensagem.setTitle(titulo);
        mensagem.setNeutralButton("OK", null);
        mensagem.show();
    }
}
