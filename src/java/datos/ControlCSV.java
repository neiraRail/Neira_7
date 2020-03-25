package datos;

import contextoProblema.Boleta;
import contextoProblema.Inventario;

import java.io.*;

public class ControlCSV {

	public ControlCSV() {
	}

	void imprimirInventario(Inventario inventario){
		try {
			File file = new File("datos/Inventario.csv");
			FileWriter fw = new FileWriter(file,false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(inventario.toCSV());
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void imprimirBoleta(Boleta boletActual) {
		try {
			File file = new File("Boleta_"+boletActual.getNroID()+".csv");
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("\n"+boletActual.toCSV());
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String leerArchivo(String ruta){
		BufferedReader br = null;
		StringBuilder text = new StringBuilder();
		try {
			File file = new File(ruta);
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String sCadena;
			while((sCadena = br.readLine())!=null){
				text.append(sCadena+" ");
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return text.toString();
	}


	public String[] leerPassword(String ruta) {
		String[] pass;
		String passes = leerArchivo(ruta);
		pass = passes.split(",");
		for(int i=0;i<pass.length;i++){
			pass[i]=pass[i].split("\"")[1];
		}
		return pass;
	}

	public double[] leerInventario(String ruta) {
		String inventariocsv = leerArchivo(ruta);
		String[] inventario = inventariocsv.split(" ");
		double[] cantidades = new double[inventario.length];
		for(int i=0;i<inventario.length;i++){
			cantidades[i] = Double.parseDouble(inventario[i].split(",")[1]);
		}
		return cantidades;

	}
}