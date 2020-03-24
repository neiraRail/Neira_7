package contextoProblema;

import java.util.ArrayList;

public class Inventario {
    ArrayList<Ingredientes> ingredientes = new ArrayList<>();
    ArrayList<TipoPlato> platos = new ArrayList<>();

    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        for(Ingredientes i:Ingredientes.values()){
            stringBuilder.append(i.name()).append(": ").append(i.getCantidad()).append("\n");
        }
        return stringBuilder.toString();

    }
    public String toCSV(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Ingredientes i:Ingredientes.values()){
            stringBuilder.append(i.name()).append(",").append(i.getCantidad()).append("\n");
        }
        return stringBuilder.toString();
    }
}
