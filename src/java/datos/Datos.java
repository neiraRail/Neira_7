package datos;

import contextoProblema.Tienda;

public class Datos {
    LectorJson lectorJson;
    EscritorJson escritorJson;
    ControlCSV csv;
    Tienda tienda;
    public Datos(Tienda tienda){
        this.tienda=tienda;
        lectorJson = new LectorJson("");
        escritorJson = new EscritorJson("");
        csv = new ControlCSV();
    }

    public void guardarInventario() {
        csv.crearArchivo("Inventario");
        csv.imprimirInventario();
    }

    public void guardarTalonario() {
    }

    public void guardarBalance() {
    }
}
