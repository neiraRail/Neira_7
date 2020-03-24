package datos;

import contextoProblema.Boleta;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EscritorJson {

    private String ruta;

    public EscritorJson(String ruta){
        this.ruta=ruta;
    }

    public void agregarObjeto(Boleta boleta){
        LectorJson lector = new LectorJson(ruta);
        String texto=lector.leer_Archivo();
        String target="]}";
        texto = texto.replaceAll(target,","+"\n  "+boleta.toJSON()+"]}");
        this.escribir_Texto(texto);
    }

    public void escribir_Texto(String mensaje){
        try {
            intentar_Agregar(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("El archivo no fue encontrado en este directorio!!");
        }
    }

    private void intentar_Agregar(String mensaje) throws Exception{
        FileWriter fichero = new FileWriter(ruta);
        PrintWriter pw = new PrintWriter(fichero);
        pw.println(mensaje);
        fichero.close();
    }



}