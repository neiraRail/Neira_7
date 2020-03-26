package datos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LectorJsonTest {
    private static String TEXTO= "{Boletas:[{id:1,fecha:4,consumo:[1300,200,4500],total:6000},{id:2,fecha:marzo,consumo:[2000,3000],total:5000},{id:3,fecha:mayo,consumo:[2000,3000,1200,3800],total:10000}]}";
    private static String TEXTO2="{Boletas:[{id:1,fecha:4,consumo:[1300,200,4500],total:6000}]}";
    private static String TEXTO3="{\"Boletas\":[]}";
    static ArrayList<String> ListaBoletasEsperada;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void separaBoletas() {
        LectorJson lector = new LectorJson("datos/Talonario.json");
        String texto=lector.leer_Archivo();

        ArrayList<String> ListaBoletas = LectorJson.separaBoletas(TEXTO);
        ArrayList<String> ListaBoletas2 = LectorJson.separaBoletas(TEXTO2);
        ArrayList<String> ListaBoletas3 = LectorJson.separaBoletas(texto);
        System.out.println("mmmm: "+ListaBoletas3.get(0));
       
        assertEquals(ListaBoletas.get(2),"id:3,fecha:mayo,consumo:[2000,3000,1200,3800],total:10000");
        assertEquals(ListaBoletas2.get(0),"id:1,fecha:4,consumo:[1300,200,4500],total:6000");
        assertEquals(4,ListaBoletas3.size());
    }

    @Test
    public void obtenerId() {

    }

    @Test
    public void buscarBoleta() {
        String boleta1 = LectorJson.buscarBoleta(1,LectorJson.separaBoletas(TEXTO));
        String boleta1Esperada="id:1,fecha:4,consumo:[1300,200,4500],total:6000";
        assertEquals(boleta1,boleta1Esperada);
    }

    @Test
    public void leer_Archivo() {
    }
}