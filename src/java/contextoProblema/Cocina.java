package contextoProblema;

import datos.Sin_Ingredientes_Excepcion;

import java.util.ArrayList;

public class Cocina {

    private final Tienda tienda;

    public Cocina(Tienda tienda) {
        this.tienda = tienda;
    }

    void usarIngredientes(TipoPlato plato) throws Sin_Ingredientes_Excepcion{
        ArrayList<Inventario> faltas=new ArrayList<>();
        for (Inventario i:Inventario.values()) {
            if(plato.getReceta()[i.ordinal()]<=i.getCantidad()) {
                i.setCantidad(i.getCantidad() - plato.getReceta()[i.ordinal()]);
            }
            else {
                faltas.add(i);
            }
        }
        if (faltas.size()>0){
            throw new Sin_Ingredientes_Excepcion(faltas);
        }
    }


}
