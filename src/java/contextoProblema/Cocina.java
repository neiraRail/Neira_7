package contextoProblema;

import datos.Sin_Ingredientes_Excepcion;

import java.util.ArrayList;

public class Cocina {

private Tienda tienda;


    public Cocina(Tienda tienda) {
        this.tienda = tienda;
    }

    void usarIngredientes(TipoPlato plato) throws Sin_Ingredientes_Excepcion{

        ArrayList<Ingredientes> faltantes=new ArrayList<>();
        for (Ingredientes i:Ingredientes.values()) {
            if(plato.getReceta()[i.ordinal()]<=i.getCantidad()) {
                i.setCantidad(i.getCantidad() - plato.getReceta()[i.ordinal()]);
            }
            else {

                faltantes.add(i);
            }
        }
        if (faltantes.size()>0){
            throw new Sin_Ingredientes_Excepcion(faltantes);
        }
    }


}
