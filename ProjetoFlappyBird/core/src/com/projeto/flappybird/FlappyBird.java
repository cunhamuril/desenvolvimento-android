package com.projeto.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture[] passaro;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Random numeroRandomico;

    // Atributos de configuração
    private int larguraDispositivo;
    private int alturaDispositivo;

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

        passaro = new Texture[3];
        passaro[0] = new Texture("passaro1.png");
        passaro[1] = new Texture("passaro2.png");
        passaro[2] = new Texture("passaro3.png");

        fundo = new Texture("fundo.png");

        canoBaixo = new Texture("cano_baixo_maior.png");
        canoTopo = new Texture("cano_topo_maior.png");

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

        // queda do passado, inicializando no meio e caindo até o fim da tela
        velocidadeQueda++;
        if (posicaoInicialVertical > 0 || velocidadeQueda < 0) {
			posicaoInicialVertical = posicaoInicialVertical - velocidadeQueda;
        }

        // condição que verifica se a tela foi tocada
        if (Gdx.input.justTouched()) {
            velocidadeQueda = -20;
        }

        // movimentação dos canos
        posicaoMovimentoCanoHorizontal -= deltaTime * 200;
        // verifica se o cano saiu inteiramente da tela
        if (posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()) {
            posicaoMovimentoCanoHorizontal = larguraDispositivo - 100;
            alturaEntreCanosRandomica = numeroRandomico.nextInt(400) - 200;
        }

        batch.begin();

        // renderização dos componentes.
        // as camadas sao definidas pela posicao da linha: primeira linha -> ultima camada, ultima linha -> primeira camada
        batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
        batch.draw(canoTopo, posicaoMovimentoCanoHorizontal,
                alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica);
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal,
                alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomica);
        batch.draw(passaro[(int) variacao], 120, posicaoInicialVertical);

        batch.end();
    }

    // nas videoaulas essa parte do código não existe.
    // Mas como essa é uma versão diferente das aulas, acredito que seja necessário
    @Override
    public void dispose() {
        batch.dispose();

        passaro[0].dispose();
        passaro[1].dispose();
        passaro[2].dispose();

        fundo.dispose();

        canoBaixo.dispose();
        canoTopo.dispose();
    }
}
