package metier;

public class Versement extends Operation {
    private String source;

    public Versement(String numero, double montant, String source) {
        super(numero, montant);
        this.source = source;
    }

    @Override
    public String getType() {
        return "VERSEMENT";
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Versement [numero=" + numero + ", date=" + date + ", montant=" + montant + ", source=" + source + "]";
    }
}
