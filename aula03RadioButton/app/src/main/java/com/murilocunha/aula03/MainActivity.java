package com.murilocunha.aula03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioButton rd01, rd02, rd03;
    private RadioGroup rdPergunta01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdPergunta01=(RadioGroup)findViewById(R.id.rdgPergunta01);
        rd01=(RadioButton)findViewById(R.id.rdb01);
        rd02=(RadioButton)findViewById(R.id.rdb02);
        rd03=(RadioButton)findViewById(R.id.rdb03);

        rdPergunta01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(rd01.isChecked()){
                    Toast.makeText(getApplicationContext(), "Sim", Toast.LENGTH_SHORT).show();
                }if(rd02.isChecked()){
                    Toast.makeText(getApplicationContext(), "Sim", Toast.LENGTH_SHORT).show();
                }if(rd03.isChecked()){
                    Toast.makeText(getApplicationContext(), "Não", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
