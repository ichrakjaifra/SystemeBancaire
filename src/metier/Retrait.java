package metier;

public class Retrait extends Operation {
    private String destination;

    public Retrait(String numero, double montant, String destination) {
        super(numero, montant);
        this.destination = destination;
    }

    @Override
    public String getType() {
        return "RETRAIT";
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Retrait [identifiant=" + numero + ", date=" + date + ", montant=" + montant + ", destination=" + destination + "]";
    }
}
