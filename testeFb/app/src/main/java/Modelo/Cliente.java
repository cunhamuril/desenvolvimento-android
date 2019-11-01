package Modelo;

import androidx.annotation.NonNull;

public class Cliente {
    private String id;
    private String nome;
    private String cidade;

    public Cliente() {

    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

   public String getCidade() {
        return cidade;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
