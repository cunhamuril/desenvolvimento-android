package com.exercicio.lista01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConverteActivity extends AppCompatActivity {
    private EditText edtValue;
    private Spinner spnType;
    private Button btnCalc;
    private TextView txtResult;
    private Double value, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converte);
        loadWidgets();
        convert();
    }

    public void loadWidgets() {
        edtValue = findViewById(R.id.edtValue);
        spnType = findViewById(R.id.spnType);
        btnCalc = findViewById(R.id.btnCalc);
        txtResult = findViewById(R.id.txtResult);
    }

    public void convert() {
        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                btnCalc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        value = Double.valueOf(edtValue.getText().toString());

                        Integer option = (int) spnType.getSelectedItemId();

                        switch (option) {
                            case 0:
                                Toast.makeText(getApplicationContext(),
                                        "Selecione um tipo",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                result = value * 1000;
                                break;
                            case 2:
                                result = value * 1.09361;
                                break;
                            case 3:
                                result = value * 0.0001;
                                break;
                            case 4:
                                result = value * 0.000247105;
                                break;
                        }

                        txtResult.setText(String.format("%.3f", result));
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}
