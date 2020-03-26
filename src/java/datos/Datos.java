package datos;

import contextoProblema.Boleta;
import contextoProblema.Inventario;
import contextoProblema.TipoPlato;

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
        System.out.println("boleta emitida, metodo guuardar boleta de Datos");
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
    public Boleta buscarBoleta(int nroBoleta){
        ArrayList<TipoPlato> consumo = new ArrayList<>();

        lectorJson = new LectorJson("datos/Talonario.json");
        String file = lectorJson.leer_Archivo();
        ArrayList<String> boletas = LectorJson.separaBoletas(file);
        String boletaEncontrada = LectorJson.buscarBoleta(nroBoleta,boletas);

        int nroID = Integer.parseInt(boletaEncontrada.split(":")[1].split(",")[0]);
        String[] listado = boletaEncontrada.split("\\[")[1].split("]")[0].split(",");

        consumo = crearConsumo(listado);

        return new Boleta(nroID,consumo);
    }

     ArrayList<TipoPlato> crearConsumo(String[] listado) {
        ArrayList<TipoPlato> consumo = new ArrayList<>();
        for(String plato:listado){
            double precio = Double.parseDouble(plato);
            consumo.add(TipoPlato.get(precio,true));
        }
        return consumo;
    }

    public int nroBoletas() {
        lectorJson = new LectorJson("datos/Talonario.json");
        String file = lectorJson.leer_Archivo();
        ArrayList<String> boletas = LectorJson.separaBoletas(file);
        return  boletas.size();
    }

}
