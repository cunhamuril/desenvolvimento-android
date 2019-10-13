package com.exercicio.exemplopiao;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private AnimationDrawable anima; // Objeto do tipo animation
    private AnimationUtils an01, an02; // Objeto que tera as propriedades que utilizaremos para animar
    public MediaPlayer player; // Objeto do tipo player da música
    public SeekBar skb; // Barra para setarmos o tempo
    public int num = 0;
    private CountDownTimer timer; // Objeto do tipo timer
    private boolean iniciouDevagar = false; // Variável que diz ao timer que já foi setado a animação devagar
    private ImageView img;
    private Button btGira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGira = findViewById(R.id.btnGirar);
        skb = findViewById(R.id.skbTempo);
        img = findViewById(R.id.imagemPiao);
        player = MediaPlayer.create(MainActivity.this, R.raw.piaodobau);

        botao();
    }

    private void botao() {
        btGira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = skb.getProgress(); // Pega o tempo que foi colocado na SeekBar
                if (num > 30) {
                    giraRapido(); // chama o método gira rápido
                    tempo(num * 1000); // em milissegundos
                } else {
                    giraDevagar(); // chama o método gira devagar
                    tempo(num * 1000);
                }
            }
        });
    }

    private void tempo(int seg) {
        timer = new CountDownTimer(seg, 1000) {
            @Override
            public void onTick(long l) {
                num = skb.getProgress(); // pega o progresso atual e atribui na variável num
                num--; // decrementa um
                skb.setProgress(num); // seta o progresso atual conforme o decremento de num
                if (num < 40 && iniciouDevagar == false) {
                    giraDevagar();
                }
            }

            @Override
            public void onFinish() {
                anima.stop(); // parar animacao
            }
        }.start();
    }

    private void giraRapido() {
        tocarSom(); // chama o método para iniciar o som
        img.setBackgroundResource(R.drawable.giranumrapido); // seta a lista de imagens da animacao
        anima = (AnimationDrawable) img.getBackground(); // instancia o objeto animacao;
        anima.start(); // inicia a animacao
    }

    private void giraDevagar() {
        tocarSom();
        iniciouDevagar = true;
        img.setBackgroundResource(R.drawable.giranumdevagar);
        anima = (AnimationDrawable) img.getBackground();
        anima.start();
    }

    private void tocarSom() {
        try {
            if (player.isPlaying() && iniciouDevagar == false) {
                // Dependendo do tempo que você setou e estiver tocando o som
                player.release();
                // libere os recursos
                player = MediaPlayer.create(MainActivity.this, R.raw.piaodobau);
                // instancie o medica player novamento
                iniciouDevagar = true;
                // sete a variavel inicia devagar para true
            }
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    player.release();
                }
            });
            player.start();
        } catch (Exception e) {
            player.release();
        }
    }
}
