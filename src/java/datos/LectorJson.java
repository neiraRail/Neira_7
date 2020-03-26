package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class LectorJson {
    private String ruta;


    LectorJson(String ruta){
        this.ruta=ruta;
    }

    private String intentar_leer_Archivo() throws IOException {
        File file = new File(ruta);
        if (!file.exists()) {
            EscritorJson escritor = new EscritorJson(ruta);
            file.createNewFile();
            escritor.escribir_Texto("{\"Boletas\":[]}");
        }
        StringBuilder texto= new StringBuilder();
        String linea;
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);

        while((linea = br.readLine())!=null) {
            texto.append(linea).append("\n");
        }
        br.close();
        return texto.toString();

    }
    public static ArrayList<String> separaBoletas(String texto){
        String[] boletasplus= texto.split("[\\{\\}]");
        int nro_boletas=(boletasplus.length-2)/2;
        System.out.println();

        ArrayList <String> boletas= new ArrayList<String>();
        for (int i=2; i<boletasplus.length;i=i+2){

            boletas.add(boletasplus[i]);
        }
        System.out.println(boletas.size());
        return boletas;
    }

    public static int obtenerId(String boleta){
        String[] str=boleta.split(",");
        //System.out.println("str = "+str[0]);
        String[] str2 = str[0].split(":");
        //System.out.println("str2 = "+str2[1]);

        return stringAInt(str2[1]);
    }

    private static int stringAInt(String myString){
        int num;
        try {
            num = Integer.parseInt(myString);
        }
        catch (NumberFormatException e)
        {
            num = 0;
        }
        return num;
    }


    public static String buscarBoleta(int id, ArrayList<String> boletas){
        System.out.println("boletas.size para buscar boletas= "+boletas.size()+"ID buscado = "+id);
        for(int i=0; i<boletas.size();i++){
            if (obtenerId(boletas.get(i))==id){
                return boletas.get(i);
            }
        }
        return "";
    }

    public String leer_Archivo() {
        String file = "";
        try {
           file = intentar_leer_Archivo();
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return file;
    }







/*
    private String[] separa_Atributos(String objeto_String) throws IOException{
        String[] atributos = new String[3];
        String[] partes = objeto_String.split(",");
        String[] subParte;
        for(int i=0;i<3;i++){
            subParte=partes[i].split(":");
            atributos[i]=subParte[1].split("\"")[1];
        }
        return atributos;
    } */


    /*
    String obtener_Marca(String objeto_String) throws IOException{
        return separa_Atributos(objeto_String)[0];
    }
    String obtener_Modelo(String objeto_String) throws IOException{
        return separa_Atributos(objeto_String)[1];
    }
    String obtener_Color(String objeto_String) throws IOException{
        return separa_Atributos(objeto_String)[2];
    }

    String[] separar_Objetos() throws IOException{
        String texto=separar_enCorchetes();
        String[] parte = texto.split("[{}]");
        String[] objetos = new String[parte.length/2];
        for(int i=0;i<(parte.length/2);i++){
            objetos[i]=parte[(2*i)+1];
        }
        return objetos;
    }*/
    /*
    private String separar_enCorchetes()throws IOException{
        String texto=leer_Archivo();
        String[] parte = texto.split("[\\[\\]]");
        return parte[1];
    }*/


    /**
     * Metodo que lee un archivo .
     * @throws IOException en caso que la ruta noe existiera o hubiera un error de lectura.
     */

}
