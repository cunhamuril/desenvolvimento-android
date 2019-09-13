package com.exercicio.lista01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {
    private RadioGroup rdgPergunta01, rdgPergunta02, rdgPergunta03;
    private RadioButton rdb11, rdb23, rdb33;
    private Button btnResult;
    private TextView txtResult;
    private Integer numAcertos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        loadWidgets();
        result();
    }

    public void loadWidgets() {
        rdgPergunta01 = findViewById(R.id.rdgPergunta01);
        rdgPergunta02 = findViewById(R.id.rdgPergunta02);
        rdgPergunta03 = findViewById(R.id.rdgPergunta03);
        rdb11 = findViewById(R.id.rdb11);
        rdb23 = findViewById(R.id.rdb23);
        rdb33 = findViewById(R.id.rdb33);
        btnResult = findViewById(R.id.btnResult);
        txtResult = findViewById(R.id.txtResult);
    }

    public void countHits(){
        rdgPergunta01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rdb11.isChecked()) numAcertos++;
            }
        });
        rdgPergunta02.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rdb23.isChecked()) numAcertos++;
            }
        });
        rdgPergunta03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rdb33.isChecked()) numAcertos++;
            }
        });
    }

    public void result() {
        countHits();

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.setText("Acertou " + numAcertos);
                rdb11.setTextColor(getResources().getColor(R.color.colorHit));
                rdb11.setTypeface(Typeface.DEFAULT_BOLD);
                rdb23.setTextColor(getResources().getColor(R.color.colorHit));
                rdb23.setTypeface(Typeface.DEFAULT_BOLD);
                rdb33.setTextColor(getResources().getColor(R.color.colorHit));
                rdb33.setTypeface(Typeface.DEFAULT_BOLD);
            }
        });
    }
}
