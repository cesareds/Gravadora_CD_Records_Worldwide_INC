package dados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Musico extends Criador{
    //construtor com tudo
    public Musico(int nroRegistro, String nome, String descricao, String genero, String cep, String rua, String cidade, String estado, String telefone, HashMap<Banda, String> bandaStringHashMap, ArrayList<Instrumento> instrumentos, ArrayList<Disco> discos, ArrayList<Musica> musicas) {
        super(nroRegistro, nome, descricao, genero, discos, musicas);
        this.cep = cep;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.bandaStringHashMap = bandaStringHashMap;
        this.instrumentos = instrumentos;
    }
    //construtor só com o básico
    public Musico(String nome, String descricao, String genero, String cep, String rua, String cidade, String estado, String telefone) {
        super(nome, descricao, genero);
        this.cep = cep;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
    }

    private final String cep;
    private final String rua;
    private final String cidade;
    private final String estado;
    private final String telefone;
    private HashMap<Banda, String> bandaStringHashMap = new HashMap<>();
    private ArrayList<Instrumento> instrumentos = new ArrayList<>();



    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musico musico)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCep(), musico.getCep()) && Objects.equals(getRua(), musico.getRua()) && Objects.equals(getCidade(), musico.getCidade()) && Objects.equals(getEstado(), musico.getEstado()) && Objects.equals(getTelefone(), musico.getTelefone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCep(), getRua(), getCidade(), getEstado(), getTelefone());
    }

    @Override
    public String toString() {
        return "Musico{" +
                ", nome='" + super.getNome() + '\'' +
                ", descricao='" + super.getDescricao() + '\'' +
                ", genero='" + super.getGenero() + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", telefone='" + telefone + '\'' +
                "}\n";
    }
}
