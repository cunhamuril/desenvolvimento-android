package com.projeto.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
    private SpriteBatch batch;

    private Texture[] passaro;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Texture gameOver;

    private Random numeroRandomico;

    private BitmapFont mensagem;
    private BitmapFont fonte;

    private Circle passaroCirculo;
    private Rectangle retanguloCanoTopo;
    private Rectangle retanguloCanoBaixo;
//    private ShapeRenderer shape;

    // Atributos de configuração
    private int larguraDispositivo;
    private int alturaDispositivo;
    private int pontuacao = 0;
    private int estadoJogo = 0; // 0 -> jogo não iniciado | 1 -> jogo iniciado | 2 -> Tela game over

    private boolean marcouPonto;

    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical;

    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float alturaEntreCanosRandomica;

    private float deltaTime;

    @Override
    public void create() {
        batch = new SpriteBatch();
        numeroRandomico = new Random();

        passaroCirculo = new Circle();
//        retanguloCanoTopo = new Rectangle();
//        retanguloCanoBaixo = new Rectangle();
//        shape = new ShapeRenderer();

        mensagem = new BitmapFont();
        mensagem.setColor(Color.WHITE);
        mensagem.getData().setScale(3);

        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(6);

        passaro = new Texture[3];
        passaro[0] = new Texture("passaro1.png");
        passaro[1] = new Texture("passaro2.png");
        passaro[2] = new Texture("passaro3.png");

        fundo = new Texture("fundo.png");
        canoBaixo = new Texture("cano_baixo_maior.png");
        canoTopo = new Texture("cano_topo_maior.png");
        gameOver = new Texture("game_over.png");

        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();

        posicaoInicialVertical = alturaDispositivo / 2;

        posicaoMovimentoCanoHorizontal = larguraDispositivo;
        espacoEntreCanos = 300;
    }

    @Override
    public void render() {
        //Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // esta linha de código não existe na video aula, mas sem ela dá um bug estranho

        deltaTime = Gdx.graphics.getDeltaTime();

        // bater de asas do passaro
        variacao += deltaTime * 10;
        if (variacao > 2) variacao = 0;

        // condição que verifica se o jogo foi iniciado ou não
        if (estadoJogo == 0) {
            if (Gdx.input.justTouched()) {
                estadoJogo = 1;
            }
        } else {
            velocidadeQueda++;

            if (estadoJogo == 1) {
                // movimentação dos canos
                posicaoMovimentoCanoHorizontal -= deltaTime * 200;

                // condição que verifica se a tela foi tocada
                if (Gdx.input.justTouched()) {
                    velocidadeQueda = -20;
                }

                // verifica se o cano saiu inteiramente da tela
                if (posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()) {
                    posicaoMovimentoCanoHorizontal = larguraDispositivo - 100;
                    alturaEntreCanosRandomica = numeroRandomico.nextInt(400) - 200;
                    marcouPonto = false;
                }

                // verifica pontuação
                if (posicaoMovimentoCanoHorizontal < 120) {
                    if (!marcouPonto) {
                        pontuacao++;
                        marcouPonto = true;
                    }
                }
            } else { // tela de game over
                if (Gdx.input.justTouched()) {
                    /**
                     * aqui vai regitrar o record
                     */

                    // Ao reniciar...
                    estadoJogo = 0;
                    pontuacao = 0;
                    velocidadeQueda = 0;
                    posicaoInicialVertical = alturaDispositivo / 2;
                    posicaoMovimentoCanoHorizontal = larguraDispositivo;
                }
            }

            // queda do passado, inicializando no meio e caindo até o fim da tela
            if (posicaoInicialVertical > 0 || velocidadeQueda < 0) {
                posicaoInicialVertical = posicaoInicialVertical - velocidadeQueda;
            }

        }

        // renderização dos componentes.
        // as camadas sao definidas pela posicao da linha: primeira linha -> ultima camada, ultima linha -> primeira camada
        batch.begin();

        batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
        batch.draw(
                canoTopo,
                posicaoMovimentoCanoHorizontal,
                alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica
        );
        batch.draw(
                canoBaixo,
                posicaoMovimentoCanoHorizontal,
                alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomica
        );
        batch.draw(passaro[(int) variacao], 120, posicaoInicialVertical);
        fonte.draw(batch, String.valueOf(pontuacao), larguraDispositivo / 2, alturaDispositivo - 50);

        if (estadoJogo == 2) {
            batch.draw(gameOver, larguraDispositivo / 2 - gameOver.getWidth() / 2, alturaDispositivo / 2);
            mensagem.draw(batch, "Toque para reiniciar" ,larguraDispositivo / 2 - gameOver.getWidth() / 2, alturaDispositivo / 2 - gameOver.getHeight() / 2);
        }

        batch.end();

        passaroCirculo.set(
                120 + passaro[0].getWidth() / 2,
                posicaoInicialVertical + passaro[0].getHeight() / 2,
                passaro[0].getWidth() / 2
        );
        retanguloCanoBaixo = new Rectangle(
                posicaoMovimentoCanoHorizontal,
                alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomica,
                canoBaixo.getWidth(),
                canoBaixo.getHeight()
        );
        retanguloCanoTopo = new Rectangle(
                posicaoMovimentoCanoHorizontal,
                alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica,
                canoBaixo.getWidth(),
                canoBaixo.getHeight()
        );

        // Desenhar formas
//        shape.begin(ShapeRenderer.ShapeType.Filled);
//        shape.circle( passaroCirculo.x, passaroCirculo.y, passaroCirculo.radius);
//        shape.rect(retanguloCanoBaixo.x, retanguloCanoBaixo.y, retanguloCanoBaixo.width, retanguloCanoBaixo.height);
//        shape.rect(retanguloCanoTopo.x, retanguloCanoTopo.y, retanguloCanoTopo.width, retanguloCanoTopo.height);
//        shape.setColor(Color.RED);
//        shape.end();

        // teste de colisao
        if (Intersector.overlaps(passaroCirculo, retanguloCanoBaixo) || Intersector.overlaps(passaroCirculo, retanguloCanoTopo)
                || (posicaoInicialVertical <= 0) || (posicaoInicialVertical >= alturaDispositivo)) {
            estadoJogo = 2;
        }
    }

    /**
     * nas videoaulas este bloco do código não existe.
     * Mas como essa é uma versão diferente das aulas, acredito que seja necessário
     * pois estava assim antes de começar o projeto.
     */
    @Override
    public void dispose() {
        batch.dispose();

        passaro[0].dispose();
        passaro[1].dispose();
        passaro[2].dispose();

        fundo.dispose();

        canoBaixo.dispose();
        canoTopo.dispose();

        fonte.dispose();
    }
}
