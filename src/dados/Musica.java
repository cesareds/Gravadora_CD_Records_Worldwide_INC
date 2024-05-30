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
    public void setId(long id){
        this.id = id;
    }
    private long id;
    private final float duracao;
    private final int faixa;
    private final String autores;
    private final String titulo;
    private final String letra;
    private ArrayList<Disco> discos = new ArrayList<>();
    private ArrayList<Criador> criadores = new ArrayList<>();

    public float getDuracao() {
        return duracao;
    }

    public int getFaixa() {
        return faixa;
    }

    public String getAutores() {
        return autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getLetra() {
        return letra;
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
                "}\n";
    }
}
