package dados;

import java.util.ArrayList;
import java.util.Objects;

public class Musica {
    //construtor completo
    public Musica(long id, float duracao, int faixa, String autores, String titulo, String letra, ArrayList<Disco> discos, ArrayList<Criador> criadores) {
        this.id = id;
        this.duracao = duracao;
        this.faixa = faixa;
        this.autores = autores;
        this.titulo = titulo;
        this.letra = letra;
        this.discos = discos;
        this.criadores = criadores;
    }
    //construtor só o básico
    public Musica(float duracao, int faixa, String autores, String titulo, String letra) {
        this.duracao = duracao;
        this.faixa = faixa;
        this.autores = autores;
        this.titulo = titulo;
        this.letra = letra;
    }

    private long id;
    private float duracao;
    private int faixa;
    private String autores;
    private String titulo;
    private String letra;
    private ArrayList<Disco> discos = new ArrayList<>();
    private ArrayList<Criador> criadores = new ArrayList<>();

    public ArrayList<Criador> getCriadores() {
        return criadores;
    }

    public void setCriadores(ArrayList<Criador> criadores) {
        this.criadores = criadores;
    }

    public ArrayList<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(ArrayList<Disco> discos) {
        this.discos = discos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }

    public int getFaixa() {
        return faixa;
    }

    public void setFaixa(int faixa) {
        this.faixa = faixa;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musica musica)) return false;
        return Float.compare(getDuracao(), musica.getDuracao()) == 0 && getFaixa() == musica.getFaixa() && Objects.equals(getAutores(), musica.getAutores()) && Objects.equals(getTitulo(), musica.getTitulo()) && Objects.equals(getLetra(), musica.getLetra());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDuracao(), getFaixa(), getAutores(), getTitulo(), getLetra());
    }

    @Override
    public String toString() {
        return "Musica{" +
                "id=" + id +
                ", duracao=" + duracao +
                ", faixa=" + faixa +
                ", autores='" + autores + '\'' +
                ", titulo='" + titulo + '\'' +
                ", letra='" + letra + '\'' +
                '}';
    }
}
