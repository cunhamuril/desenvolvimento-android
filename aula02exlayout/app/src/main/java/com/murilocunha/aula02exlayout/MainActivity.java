package com.murilocunha.aula02exlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSomar, btnSubtrair, btnVoltarSomar, btnVoltarSubtrair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregaWdigets();
    }

    private void carregaWdigets() {
        btnSomar=(Button)findViewById(R.id.btnSomar);
        btnSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_soma);
                carregarWidgetsSomar();
            }
        });

        btnSubtrair=(Button)findViewById(R.id.btnSomar);
        btnSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_subtrair);
                carregarWidgetsSubtrair();
            }
        });
    }

    private void carregarWidgetsSomar() {
        btnVoltarSomar=(Button)findViewById(R.id.btnVoltarSomar);
        btnVoltarSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                carregaWdigets();
            }
        });
    }

    private void carregarWidgetsSubtrair() {
        btnVoltarSubtrair=(Button)findViewById(R.id.btnVoltarSomar);
        btnVoltarSubtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                carregaWdigets();
            }
        });
    }


}
