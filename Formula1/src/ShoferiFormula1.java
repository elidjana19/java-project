public class ShoferiFormula1 extends Shoferi{
    private int nrVendPare,nrVendiDyte, nrVendiTrete, piket, nrGarave;

    public int getNrVendPare() {
        return nrVendPare;
    }

    public void setNrVendPare(int nrVendPare) {
        this.nrVendPare = nrVendPare;
    }

    public int getNrVendiDyte() {
        return nrVendiDyte;
    }

    public void setNrVendiDyte(int nrVendiDyte) {
        this.nrVendiDyte = nrVendiDyte;
    }

    public int getNrVendiTrete() {
        return nrVendiTrete;
    }

    public void setNrVendiTrete(int nrVendiTrete) {
        this.nrVendiTrete = nrVendiTrete;
    }

    public int getPiket() {
        return piket;
    }

    public void setPiket(int piket) {
        this.piket = piket;
    }

    public int getNrGarave() {
        return nrGarave;
    }

    public void setNrGarave(int nrGarave) {
        this.nrGarave = nrGarave;
    }

    @Override
    public String toString() {
        return  emri +
                ", Skuadra: " + skuadra +
                ", Ne vend te pare: " + nrVendPare +
                ", Ne vend te dyte: " + nrVendiDyte +
                ", Ne vend te trete: " + nrVendiTrete +
                ", Piket: " + piket +
                ", Gara te kryera: " + nrGarave + "\n";
    }

}
