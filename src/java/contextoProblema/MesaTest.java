package contextoProblema;

import static org.junit.Assert.*;

public class MesaTest {

    @org.junit.Before
    public void setUp() throws Exception {
        Tienda tienda = new Tienda();
        Mesa mesa1= new Mesa(tienda);
        Boleta boleta1 = mesa1.getBoleta();


    }

    @org.junit.Test
    public void setBoleta() {

    }

    @org.junit.Test
    public void getBoleta() {
    }

    @org.junit.Test
    public void setOcupado() {
    }

    @org.junit.Test
    public void esOcupado() {
    }

    @org.junit.Test
    public void consumoString() {
    }

    @org.junit.Test
    public void consumoCSV() {
    }

    @org.junit.Test
    public void hacerPedido() {
    }
}