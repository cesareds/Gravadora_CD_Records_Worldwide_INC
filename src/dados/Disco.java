package dados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Disco {
    //construtor com tudo
    public Disco(long identificador, LocalDate dataLancamento, double preco, int platinas, String titulo, String formato, String descricao, String genero, ArrayList<Produtor> produtores, ArrayList<Musica> musicas) {
        this.identificador = identificador;
        this.dataLancamento = dataLancamento;
        this.preco = preco;
        this.platinas = platinas;
        this.titulo = titulo;
        this.formato = formato;
        this.descricao = descricao;
        this.genero = genero;
        this.produtores = produtores;
        this.musicas = musicas;
    }
    //construtor só o básico
    public Disco(LocalDate dataLancamento, double preco, int platinas, String titulo, String formato, String descricao, String genero) {
        this.dataLancamento = dataLancamento;
        this.preco = preco;
        this.platinas = platinas;
        this.titulo = titulo;
        this.formato = formato;
        this.descricao = descricao;
        this.genero = genero;
    }

    private long identificador;
    private final LocalDate dataLancamento;
    private final double preco;
    private final int platinas;
    private final String titulo;
    private final String formato;
    private final String descricao;
    private final String genero;
    private ArrayList<Produtor> produtores = new ArrayList<>();
    private ArrayList<Musica> musicas = new ArrayList<>();


    public long getIdentificador() {
        return identificador;
    }
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
    public double getPreco() {
        return preco;
    }
    public int getPlatinas() {
        return platinas;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getFormato() {
        return formato;
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
        if (!(o instanceof Disco disco)) return false;
        return Double.compare(getPreco(), disco.getPreco()) == 0 && getPlatinas() == disco.getPlatinas() && Objects.equals(getDataLancamento(), disco.getDataLancamento()) && Objects.equals(getTitulo(), disco.getTitulo()) && Objects.equals(getFormato(), disco.getFormato()) && Objects.equals(getDescricao(), disco.getDescricao()) && Objects.equals(getGenero(), disco.getGenero());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentificador(), getDataLancamento(), getPreco(), getPlatinas(), getTitulo(), getFormato(), getDescricao(), getGenero());
    }

    @Override
    public String toString() {
        return "Disco{" +
                "dataLancamento=" + dataLancamento +
                ", preco=" + preco +
                ", platinas=" + platinas +
                ", titulo='" + titulo + '\'' +
                ", formato='" + formato + '\'' +
                ", descricao='" + descricao + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
