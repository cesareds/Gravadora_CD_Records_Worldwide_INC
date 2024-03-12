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
    private LocalDate dataLancamento;
    private double preco;
    private int platinas;
    private String titulo;
    private String formato;
    private String descricao;
    private String genero;
    private ArrayList<Produtor> produtores = new ArrayList<>();
    private ArrayList<Musica> musicas = new ArrayList<>();

    public ArrayList<Produtor> getProdutores() {
        return produtores;
    }

    public void setProdutores(ArrayList<Produtor> produtores) {
        this.produtores = produtores;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getPlatinas() {
        return platinas;
    }

    public void setPlatinas(int platinas) {
        this.platinas = platinas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
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
