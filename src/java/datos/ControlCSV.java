package datos;

import contextoProblema.Boleta;
import contextoProblema.Inventario;

import java.io.*;

public class ControlCSV {

	public ControlCSV() {
	}

	public void crearBoleta(Boleta boleta) {
		crearArchivo("Boleta "+boleta.getNroID());
	}

	public void crearArchivo(String nombre) {
		try {
			File file = new File(nombre+".csv");
			if (!file.exists()) {
				file.createNewFile();
			}
			else{
				file.delete();
				file.createNewFile();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public void imprimirInventario(Inventario inventario){
		try {
			File file = new File("Inventario"+".csv");
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


	public String[] leerArchivo(String ruta) {
		String[] pass= null;
		BufferedReader br = null;
		try {
			File file = new File(ruta);
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			pass = br.readLine().split(",");
			for(int i=0;i<pass.length;i++){
				pass[i]=pass[i].split("\"")[1];
			}
			System.out.println(pass[0]+pass[1]);
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
		return pass;
	}

}