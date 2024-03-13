package dados;

import java.util.ArrayList;
import java.util.Objects;

public class Instrumento {
    //construtor completo
    public Instrumento(long codigoInterno, String marca, String tipo, String nome, ArrayList<Musico> musicos) {
        this.codigoInterno = codigoInterno;
        this.marca = marca;
        this.tipo = tipo;
        this.nome = nome;
        this.musicos = musicos;
    }
    //construtor só com o básico
    public Instrumento(String marca, String tipo, String nome) {
        this.marca = marca;
        this.tipo = tipo;
        this.nome = nome;
    }

    private long codigoInterno;
    private final String marca;
    private final String tipo;
    private final String nome;
    private ArrayList<Musico> musicos = new ArrayList<>();

    public String getMarca() {
        return marca;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
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

    @Override
    public String toString() {
        return "Instrumento{" +
                "marca='" + marca + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
