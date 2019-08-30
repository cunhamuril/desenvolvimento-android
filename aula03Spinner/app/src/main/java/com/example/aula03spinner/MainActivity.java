package com.example.aula03spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Spinner spnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCalc = (Spinner)findViewById(R.id.spnExemplo);
        spnCalc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Integer opcao = (int) spnCalc.getSelectedItemId();

                if(opcao == 0) {
                    Toast.makeText(getApplicationContext(), "opcao 1", Toast.LENGTH_SHORT).show();
                }
                if(opcao == 1) {
                    Toast.makeText(getApplicationContext(), "opcao 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
