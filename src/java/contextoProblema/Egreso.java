package contextoProblema;

public class Egreso {
    private double monto;
    private Caja caja;



    @Override
    public String toString() {
        return "Egreso "+caja.getEgresos().size()+":\t\t\t $"+monto;
    }

    public Egreso(double monto, Caja caja){
        this.monto=monto;
        this.caja=caja;
    }

    public double getMonto() {
        return this.monto;
    }
}
