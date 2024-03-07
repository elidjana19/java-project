public class Data {
    private int viti, muaji, dita;

    public Data() {
    }

    public int getViti() {
        return viti;
    }

    public void setViti(int viti) {
        this.viti = viti;
    }

    public int getMuaji() {
        return muaji;
    }

    public void setMuaji(int muaji) {
        this.muaji = muaji;
    }

    public int getDita() {
        return dita;
    }

    public void setDita(int dita) {
        this.dita = dita;
    }

    @Override
    public String toString() {
        return "Data: " + viti +
                " " + muaji +
                " " + dita;
    }
}
