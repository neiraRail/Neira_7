package datos;

import contextoProblema.Inventario;

import java.util.ArrayList;


public class Sin_Ingredientes_Excepcion extends Throwable {
    public ArrayList<Inventario> ingredientes;

    public Sin_Ingredientes_Excepcion(ArrayList<Inventario> ingredientes){
        this.ingredientes = ingredientes;
    }
}
