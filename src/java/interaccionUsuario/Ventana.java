package interaccionUsuario;

import contextoProblema.Tienda;
import contextoProblema.TipoPlato;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Ventana extends JFrame {
    private Tienda tienda = new Tienda();

    // Los cuatro botones correspondientes a cada mesa, cambiar a arraylist
    private JButton button1 = new JButton("Mesa 1 "+tienda.getMesa(0).esOcupado());
    private JButton button2 = new JButton("Mesa 2 "+tienda.getMesa(1).esOcupado());
    private JButton button3 = new JButton("Mesa 3 "+tienda.getMesa(0).esOcupado());
    private JButton button4 = new JButton("Mesa 4 "+tienda.getMesa(0).esOcupado() );

    public Ventana(){
        tienda.comprarAutomatico();
    }



    public void iniciar_Vista_Mesas() {
        // Cuando la ventana cierre, el programa terminará
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Tamaño de la ventana
        setSize(500,500);
        // La ventana se colocará en el centro de la pantalla.
        setLocationRelativeTo(null);
        // Titulo de la pantalla
        setTitle("Tienda - Vista Mesero");

        // Panel principal, dividido en 4
        JPanel panel1 = new JPanel(new GridLayout(2,2));


        // Añadir los botones al panel
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        // Añadir lo anterior al frame
        this.getContentPane().add(panel1);
        // Hacer la ventana visible
        this.setVisible(true);

        // Gestion de evento click del mouse
        MouseListener mouseListener=new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getSource().equals(button1)){
                    ventana_Mesa(0);
                }
                if(mouseEvent.getSource().equals(button2)){
                    ventana_Mesa(1);
                }
                if(mouseEvent.getSource().equals(button3)){
                    ventana_Mesa(2);
                }
                if(mouseEvent.getSource().equals(button4)){
                    ventana_Mesa(3);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }
            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };
        // Añadir comportamiento a todos los botones.
        button1.addMouseListener(mouseListener);
        button2.addMouseListener(mouseListener);
        button3.addMouseListener(mouseListener);
        button4.addMouseListener(mouseListener);
    }






    private void ventana_Mesa(int nroMesa) {
        // Print para monitorear por consola
        System.out.println("Mesa"+nroMesa);

        if(tienda.getMesa(nroMesa).esOcupado()&&tienda.getMesa(nroMesa).getBoleta().getTotal()!=0){
            //Si la mesa está ocupada, se abre una ventana que corresponde
            ventana_Mesa_Ocupada(nroMesa);
        }
        else {
            //Si la mesa esta libre se abre una ventana acorde
            ventana_Mesa_Libre(nroMesa);
        }
    }



    private void ventana_Mesa_Libre(int nroMesa) {
        // Se crea y se ajusta la ventana
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Mesa "+(nroMesa+1));
        // Panel principal
        JPanel panel2 = new JPanel();
        // Area donde se muestra el consumo
        JTextArea area = new JTextArea();
        area.setSize(100,200);
        area.setText("Consumo: ");
        area.setEditable(false);
        // Boton para agregar un pedido
        JButton agregar_Plato = new JButton("Agregar Plato");

        // Se agrega el boton al panel y el panel al frame
        panel2.add(agregar_Plato);
        frame.getContentPane().add(area,BorderLayout.CENTER);
        frame.getContentPane().add(panel2,BorderLayout.EAST);

        // Gestion del evento click del mouse
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                ventana_Pedido(nroMesa,frame);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {            }
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {            }
            @Override
            public void mouseExited(MouseEvent mouseEvent) {            }
        };

        agregar_Plato.addMouseListener(mouseListener);
        frame.setVisible(true);
    }

    private void ventana_Mesa_Ocupada(int nroMesa) {
        //Texto que representa el consumo de la mesa
        String consumo = tienda.getMesa(nroMesa).consumoString()+"\nTotal: "+tienda.getMesa(nroMesa).getBoleta().getTotal();

        // Se crea y se ajusta la ventana
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Mesa "+(nroMesa+1));

        //Panel principal
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));

        // Area de texto donde van los pedidos
        JTextArea area = new JTextArea();
        area.setSize(100,200);
        area.setText("Consumo: \n"+ consumo);
        area.setEditable(false);

        //Los dos botones agregar plato y pagar la cuenta
        JButton agregar_Plato = new JButton("Agregar Plato");
        JButton cancelar_Cuenta = new JButton("Cancelar Cuenta");

        //Se agregan los botones al panel y el panel al frame
        panel2.add(agregar_Plato);
        panel2.add(cancelar_Cuenta);
        frame.getContentPane().add(area,BorderLayout.CENTER);
        frame.getContentPane().add(panel2,BorderLayout.EAST);

        //Gestion del evento click del mouse
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getSource().equals(agregar_Plato)){
                    // Se abre una nueva ventana para hacer el pedido.
                    ventana_Pedido(nroMesa,frame);
                }
                else {
                    // Se emite una boleta en formato csv
                    tienda.getCaja().emitirBoleta(nroMesa);
                    // Aviso de boleta emitida
                    boleta_Emitida();
                    // Se cierra esta ventana
                    frame.dispose();
                }
            }
            @Override
            public void mousePressed(MouseEvent mouseEvent) {}
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {}
            @Override
            public void mouseExited(MouseEvent mouseEvent) {}
        };
        //Se añade el comportamiento a los botones
        agregar_Plato.addMouseListener(mouseListener);
        cancelar_Cuenta.addMouseListener(mouseListener);

        frame.setVisible(true);
    }

    private void boleta_Emitida() {
        JFrame frame = new JFrame("Aviso");
        frame.setSize(250,100);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Boleta Emitida Correctamente");
        frame.add(label,BorderLayout.NORTH);
        frame.add(new JButton("OK"),BorderLayout.SOUTH);
        frame.setVisible(true);
        actualizar_MainButtons();

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {            }
        };

        frame.getContentPane().getComponents()[1].addMouseListener(mouseListener);
    }

    private void ventana_Pedido(int nroMesa, JFrame frameAnt) {
        JFrame frame = new JFrame();

        frame.setLocationRelativeTo(null);

        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JButton[] botones_platos = new JButton[5];
        for(TipoPlato plato:TipoPlato.values()){
            botones_platos[plato.ordinal()]=new JButton();
            botones_platos[plato.ordinal()].setText(plato.toString());
        }

        for(int i =0;i<5;i++){
            panel.add(botones_platos[i]);
        }

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        //Gestion de Eventos

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                for(int i=0;i<5;i++){
                    if(mouseEvent.getSource().equals(botones_platos[i])){
                        tienda.ocuparMesa(nroMesa);
                        actualizar_MainButtons();
                        tienda.getMesa(nroMesa).hacerPedido(i);
                        frame.dispose();
                        frameAnt.dispose();
                        ventana_Mesa_Ocupada(nroMesa);
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent mouseEvent) {            }
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {            }
        };

        for(int i=0;i<5;i++){
            botones_platos[i].addMouseListener(mouseListener);
        }
    }

    private void actualizar_MainButtons() {
        button1.setText("Mesa 1 "+tienda.getMesa(0).esOcupado());
        button2.setText("Mesa 2 "+tienda.getMesa(1).esOcupado());
        button3.setText("Mesa 3 "+tienda.getMesa(2).esOcupado());
        button4.setText("Mesa 4 "+tienda.getMesa(3).esOcupado());
    }



    public void iniciar_vista_administrador(){
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Vista Administrador");

        JPanel panel = new JPanel(new GridLayout(0,1));
        JButton Boton1 = new JButton("Ver inventario");
        JButton Boton2 = new JButton("Buscar Bolet");
        JButton Boton3 = new JButton("Ver Balance");
        JButton Boton4 = new JButton("Comprar");

        panel.add(Boton1);
        panel.add(Boton2);
        panel.add(Boton3);
        panel.add(Boton4);

        // Gestion de evento click del mouse
        MouseListener mouseListener=new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getSource().equals(Boton1)){
                    vista_ver_Inventario();
                }
                if(mouseEvent.getSource().equals(Boton2)){
                    vista_buscar_Boleta();
                }
                if(mouseEvent.getSource().equals(Boton3)){
                    vista_ver_Balance();
                }
                if(mouseEvent.getSource().equals(Boton4)){
                    
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }
            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };
        // Añadir comportamiento a todos los botones.
        Boton1.addMouseListener(mouseListener);
        Boton2.addMouseListener(mouseListener);
        Boton3.addMouseListener(mouseListener);
        Boton4.addMouseListener(mouseListener);

        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }

    private void vista_ver_Balance() {
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Balance");
        frame.setLayout(new GridLayout(1,1));

        JTextArea balance = new JTextArea("a\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na");
        balance.setEditable(false);

        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(balance);

        frame.getContentPane().add(scroll);

        frame.setVisible(true);
    }

    private void vista_ver_Inventario() {
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Inventario");
        frame.setLayout(new GridLayout(1,1));

        JTextArea inventario = new JTextArea("a\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na");
        inventario.setEditable(false);

        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(inventario);

        frame.getContentPane().add(scroll);

        frame.setVisible(true);
    }

    private void vista_buscar_Boleta(){
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Buscar Boleta");
        frame.setLayout(new GridLayout(2,1));

        JPanel panel = new JPanel();
        JTextField vista_Boleta = new JTextField();

        JLabel label1 = new JLabel("Numero de Boleta:");
        JTextField buscador = new JTextField(10);
        JButton botonBuscar = new JButton("Buscar");

        panel.add(label1);
        panel.add(buscador);
        panel.add(botonBuscar);

        frame.getContentPane().add(panel);
        frame.getContentPane().add(vista_Boleta);

        frame.setVisible(true);


    }
}
