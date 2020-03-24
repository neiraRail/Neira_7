package datos;

import contextoProblema.Boleta;
import contextoProblema.Inventario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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
}