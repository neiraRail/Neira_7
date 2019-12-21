package interaccionUsuario;
import contextoProblema.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	public Menu(){
	}

	public void desplegarMenu(){
		Tienda tienda = new Tienda();
		boolean salir = false;
		int opcion;
		while(!salir) {
			System.out.println("------Menu------");
			System.out.println("1. Vista Admin");
			System.out.println("2. Vista Mesero");
			System.out.println("3. Cerrar");

			opcion= elegirOpcion();
			switch (opcion) {
				case 1:
					vistaAdmin(tienda);
					break;
				case 2:
					vistaMesero(tienda);
					break;
				case 3:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 4");
			}

		}
	}

	private void vistaAdmin(Tienda tienda) {
		int opcion;
		boolean salir=false;
		while (!salir){
			System.out.println("1. Ver Inventario");
			System.out.println("2. Hacer Pedido");
			System.out.println("3. Ver Boletas");
			System.out.println("4. Ver Balance");
			System.out.println("5. Cerrar");

			opcion=elegirOpcion();
			switch (opcion){
				case 1:
					System.out.println(tienda.getInventario());
					break;
				case 2:
					hacer_Pedido(tienda);
					break;
				case 3:
					mostrarTalonario(tienda);
					break;
				case 4:
					mostrar_Balance(tienda);
					break;
				case 5:
					salir=true;
					break;
				default:
					System.out.println("Opcion no valida!");
			}
		}
	}

	private void mostrar_Balance(Tienda tienda) {
		for(Ingreso i:tienda.getCaja().getIngresos()){
			System.out.println(i.toString());
		}
		for(Egreso e:tienda.getCaja().getEgresos()){
			System.out.println(e.toString());
		}
		System.out.println("Total: "+tienda.getCaja().getSaldo());
	}

	private void hacer_Pedido(Tienda tienda) {
		boolean salir = false;
		int opcion;
		while(!salir) {
			System.out.println("------Menu------");
			System.out.println("1. Pedido Automatico");
			System.out.println("2. Pedido Especial");
			System.out.println("3. Atras");

			opcion= elegirOpcion();
			switch (opcion) {
				case 1:
					tienda.comprarAutomatico();
					salir=true;
					break;
				case 2:
					pedido_Especial(tienda);
					salir=true;
					break;
				case 3:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 4");
			}
		}
	}

	private void pedido_Especial(Tienda tienda) {
		Scanner scanner=new Scanner(System.in);
		double[] pedido = new double[10];
		for(Inventario i:Inventario.values()){
			System.out.println("Ingrese la cantidad de "+i.name());
			pedido[i.ordinal()] = scanner.nextInt();
		}
		tienda.comprarPersonalizado(pedido);
	}


	private void mostrarTalonario(Tienda tienda) {
		System.out.println(tienda.getCaja().mostrarTalonario());
	}

	private void vistaMesero(Tienda tienda){
		int opcion;
		boolean salir=false;
		while (!salir){

			mostrar_Mesas(tienda);
			System.out.println("\n5.- Salir");
			System.out.println("Eliga una mesa.");
			opcion=elegirOpcion();
			if(opcion>0 && opcion<5){
				vistaMesa(tienda, opcion-1);
			}
			else if(opcion==5){
				salir=true;
			}
			else{
				System.out.println("Opcion no valida!");
			}
		}
	}

	private void vistaMesa(Tienda tienda, int mesa) {
		if(tienda.getMesa(mesa).esOcupado()){
			vistaMesa_Ocupada(tienda, mesa);
		}
		else {
			vistaMesa_Libre(tienda,mesa);
		}

	}

	private void vistaMesa_Libre(Tienda tienda, int mesa) {
		boolean salir=false;
		while (!salir){
			System.out.println("1. Hacer Pedido");
			System.out.println("2. Salir");
			switch (elegirOpcion()){
				case 1:
					tienda.ocuparMesa(mesa);
					elegir_Platos(tienda,mesa);
				case 2:
					salir=true;
					break;
				default:
					System.out.println("Opcion no valida!");
			}

		}
	}

	private void vistaMesa_Ocupada(Tienda tienda, int mesa) {
		boolean salir=false;
		while (!salir){
			System.out.println(tienda.getMesa(mesa).consumoString());
			System.out.println("Total: "+tienda.getMesa(mesa).getBoleta().getTotal());
			System.out.println("1. Hacer Pedido");
			System.out.println("2. Cancelar Cuenta");
			System.out.println("3. Salir");
			switch (elegirOpcion()){
				case 1:
					elegir_Platos(tienda,mesa);
					salir=true;
					break;
				case 2:
					tienda.getCaja().emitirBoleta(mesa);
					salir=true;
					break;
				case 3:
					salir=true;
					break;
				default:
					System.out.println("Opcion no valida!");
			}

		}

	}

	private void mostrar_Mesas(Tienda tienda) {
		for(int i=0;i<4;i++) {
			if (tienda.getMesa(i).esOcupado())
				System.out.println((i+1)+".- [" + 1 + "]");
			else
				System.out.println((i+1)+".- [" + 0 + "]");
		}
	}

	private void elegir_Platos(Tienda tienda, int nroMesa) {
		System.out.println("Que quiere pedir?");
		boolean salir=false;
		while(!salir) {
			mostrarOpciones();
			int opcion = elegirOpcion();
			if(opcion>0&&opcion<6) {
				tienda.getMesa(nroMesa).hacerPedido(opcion-1);
				System.out.println("Quiere pedir algo mas?");
			}
			else if(opcion==6){
				salir=true;
			}
			else {
				System.out.println("Opcion no valida");
			}
		}
	}

	private void mostrarOpciones() {
		for(int i=0;i<5;i++) {
			System.out.println("[" + (i+1) + "] "+TipoPlato.get(i));
		}
		System.out.println("[6] Salir");
	}

	private int elegirOpcion() {
		int opcion=0;
		try {
			opcion= intentarElegir();
		}
		catch (InputMismatchException e) {
			System.out.println("Debes insertar un número");
			elegirOpcion();
		}
		return opcion;
	}

	private int intentarElegir() {
		Scanner sn = new Scanner(System.in);
		return sn.nextInt();
	}
}