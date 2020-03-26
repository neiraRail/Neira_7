package datos;

import contextoProblema.Boleta;
import contextoProblema.Inventario;

import java.io.*;

public class ControlCSV {

	public ControlCSV() {
	}
	void escribir(String ruta,String texto){
		try {
			FileWriter fw = new FileWriter(ruta, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(texto);
			bw.close();
		}catch (Exception e){

		}
	}

	void imprimirInventario(Inventario inventario){
		try {
			File file = new File("datos/Inventario.csv");
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file,false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(inventario.toCSV());
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
			if(!file.exists()) {
				file.createNewFile();
			}
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String sCadena;
			while((sCadena = br.readLine())!=null){
				text.append(sCadena).append(" ");
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
		try {
			pass = passes.split(",");
			for (int i = 0; i < pass.length; i++) {
				pass[i] = pass[i].split("\"")[1];
			}
		}catch (ArrayIndexOutOfBoundsException bounds){
			pass =new String[2];
			escribir(ruta,"administrador,pass");
			pass[0]="administrador";
			pass[1]="pass";
		}
		return pass;
	}

	public double[] leerInventario(String ruta) {
		try {
			String inventariocsv = leerArchivo(ruta);
			String[] inventario = inventariocsv.split(" ");
			double[] cantidades = new double[inventario.length];
			for (int i = 0; i < inventario.length; i++) {
				cantidades[i] = Double.parseDouble(inventario[i].split(",")[1]);
			}
			return cantidades;
		}
		catch (ArrayIndexOutOfBoundsException aiobe){

			double[] cantidades = new double[10];
			System.out.println(cantidades[2]);
			return cantidades;
		}
	}
}