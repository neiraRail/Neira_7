package contextoProblema;

public enum TipoPlato {
	Empanada(1000, new double[]{20, 0, 0, 0, 0, 0, 0, 1, 0,0}),
	Pizza(1500, new double[]{100, 100, 0, 0, 0, 20, 1, 0, 0,0}),
	Churro(800,new double[]{0, 0, 1, 20, 0, 0, 0, 0, 50,0}),
	Papas_Fritas(600,new double[]{0, 0, 0, 0, 500, 0, 0, 0, 100,0}),
	Humita(1200,new double[]{0, 50, 0, 0, 0, 0, 0, 0, 0, 100});

	private double precio;

	private double[] receta;

	private TipoPlato(double precio,double[] receta) {
		this.precio=precio;
		this.receta=receta;
	}

	public double faltante(Ingredientes i){
		return receta[i.ordinal()]-i.getCantidad();
	}

	public double[] getReceta() {
		return receta;
	}

	public double getPrecio(){
		return this.precio;
	}

	public String toString(){
		return name()+": $"+getPrecio();
	}

	public static TipoPlato get(double index, boolean porPrecio){
		if(porPrecio) {
			switch ((int)index) {
				case 1000: return Empanada;
				case 1500: return Pizza;
				case 800: return Churro;
				case 600: return Papas_Fritas;
				case 1200: return Humita;
				default: return null;
			}
		}
		else {
			switch ((int)index) {
				case 0: return Empanada;
				case 1: return Pizza;
				case 2: return Churro;
				case 3: return Papas_Fritas;
				case 4: return Humita;
				default: return null;
			}
		}
	}
}