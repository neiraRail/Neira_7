package interaccionUsuario;

import contextoProblema.Tienda;
import contextoProblema.TipoPlato;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Ventana extends JFrame {
    private Tienda tienda = new Tienda();

    public Ventana(){
        tienda.comprarAutomatico();
    }

    public void iniciar_Vista_Mesas() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setTitle("Tienda - Vista Mesero");
        JPanel panel1 = new JPanel(new GridLayout(2,2));

        JButton button1 = new JButton("Mesa 1");
        JButton button2 = new JButton("Mesa 2");
        JButton button3 = new JButton("Mesa 3");
        JButton button4 = new JButton("Mesa 4");


        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);

        this.getContentPane().add(panel1);

        this.setVisible(true);


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
        button1.addMouseListener(mouseListener);
        button2.addMouseListener(mouseListener);
        button3.addMouseListener(mouseListener);
        button4.addMouseListener(mouseListener);
    }






    private void ventana_Mesa(int nroMesa) {
        System.out.println("MEsa"+nroMesa);
        if(tienda.getMesa(nroMesa).esOcupado()&&tienda.getMesa(nroMesa).getBoleta().getTotal()!=0){
            ventana_Mesa_Ocupada(nroMesa);
        }
        else {
            tienda.ocuparMesa(nroMesa);
            ventana_Mesa_Libre(nroMesa);
        }
    }



    private void ventana_Mesa_Libre(int nroMesa) {
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Mesa "+(nroMesa+1));

        JPanel panel2 = new JPanel();

        JTextArea area = new JTextArea();
        area.setSize(100,200);
        area.setText("Consumo: ");
        area.setEditable(false);

        JButton agregar_Plato = new JButton("Agregar Plato");

        panel2.add(agregar_Plato);

        frame.getContentPane().add(area,BorderLayout.CENTER);
        frame.getContentPane().add(panel2,BorderLayout.EAST);

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                ventana_Pedido(nroMesa,frame);


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
        agregar_Plato.addMouseListener(mouseListener);
        frame.setVisible(true);
    }

    private void ventana_Mesa_Ocupada(int nroMesa) {
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Mesa "+(nroMesa+1));


        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));


        JTextArea area = new JTextArea();
        area.setSize(100,200);
        area.setText("Consumo: \n"+tienda.getMesa(nroMesa).consumoString()+"\nTotal: "+tienda.getMesa(nroMesa).getBoleta().getTotal());
        area.setEditable(false);


        JButton agregar_Plato = new JButton("Agregar Plato");
        JButton cancelar_Cuenta = new JButton("Cancelar Cuenta");

        panel2.add(agregar_Plato);
        panel2.add(cancelar_Cuenta);


        frame.getContentPane().add(area,BorderLayout.CENTER);
        frame.getContentPane().add(panel2,BorderLayout.EAST);

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getSource().equals(agregar_Plato)){
                    ventana_Pedido(nroMesa,frame);
                }
                else {
                    tienda.getCaja().emitirBoleta(nroMesa);
                    boleta_Emitida();
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

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                frame.dispose();
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
                        tienda.getMesa(nroMesa).hacerPedido(i);
                        frame.dispose();
                        frameAnt.dispose();
                        ventana_Mesa_Ocupada(nroMesa);
                    }
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
        for(int i=0;i<5;i++){
            botones_platos[i].addMouseListener(mouseListener);
        }
    }


}
