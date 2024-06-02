package dados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Banda extends Criador{
    //construtor com tudo
    public Banda(int id, String nome, String descricao, String genero, LocalDate dataDeFormacao, HashMap<Musico, String> musicoStringHashMap, ArrayList<Disco> discos, ArrayList<Musica> musicas) {
        super(id, nome, descricao, genero, discos, musicas);
        this.dataDeFormacao = dataDeFormacao;
        this.musicoStringHashMap = musicoStringHashMap;
    }
    //construtor só o básico
    public Banda(String nome, String descricao, String genero, LocalDate dataDeFormacao) {
        super(nome, descricao, genero);
        this.dataDeFormacao = dataDeFormacao;
    }
    private final LocalDate dataDeFormacao;
    public HashMap<Musico, String> musicoStringHashMap = new HashMap<>();
    public LocalDate getDataDeFormacao() {
        return dataDeFormacao;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Banda banda)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getDataDeFormacao(), banda.getDataDeFormacao());
    }
    @Override
    public String toString() {
        return "Banda{" + '\'' +
                ", nome='" + super.getNome() + '\'' +
                ", descricao='" + super.getDescricao() + '\'' +
                ", genero='" + super.getGenero() + '\'' +
                ", dataDeFormacao=" + dataDeFormacao +
                "}\n";
    }
}
