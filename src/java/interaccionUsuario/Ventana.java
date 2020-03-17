package interaccionUsuario;


import contextoProblema.Egreso;
import contextoProblema.Ingreso;

import contextoProblema.Tienda;
import contextoProblema.TipoPlato;


import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Ventana extends JFrame {
    private Tienda tienda = new Tienda();


    // Los cuatro botones correspondientes a cada mesa, cambiar a arraylist
    private JButton button1 = new JButton("Mesa 1 ");
    private JButton button2 = new JButton("Mesa 2 ");
    private JButton button3 = new JButton("Mesa 3 ");
    private JButton button4 = new JButton("Mesa 4 ");


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

        //Color verde default
        button1.setBackground(Color.GREEN);
        button2.setBackground(Color.GREEN);
        button3.setBackground(Color.GREEN);
        button4.setBackground(Color.GREEN);



        // Añadir los botones al panel
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        // Añadir lo anterior al frame
        this.getContentPane().add(panel1);
        // Hacer la ventana visible


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

        this.setVisible(true);

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

        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(400,400);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Mesa "+(nroMesa+1));

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

        dialog.getContentPane().add(area,BorderLayout.CENTER);
        dialog.getContentPane().add(panel2,BorderLayout.EAST);


        // Gestion del evento click del mouse
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                ventana_Pedido(nroMesa,dialog);
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

        dialog.setVisible(true);

    }

    private void ventana_Mesa_Ocupada(int nroMesa) {
        //Texto que representa el consumo de la mesa
        String consumo = tienda.getMesa(nroMesa).consumoString()+"\nTotal: "+tienda.getMesa(nroMesa).getBoleta().getTotal();

        // Se crea y se ajusta la ventana

        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(400,400);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Mesa "+(nroMesa+1));


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

        dialog.getContentPane().add(area,BorderLayout.CENTER);
        dialog.getContentPane().add(panel2,BorderLayout.EAST);


        //Gestion del evento click del mouse
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getSource().equals(agregar_Plato)){
                    // Se abre una nueva ventana para hacer el pedido.

                    ventana_Pedido(nroMesa,dialog);

                }
                else {
                    // Se emite una boleta en formato csv
                    tienda.getCaja().emitirBoleta(nroMesa);
                    // Aviso de boleta emitida

                    boleta_Emitida(dialog);
                    // Se cierra esta ventana
                    dialog.dispose();

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


        dialog.setVisible(true);
    }

    private void boleta_Emitida(JDialog frame) {
        JDialog dialog = new JDialog(frame);
        dialog.setModal(true);
        int LAST = tienda.getCaja().getTalonario().size()-1;
        int HEIGHT = tienda.getCaja().getTalonario().get(LAST).getConsumo().size() * 15 + 115;
        dialog.setSize(250,HEIGHT);
        dialog.setLocationRelativeTo(null);
        JLabel label = new JLabel("Boleta Emitida Correctamente");
        JTextArea boleta = new JTextArea(tienda.getCaja().getTalonario().get(LAST).toString());
        boleta.setEditable(false);
        dialog.add(label,BorderLayout.NORTH);
        dialog.add(boleta,BorderLayout.CENTER);


        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                dialog.dispose();

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


        JButton boton_OK = new JButton("OK");
        dialog.add(boton_OK,BorderLayout.SOUTH);
        actualizar_MainButtons();
        boton_OK.addMouseListener(mouseListener);
        dialog.setVisible(true);
    }

    private void ventana_Pedido(int nroMesa, JDialog frameAnt) {
        JDialog dialog = new JDialog(frameAnt);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);

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


        dialog.add(panel);
        dialog.pack();


        //Gestion de Eventos

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                for(int i=0;i<5;i++){
                    if(mouseEvent.getSource().equals(botones_platos[i])){

                        System.out.println("boton presseed");
                        tienda.ocuparMesa(nroMesa);
                        actualizar_MainButtons();
                        tienda.getMesa(nroMesa).hacerPedido(i);
                        dialog.dispose();
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

        dialog.setVisible(true);
    }

    private void actualizar_MainButtons() {
        if(tienda.getMesa(0).esOcupado())
            button1.setBackground(Color.RED);
        else
            button1.setBackground(Color.GREEN);
        if(tienda.getMesa(1).esOcupado())
            button2.setBackground(Color.RED);
        else
            button2.setBackground(Color.GREEN);
        if(tienda.getMesa(2).esOcupado())
            button3.setBackground(Color.RED);
        else
            button3.setBackground(Color.GREEN);
        if(tienda.getMesa(3).esOcupado())
            button4.setBackground(Color.RED);
        else
            button4.setBackground(Color.GREEN);
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
                    vista_comprar_ingredientes();
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

        JTextArea balance = new JTextArea(mostrar_Balance(tienda));
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

        JTextArea inventario = new JTextArea(tienda.getInventario());
        inventario.setEditable(false);

        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(inventario);

        frame.getContentPane().add(scroll);

        frame.setVisible(true);
    }

    private void vista_comprar_ingredientes(){
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Comprar");


        JPanel panel = new JPanel(new GridLayout(1,2));
        JTextArea area = new JTextArea();

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
        JTextArea vista_Boleta = new JTextArea();

        JLabel label1 = new JLabel("Numero de Boleta:");
        JTextField buscador = new JTextField(10);
        JButton botonBuscar = new JButton("Buscar");

        panel.add(label1);
        panel.add(buscador);
        panel.add(botonBuscar);
        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int numero = Integer.parseInt(buscador.getText());
                vista_Boleta.setText(tienda.getCaja().getTalonario().get(numero).toString());
            }
        };

        MouseListener mouseListener=new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int numero = Integer.parseInt(buscador.getText());
                vista_Boleta.setText(tienda.getCaja().getTalonario().get(numero).toString());
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
        botonBuscar.addMouseListener(mouseListener);
        buscador.addActionListener(action);

        frame.getContentPane().add(panel);
        frame.getContentPane().add(vista_Boleta);

        frame.setVisible(true);


    }

    private String mostrar_Balance(Tienda tienda) {
        StringBuilder balance = new StringBuilder();
        for(Ingreso i:tienda.getCaja().getIngresos()){
            balance.append(i.toString()).append("\n");
        }
        for(Egreso e:tienda.getCaja().getEgresos()){
            balance.append(e.toString()).append("\n");
        }
        balance.append("\tTotal: ").append(tienda.getCaja().getSaldo()).append("\n");

        return balance.toString();
    }


}
