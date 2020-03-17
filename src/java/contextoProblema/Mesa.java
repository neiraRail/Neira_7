package contextoProblema;

import datos.Sin_Ingredientes_Excepcion;

import java.util.ArrayList;
import java.util.Objects;

public class Mesa {
    private boolean ocupado = false;
    private Boleta boleta;
    private Tienda tienda;

    Mesa(Tienda tienda){
        this.tienda=tienda;
    }

    void setBoleta(Boleta boleta){
        this.boleta=boleta;
    }

    public Boleta getBoleta(){
        return this.boleta;
    }

    void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean esOcupado() {
        return ocupado;
    }

    public String consumoString() {
        StringBuilder platos = new StringBuilder();
        for (TipoPlato tipoPlato : boleta.getConsumo()) {
            platos.append(tipoPlato.toString()).append("\n");
        }
        return platos.toString();
    }

    String consumoCSV(){
        StringBuilder platos = new StringBuilder();
        for (TipoPlato tipoPlato : boleta.getConsumo()) {
            platos.append(tipoPlato).append(",");
        }
        return platos.toString();
    }

    public void hacerPedido(int opcion){
        try {
            agregarPlato(opcion);
        }
        catch (Sin_Ingredientes_Excepcion SIE){
            System.out.println("Faltan los siguientes Ingredientes");
            for(Inventario i:SIE.ingredientes){
                System.out.println(i.name()+": "+ Objects.requireNonNull(TipoPlato.get(opcion)).faltante(i));
            }
        }
    }

    private void agregarPlato(int opcion)throws Sin_Ingredientes_Excepcion {
        TipoPlato plato = TipoPlato.get(opcion);
        tienda.getCocina().usarIngredientes(plato);
        boleta.getConsumo().add(plato);
    }

}
