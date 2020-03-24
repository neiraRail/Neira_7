package datos;

import contextoProblema.Boleta;
import contextoProblema.Inventario;
import contextoProblema.Tienda;

public class Datos {
    LectorJson lectorJson;
    EscritorJson escritorJson;
    ControlCSV csv;

    public Datos(){
        lectorJson = new LectorJson("");
        escritorJson = new EscritorJson("");
        csv = new ControlCSV();
    }

    public void guardarInventario(Inventario inventario) {
        csv.crearArchivo("Inventario");
        csv.imprimirInventario(inventario);
    }

    public void guardarBoleta(Boleta boleta) {
        escritorJson.agregarObjeto(boleta);
    }

    public void guardarBalance() {
    }
}
