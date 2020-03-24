package main;
import interaccionUsuario.Menu;
import interaccionUsuario.Ventana;


import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] aArgs) {
		Ventana v1 = new Ventana();
		v1.iniciar_Vista_Mesas();
		v1.iniciar_vista_administrador();
		Menu m = new Menu();
		m.desplegarMenu();


	}
}