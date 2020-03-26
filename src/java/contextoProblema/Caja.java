package contextoProblema;

import datos.ControlCSV;
import datos.Datos;
import datos.EscritorJson;

import java.util.ArrayList;

public class Caja {

    private ArrayList<Ingreso> ingresos = new ArrayList<>();
    private ArrayList<Egreso> egresos = new ArrayList<>();

    Caja(){

    }



    public ArrayList<Ingreso> getIngresos() {
        return ingresos;
    }

    public ArrayList<Egreso> getEgresos() {
        return egresos;
    }


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

    void hacer_Egreso(double monto) {
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

}




