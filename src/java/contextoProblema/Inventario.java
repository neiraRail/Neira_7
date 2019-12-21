package contextoProblema;

public enum Inventario {
    QUESO(1000,5000,0,3000),//GRAMOS
    TOMATE(5000,10000,0,2500),//GRAMOS
    CHURRO(10,50,0,3000),//UNIDADES
    MANJAR(2000,5000,0,2000),//MILILITROS
    PAPA(5000,20000,0,2000),//GRAMOS
    SALAME(500,3000,0,4000),//GRAMOS
    PAN_PIZZA(5,20,0,2000),//UNIDADES
    MASA_EMPANADA(10,50,0,1000),//UNIDADES
    ACEITE(2000,10000,0,1500),//MILILITROS
    MAIZ(1000,5000,0,2500);//GRAMOS

    private double minimo;
    private double ideal;
    private double cantidad;
    private double precio_compra_min;

    Inventario(double minimo,double ideal, double cantidad, double precio_compra_min){
        this.minimo=minimo;
        this.ideal=ideal;
        this.cantidad=cantidad;
        this.precio_compra_min=precio_compra_min;
    }

    public double getMinimo() {
        return minimo;
    }

    public double getIdeal(){
        return ideal;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_compra_min() {
        return precio_compra_min;
    }



}
