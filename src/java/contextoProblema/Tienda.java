package contextoProblema;

import datos.ControlCSV;
import datos.Datos;

import javax.naming.ldap.Control;
import java.util.ArrayList;


public class Tienda {

	private Mesa[] mesas = {new Mesa(this),new Mesa(this),new Mesa(this),new Mesa(this)};

	private Caja caja;
	private Cocina cocina;

	public Inventario getInventario() {
		return inventario;
	}

	private Inventario inventario = new Inventario();


	public Tienda() {
		caja = new Caja(this);
		cocina = new Cocina(this);
		//inventario.comprarAutomatico();
	}

	public Caja getCaja() {
		return caja;
	}

	public Cocina getCocina() {
		return cocina;
	}



	public void ocuparMesa(int nroMesa){
		if(!mesas[nroMesa].esOcupado()) {
			Boleta boleta = caja.abrirBoleta(nroMesa);
			mesas[nroMesa] = new Mesa(this);
			mesas[nroMesa].setBoleta(boleta);
			mesas[nroMesa].setOcupado(true);
		}
	}

	public void comprarAutomatico(){
		double gasto = calcularGastoAutomatico();
		for(Ingredientes i:Ingredientes.values()) {
			i.setCantidad(i.getMax());
		}
		caja.hacer_Egreso(gasto);
	}

	private double calcularGastoAutomatico() {
		double total=0;
		double gasto;
		for(Ingredientes i:Ingredientes.values()){
			gasto=(i.getPrecio_compra_min()*(i.getMax()-i.getCantidad()))/i.getMinimo();
			total+=gasto;
		}
		return total;
	}


	public void comprarPersonalizado(double[] pedido) {
		for(Ingredientes i:Ingredientes.values()){
			i.setCantidad(i.getCantidad()+pedido[i.ordinal()]);
		}
		double gasto = calcularGastoPersonalizado(pedido);
		caja.hacer_Egreso(gasto);
	}

	private double calcularGastoPersonalizado(double[] pedido) {
		double total=0;
		double gasto;
		for(Ingredientes i:Ingredientes.values()){
			gasto=(i.getPrecio_compra_min()*pedido[i.ordinal()])/i.getMinimo();
			total+=gasto;
		}
		return total;
	}



	public Mesa getMesa(int nroMesa) {
		return mesas[nroMesa];
	}

    public void guardarDatos() {
		Datos datos = new Datos();
		datos.guardarInventario(inventario);
		datos.guardarBalance();
		System.out.println("Fin del programa");
    }
}