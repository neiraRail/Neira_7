package contextoProblema;

import datos.ControlCSV;

import javax.naming.ldap.Control;
import java.util.ArrayList;


public class Tienda {

	private Mesa[] mesas = {new Mesa(this),new Mesa(this),new Mesa(this),new Mesa(this)};
	private Inventario inventario;
	private Caja caja;
	private Cocina cocina;

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

	public String getInventario(){
		StringBuilder stringBuilder=new StringBuilder();
		for(Inventario i:Inventario.values()){
			stringBuilder.append(i.name()).append(": ").append(i.getCantidad()).append("\n");
		}
		return stringBuilder.toString();
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
		for(Inventario i:Inventario.values()) {
			i.setCantidad(i.getMax());
		}
		caja.hacer_Egreso(gasto);
	}

	private double calcularGastoAutomatico() {
		double total=0;
		double gasto;
		for(Inventario i:Inventario.values()){
			gasto=(i.getPrecio_compra_min()*(i.getMax()-i.getCantidad()))/i.getMinimo();
			total+=gasto;
		}
		return total;
	}

	public void comprarPersonalizado(double[] pedido) {
		for(Inventario i:Inventario.values()){
			i.setCantidad(i.getCantidad()+pedido[i.ordinal()]);
		}
		double gasto = calcularGastoPersonalizado(pedido);
		caja.hacer_Egreso(gasto);
	}

	private double calcularGastoPersonalizado(double[] pedido) {
		double total=0;
		double gasto;
		for(Inventario i:Inventario.values()){
			gasto=(i.getPrecio_compra_min()*pedido[i.ordinal()])/i.getMinimo();
			total+=gasto;
		}
		return total;
	}



	public Mesa getMesa(int nroMesa) {
		return mesas[nroMesa];
	}
}