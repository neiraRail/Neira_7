package datos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LectorJsonTest {
    static String TEXTO= "{Boletas:[{id:1,fecha:4,consumo:[1300,200,4500],total:6000},{id:2,fecha:marzo,consumo:[2000,3000],total:5000},{id:3,fecha:mayo,consumo:[2000,3000,1200,3800],total:10000}]}";
    static ArrayList<String> ListaBoletasEsperada;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void separaBoletas() {
        ArrayList<String> ListaBoletas = LectorJson.separaBoletas(TEXTO);
       
        assertEquals(ListaBoletas.get(2),"id:3,fecha:mayo,consumo:[2000,3000,1200,3800],total:10000");
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