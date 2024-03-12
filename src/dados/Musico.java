package dados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Musico extends Criador{
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
    private String cep;
    private String rua;
    private String cidade;
    private String estado;
    private String telefone;
    private HashMap<Banda, String> bandaStringHashMap = new HashMap<>();
    private ArrayList<Instrumento> instrumentos = new ArrayList<>();

    public HashMap<Banda, String> getBandaStringHashMap() {
        return bandaStringHashMap;
    }

    public void setBandaStringHashMap(HashMap<Banda, String> bandaStringHashMap) {
        this.bandaStringHashMap = bandaStringHashMap;
    }

    public ArrayList<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(ArrayList<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
                "cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", telefone='" + telefone + '\'' +
                ", bandaStringHashMap=" + bandaStringHashMap +
                ", instrumentos=" + instrumentos +
                '}';
    }
}
