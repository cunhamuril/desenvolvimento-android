package com.exercicio.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Spinner spnOperacao;
    public EditText edtN1, edtN2;
    public TextView txtResultado;
    public Button btnCalcular, btnLimpar;
    public Double n1, n2, res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadWidgets();
        calculate();
        clean();
    }

    public void loadWidgets() {
        spnOperacao = (Spinner)findViewById(R.id.spnOperacao);
        edtN1 = (EditText)findViewById(R.id.edtN1);
        edtN2 = (EditText)findViewById(R.id.edtN2);
        txtResultado = (TextView)findViewById(R.id.txtResultado);
        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        btnLimpar = (Button)findViewById(R.id.btnLimpar);
    }

    public void calculate() {
            spnOperacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(!edtN1.getText().toString().equals("") || !edtN2.getText().toString().equals("")) {
                        btnCalcular.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                n1 = Double.valueOf(edtN1.getText().toString());
                                n2 = Double.valueOf(edtN2.getText().toString());

                                Integer option = (int) spnOperacao.getSelectedItemId();

                                switch (option) {
                                    case 0:
                                        txtResultado.setText("0");
                                        Toast.makeText(getApplicationContext(),
                                                "Selecione uma operação!",
                                                Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        res = n1 + n2;
                                        break;
                                    case 2:
                                        res = n1 - n2;
                                        break;
                                    case 3:
                                        res = n1 * n2;
                                        break;
                                    case 4:
                                        res = n1 / n2;
                                        break;
                                    default:
                                }
                                txtResultado.setText(res.toString());
                            }
                        });
                    } else {
                        btnCalcular.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(),
                                        "Preencha os campos",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
    }

    public void clean() {
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtN1.setText("");
                edtN2.setText("");
                txtResultado.setText("0");
            }
        });
    }
}
