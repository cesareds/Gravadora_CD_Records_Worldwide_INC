package sistema;

import dados.*;

import java.util.ArrayList;

public class Gravadora {
    private ArrayList<Banda> bandas = new ArrayList<>();
    private ArrayList<Musico> musicos = new ArrayList<>();
    private ArrayList<Disco> discos = new ArrayList<>();
    private ArrayList<Instrumento> instrumentos = new ArrayList<>();
    private ArrayList<Musica> musicas = new ArrayList<>();
    private ArrayList<Produtor> produtores = new ArrayList<>();

    public ArrayList<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(ArrayList<Banda> bandas) {
        this.bandas = bandas;
    }

    public ArrayList<Musico> getMusicos() {
        return musicos;
    }

    public void setMusicos(ArrayList<Musico> musicos) {
        this.musicos = musicos;
    }

    public ArrayList<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(ArrayList<Disco> discos) {
        this.discos = discos;
    }

    public ArrayList<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(ArrayList<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public ArrayList<Produtor> getProdutores() {
        return produtores;
    }

    public void setProdutores(ArrayList<Produtor> produtores) {
        this.produtores = produtores;
    }
}
