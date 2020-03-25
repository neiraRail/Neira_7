package contextoProblema;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoletaTest {

    @Before
    public void setUp() throws Exception {
        Tienda tienda= new Tienda();
        Mesa mesa1= new Mesa(tienda);
        mesa1.hacerPedido(1);
        mesa1.hacerPedido(2);
        Boleta boleta1 = mesa1.getBoleta();
        System.out.println(boleta1.toString());

    }

    @Test
    public void getConsumo() {

    }

    @Test
    public void getTotal() {
    }

    @Test
    public void getNroID() {
    }

    @Test
    public void testToString() {

    }

    @Test
    public void toCSV() {
    }

    @Test
    public void toJSON() {
    }
}