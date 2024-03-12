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
    private String nome;
    private String descricao;
    private String genero;
    private ArrayList<Disco> discos = new ArrayList<>();
    private ArrayList<Musica> musicas = new ArrayList<>();

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }
    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }
    public ArrayList<Disco> getDiscos() {
        return discos;
    }
    public void setDiscos(ArrayList<Disco> discos) {
        this.discos = discos;
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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Criador criador)) return false;
        return Objects.equals(getNome(), criador.getNome()) && Objects.equals(getDescricao(), criador.getDescricao()) && Objects.equals(getGenero(), criador.getGenero());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getDescricao(), getGenero());
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
