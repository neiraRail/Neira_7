package contextoProblema;

import java.util.ArrayList;

public class Inventario {
    /*Esto se usará cuando los ingredientes pasen a ser un archivo modificable
    ArrayList<Ingredientes> ingredientes = new ArrayList<>();
    ArrayList<TipoPlato> platos = new ArrayList<>();
*/
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

    public void actualizarCon(double[] datosInventario) {
        for(Ingredientes i:Ingredientes.values()){
            i.setCantidad(datosInventario[i.ordinal()]);
        }
    }
}
