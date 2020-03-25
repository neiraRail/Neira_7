package datos;

import contextoProblema.Boleta;
import contextoProblema.Inventario;

import java.util.ArrayList;

public class Datos {
    private LectorJson lectorJson;
    private EscritorJson escritorJson;
    private ControlCSV csv = new ControlCSV();

    public Datos(){

    }

    public void guardarInventario(Inventario inventario) {
        csv.imprimirInventario(inventario);
    }

    public void guardarBoleta(Boleta boleta) {
        escritorJson = new EscritorJson("datos/Talonario.json");
        escritorJson.agregarObjeto(boleta);
        System.out.println("boleta emitida");
    }

    public void guardarBalance() {
    }



    public double[] obtenerInventario(){
        csv = new ControlCSV();
        return csv.leerInventario("datos/Inventario.csv");

    }

    public String[] obtenerPasswords() {
        csv = new ControlCSV();
        return csv.leerPassword("datos/pass.csv");

    }
    public void buscarBoleta(int nroBoleta){
        lectorJson = new LectorJson("datos/Talonario.csv");
        String file = lectorJson.leer_Archivo();
        ArrayList<String> boletas = lectorJson.separaBoletas(file);
        String boletaEncontrada = lectorJson.buscarBoleta(nroBoleta,boletas);


    }
}
