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

	public Boleta(int nroBoleta, ArrayList<TipoPlato> consumo){
		this.nroID=nroBoleta;
		this.nroMesa=0;
		this.consumo=consumo;
	}


    public ArrayList<TipoPlato> getConsumo() {
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
		StringBuilder boleta = new StringBuilder();
		boleta.append("Fecha: ").append(getFecha()).append(" N°: ");
		boleta.append(nroID).append("\n");
		for (TipoPlato tipoPlato : consumo) {
		  boleta.append(tipoPlato.toString()).append("\n");
		}
		boleta.append("Total:").append(this.getTotal());

		return boleta.toString();
	}

	public String toCSV() {
		return getNroID() + "," + getFecha() + "," + tienda.getMesa(nroMesa).consumoCSV() + this.getTotal();
	}

	public String toJSON(){
		return "{"+"\"id\":"+getNroID()+","+"\"fecha\":\""+getFecha()+"\","+"\"consumo\":["+consumoToJson()+"],\"total\":"+getTotal()+"}";


	}


	private String consumoToJson(){
		StringBuilder str= new StringBuilder();
		ArrayList <TipoPlato> consumo = getConsumo();
		for (int i=0; i<consumo.size();i++){
			str.append(String.valueOf(consumo.get(i).getPrecio()));
			if(i!=consumo.size()-1){
				str.append(",");
			}
		}

		return str.toString();

	}
}