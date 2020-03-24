package datos;

import contextoProblema.Boleta;
import contextoProblema.Inventario;
import contextoProblema.Tienda;

public class Datos {
    String Datos = "datos/";
    private LectorJson lectorJson;
    private EscritorJson escritorJson;
    private ControlCSV csv;

    public Datos(){
        lectorJson = new LectorJson("");
        escritorJson = new EscritorJson("");
        csv = new ControlCSV();
    }

    public void guardarInventario(Inventario inventario) {
        csv.crearArchivo("datos/Inventario.json");
        csv.imprimirInventario(inventario);
    }

    public void guardarBoleta(Boleta boleta) {
        escritorJson.agregarObjeto(boleta);
    }

    public void guardarBalance() {
    }

    public String[] obtenerPasswords() {
        csv = new ControlCSV();
        return csv.leerArchivo("datos/pass.csv");

    }
}
