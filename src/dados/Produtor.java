package dados;

import java.util.ArrayList;
import java.util.Objects;

public class Produtor {
    public Produtor(long id, String nome, String biografia, ArrayList<Disco> discos) {
        this.id = id;
        this.nome = nome;
        this.biografia = biografia;
        this.discos = discos;
    }
    private long id;
    private String nome;
    private String biografia;
    private ArrayList<Disco> discos = new ArrayList<>();

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produtor produtor)) return false;
        return Objects.equals(getNome(), produtor.getNome()) && Objects.equals(getBiografia(), produtor.getBiografia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getBiografia());
    }

    @Override
    public String toString() {
        return "Produtor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", biografia='" + biografia + '\'' +
                '}';
    }
}
