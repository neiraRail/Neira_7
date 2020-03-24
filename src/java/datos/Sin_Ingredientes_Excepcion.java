package datos;

import contextoProblema.Ingredientes;

import java.util.ArrayList;


public class Sin_Ingredientes_Excepcion extends Throwable {
    public ArrayList<Ingredientes> ingredientes;

    public Sin_Ingredientes_Excepcion(ArrayList<Ingredientes> ingredientes){
        this.ingredientes = ingredientes;
    }
}
