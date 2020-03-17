package contextoProblema;

public class Ingreso {
    private Boleta boleta;
    private double monto;

    public Ingreso(Boleta boleta){
        this.boleta = boleta;
        this.monto = boleta.getTotal();
    }

    @Override
    public String toString() {
        return "Ingreso "+boleta.getNroID()+":\t $"+monto;
    }

    public Boleta getBoleta() {
        return boleta;
    }

    public double getMonto() {
        return monto;
    }
}