package br.com.leonamCruz.model.entidade;

public abstract class Aluno {
    private int id;
    private String nome;
    private int idade;
    private String nascimento; //AAAA-MM -DD
    private int serie;

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getNascimento() {
        return nascimento;
    }

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }
}
