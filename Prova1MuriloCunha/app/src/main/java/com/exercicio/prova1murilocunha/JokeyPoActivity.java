package com.exercicio.prova1murilocunha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class JokeyPoActivity extends AppCompatActivity {
    Random numeroAleatorio = new Random();
    Integer contadorVitoriaPc = 0;
    Integer contadorVitoriaVc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokey_po);

        jogo();
    }

    private void jogo() {
        Button btnPedra = findViewById(R.id.btnPedra);
        Button btnPapel = findViewById(R.id.btnPapel);
        Button btnTesoura = findViewById(R.id.btnTesoura);

        btnPedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogadaPc(1);
            }
        });

        btnPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogadaPc(2);
            }
        });

        btnTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogadaPc(3);
            }
        });
    }

    private void jogadaPc(int jogadaVc) {
        Integer jogadaDoPc = numeroAleatorio.nextInt(3) + 1;

        TextView txtResPc = findViewById(R.id.txtResPc);
        TextView txtScorePc = findViewById(R.id.txtScorePc);

        TextView txtResVc = findViewById(R.id.txtResVc);
        TextView txtScoreVc = findViewById(R.id.txtScoreVc);

        TextView txtResJogada = findViewById(R.id.txtResJogada);

        // empate
        if (jogadaDoPc == 1 && jogadaVc == 1) {
            contadorVitoriaPc++;
            contadorVitoriaVc++;

            txtResPc.setText("Pedra");
            txtResVc.setText("Pedra");
            txtResJogada.setText("Empate!");
        }

        else if (jogadaDoPc == 2 && jogadaVc == 2) {
            contadorVitoriaPc++;
            contadorVitoriaVc++;

            txtResPc.setText("Papel");
            txtResVc.setText("Papel");
            txtResJogada.setText("Empate!");
        }

        else if (jogadaDoPc == 3 && jogadaVc == 3) {
            contadorVitoriaPc++;
            contadorVitoriaVc++;

            txtResPc.setText("Tesoura");
            txtResVc.setText("Tesoura");
            txtResJogada.setText("Empate!");
        }

        // você ganha
        else if(jogadaDoPc == 1 && jogadaVc == 2) {
            contadorVitoriaVc++;

            txtResPc.setText("Pedra");
            txtResVc.setText("Papel");
            txtResJogada.setText("Você ganhou!");
        }

        else if(jogadaDoPc == 2 && jogadaVc == 3) {
            contadorVitoriaVc++;

            txtResPc.setText("Papel");
            txtResVc.setText("Tesoura");
            txtResJogada.setText("Você ganhou!");
        }

        else if(jogadaDoPc == 3 && jogadaVc == 1) {
            contadorVitoriaVc++;

            txtResPc.setText("Tesoura");
            txtResVc.setText("Pedra");
            txtResJogada.setText("Você ganhou!");
        }

        // pc ganha
        else if (jogadaDoPc == 2 && jogadaVc == 1) {
            contadorVitoriaPc++;

            txtResPc.setText("Papel");
            txtResVc.setText("Pedra");
            txtResJogada.setText("Você perdeu!");
        }
        else if (jogadaDoPc == 3 && jogadaVc == 2) {
            contadorVitoriaPc++;

            txtResPc.setText("Tesoura");
            txtResVc.setText("Papel");
            txtResJogada.setText("Você perdeu!");
        }
        else if (jogadaDoPc == 1 && jogadaVc == 3) {
            contadorVitoriaPc++;

            txtResPc.setText("Pedra");
            txtResVc.setText("Tesoura");
            txtResJogada.setText("Você perdeu!");
        }

        txtScorePc.setText("Computador: " + contadorVitoriaPc.toString());
        txtScoreVc.setText("Você: " + contadorVitoriaVc.toString());
    }
}
