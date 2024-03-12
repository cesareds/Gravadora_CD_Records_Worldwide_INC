package dados;

import java.util.ArrayList;
import java.util.Objects;

public class Instrumento {
    public Instrumento(long codigoInterno, String marca, String tipo, String nome, ArrayList<Musico> musicos) {
        this.codigoInterno = codigoInterno;
        this.marca = marca;
        this.tipo = tipo;
        this.nome = nome;
        this.musicos = musicos;
    }

    private long codigoInterno;
    private String marca;
    private String tipo;
    private String nome;
    private ArrayList<Musico> musicos = new ArrayList<>();

    public ArrayList<Musico> getMusicos() {
        return musicos;
    }

    public void setMusicos(ArrayList<Musico> musicos) {
        this.musicos = musicos;
    }

    public long getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(long codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instrumento that)) return false;
        return Objects.equals(getMarca(), that.getMarca()) && Objects.equals(getTipo(), that.getTipo()) && Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarca(), getTipo(), getNome());
    }
}
