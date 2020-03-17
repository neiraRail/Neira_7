package contextoProblema;

import java.util.ArrayList;
import java.util.Calendar;

public class Boleta {
	private int nroID;
	private int nroMesa;
	private Tienda tienda;
	private Calendar fecha=Calendar.getInstance();
    private ArrayList<TipoPlato> consumo= new ArrayList<>();



	Boleta(int nroBoletas,Tienda tienda,int nroMesa) {
		this.nroID=nroBoletas;
		this.tienda=tienda;
		this.nroMesa=nroMesa;
	}

    ArrayList<TipoPlato> getConsumo() {
        return consumo;
    }

	public double getTotal() {
		double total=0;
		for(TipoPlato plato:consumo){
			total+=plato.getPrecio();
		}
		return total;
	}

	public int getNroID() {
		return nroID;
	}

	private String getFecha(){
		int year       = fecha.get(Calendar.YEAR);
		int month      = fecha.get(Calendar.MONTH); // Jan = 0, dec = 11
		int dayOfMonth = fecha.get(Calendar.DAY_OF_MONTH);
		return dayOfMonth+"/"+month+"/"+year;
	}

	public String toString(){
		return "Fecha: "+getFecha()+ " N°: "+nroID+
				"\n"+consumo+
				"Total:"+this.getTotal();
	}

	public String toCSV() {
		return getNroID()+","+getFecha()+","+tienda.getMesa(nroMesa).consumoCSV()+this.getTotal();
	}
	public String toJSON(){return "{"+"id:"+getNroID()+","+"fecha:"+getFecha()+","+"consumo:["+consumoToJson()+"],total:"+getTotal()+"}";}

	private String consumoToJson(){
		String str="";
		ArrayList <TipoPlato> consumo = getConsumo();
		for (int i=0; i<consumo.size();i++){
			str= str+String.valueOf(consumo.get(i).getPrecio())+",";
		}

		return str;
	}
}