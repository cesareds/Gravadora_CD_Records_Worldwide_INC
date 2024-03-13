package dados;

import java.util.ArrayList;
import java.util.Objects;

public class Criador {
    //construtor com tudo
    public Criador(int id, String nome, String descricao, String genero, ArrayList<Disco> discos, ArrayList<Musica> musicas) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.genero = genero;
        this.discos = discos;
        this.musicas = musicas;
    }
    //construtor só com o básico
    public Criador(String nome, String descricao, String genero) {
        this.nome = nome;
        this.descricao = descricao;
        this.genero = genero;
    }
    private int id;
    private final String nome;
    private final String descricao;
    private final String genero;
    private ArrayList<Disco> discos = new ArrayList<>();
    private ArrayList<Musica> musicas = new ArrayList<>();

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getGenero() {
        return genero;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Criador criador)) return false;
        return Objects.equals(getNome(), criador.getNome()) && Objects.equals(getDescricao(), criador.getDescricao()) && Objects.equals(getGenero(), criador.getGenero());
    }
    @Override
    public String toString() {
        return "Criador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
