package br.com.leonamCruz.model.entidade;

public abstract class Aluno {
    private int id;
    private String nome;
    private byte idade;
    private byte serie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte getIdade() {
        return idade;
    }

    public void setIdade(byte idade) {
        this.idade = idade;
    }

    public byte getSerie() {
        return serie;
    }

    public void setSerie(byte serie) {
        this.serie = serie;
    }
}
