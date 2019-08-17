package com.example.aula01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button btSomar, btSubtrair, btMultiplicar, btDividir;
    public EditText edNum01, edNum02, edResultado;
    public Integer n1, n2, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregaWidgets();
        botoes();
    }

    public void carregaWidgets() {
        edNum01 = (EditText)findViewById(R.id.edNum01);
        edNum02 = (EditText)findViewById(R.id.edNum02);
        edResultado = (EditText)findViewById(R.id.edResultado);

        btSomar = (Button)findViewById(R.id.btnSomar);
        btSubtrair = (Button)findViewById(R.id.btnSubtrair);
        btMultiplicar = (Button)findViewById(R.id.btnMultiplicar);
        btDividir = (Button)findViewById(R.id.btnDividir);
    }

    public void botoes() {
        btSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = Integer.valueOf(edNum01.getText().toString());
                n2 = Integer.valueOf(edNum02.getText().toString());

                total = n1 + n2;

                edResultado.setText(total.toString());
            }
        });

        btSubtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = Integer.valueOf(edNum01.getText().toString());
                n2 = Integer.valueOf(edNum02.getText().toString());

                total = n1 - n2;

                edResultado.setText(total.toString());
            }
        });

        btMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = Integer.valueOf(edNum01.getText().toString());
                n2 = Integer.valueOf(edNum02.getText().toString());

                total = n1 * n2;

                edResultado.setText(total.toString());
            }
        });

        btDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = Integer.valueOf(edNum01.getText().toString());
                n2 = Integer.valueOf(edNum02.getText().toString());

                total = n1 / n2;

                edResultado.setText(total.toString());
            }
        });
    }
}
