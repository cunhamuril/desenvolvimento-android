package com.exercicio.lista01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MediaActivity extends AppCompatActivity {
    private EditText edtP1, edtProject, edtLists, edtPmu;
    private Button btnCalc;
    private TextView txtResult, txtSituation;
    private Double p1, project, lists, pmu, res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        loadWidgets();
        calculate();
    }

    public void loadWidgets() {
        edtP1 = findViewById(R.id.edtP1);
        edtProject = findViewById(R.id.edtProject);
        edtLists = findViewById(R.id.edtLists);
        edtPmu = findViewById(R.id.edtPmu);
        btnCalc = findViewById(R.id.btnCalc);
        txtResult = findViewById(R.id.txtResult);
        txtSituation = findViewById(R.id.txtSituation);
    }

    public void calculate() {
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1 = Double.valueOf(edtP1.getText().toString());
                project = Double.valueOf(edtProject.getText().toString());
                lists = Double.valueOf(edtLists.getText().toString());
                pmu = Double.valueOf(edtPmu.getText().toString());

                res = (p1 + project + lists + pmu) / 4;

                txtResult.setText(String.format("%.2f", res));

                if(res < 4) {
                    txtSituation.setText("Situação: Reprovado!");
                } else if (res >= 4 && res < 6) {
                    txtSituation.setText("Situação: Ficou para exame!");
                } else {
                    txtSituation.setText("Situação: Aprovado");
                }
            }
        });
    }
}
