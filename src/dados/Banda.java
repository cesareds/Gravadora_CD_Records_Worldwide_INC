package dados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Banda extends Criador{
    public Banda(int id, String nome, String descricao, String genero, LocalDate dataDeFormacao, HashMap<Musico, String> musicoStringHashMap, ArrayList<Disco> discos, ArrayList<Musica> musicas) {
        super(id, nome, descricao, genero, discos, musicas);
        this.dataDeFormacao = dataDeFormacao;
        this.musicoStringHashMap = musicoStringHashMap;
    }
    private LocalDate dataDeFormacao;

    public HashMap<Musico, String> musicoStringHashMap = new HashMap<>();


    public LocalDate getDataDeFormacao() {
        return dataDeFormacao;
    }

    public void setDataDeFormacao(LocalDate dataDeFormacao) {
        this.dataDeFormacao = dataDeFormacao;
    }

    public HashMap<Musico, String> getMusicoStringHashMap() {
        return musicoStringHashMap;
    }

    public void setMusicoStringHashMap(HashMap<Musico, String> musicoStringHashMap) {
        this.musicoStringHashMap = musicoStringHashMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Banda banda)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getDataDeFormacao(), banda.getDataDeFormacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDataDeFormacao());
    }

    @Override
    public String toString() {
        return "Banda{" +
                "dataDeFormacao=" + dataDeFormacao +
                ", musicoStringHashMap=" + musicoStringHashMap +
                '}';
    }
}
