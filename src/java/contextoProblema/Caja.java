package contextoProblema;

import datos.ControlCSV;
import datos.Datos;
import datos.EscritorJson;

import java.util.ArrayList;

public class Caja {
    //private ArrayList<Boleta> talonario = new ArrayList<>();
    private Tienda tienda;
    private ArrayList<Ingreso> ingresos = new ArrayList<>();
    private ArrayList<Egreso> egresos = new ArrayList<>();

    Caja(Tienda tienda){
        this.tienda=tienda;
    }


    /*public ArrayList<Boleta> getTalonario() {
        return talonario;
    }*/


    public ArrayList<Ingreso> getIngresos() {
        return ingresos;
    }

    public ArrayList<Egreso> getEgresos() {
        return egresos;
    }

    /*public String mostrarTalonario() {
        StringBuilder stringBuilder=new StringBuilder();
        for (Boleta boleta : talonario) {
            stringBuilder.append(boleta).append("\n");
        }
        return stringBuilder.toString();
    }*/
/*
    public void emitirBoleta(int nroMesa) {
        ControlCSV ctrl = new ControlCSV();
        ctrl.imprimirBoleta(tienda.getMesa(nroMesa).getBoleta());
        tienda.getMesa(nroMesa).setOcupado(false);
        hacer_Ingreso(tienda.getMesa(nroMesa).getBoleta());
    }*/

    public void guardarBoleta(Mesa mesa) {
        Datos datos = new Datos();
        datos.guardarBoleta(mesa.getBoleta());
        mesa.setOcupado(false);
        hacer_Ingreso(mesa.getBoleta());
    }


    public Boleta getBoleta(int boletaID){
        Datos datos = new Datos();
        return datos.buscarBoleta(boletaID);

    }


    private void hacer_Ingreso(Boleta boleta) {
        Ingreso CI = new Ingreso(boleta);
        ingresos.add(CI);
    }

    public void hacer_Egreso(double monto) {
        System.out.println(monto);
        Egreso CE = new Egreso(monto,this);
        egresos.add(CE);
    }

    public double getSaldo(){
        double total_CI=0;
        double total_CE=0;
        for(Ingreso i:ingresos)
            total_CI+=i.getMonto();
        for (Egreso e:egresos)
            total_CE+=e.getMonto();
        return total_CI-total_CE;
    }

    public int sizeTalonario(){
        Datos datos = new Datos();
        return datos.nroBoletas();
    }
}




