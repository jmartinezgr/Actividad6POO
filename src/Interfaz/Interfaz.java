package Interfaz;

import Operaciones.AddFriend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame implements ActionListener{
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem createItem,readItem, updateItem,deleteItem;
    private Container contenedor;

    private JLabel nombreAmigoC,numeroAmigoC;
    private JTextField nombreAmigoCF,numeroAmigoCF;
    private AddFriend addFriend;
    private JButton crear;

    public Interfaz() {
        setTitle("CRUD");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Archivo");

        createItem = new JMenuItem("Crear");
        createItem.addActionListener(this);

        readItem = new JMenuItem("Leer");
        readItem.addActionListener(this);

        updateItem = new JMenuItem("Actualizar");
        updateItem.addActionListener(this);

        deleteItem = new JMenuItem("Borrar");
        deleteItem.addActionListener(this);

        fileMenu.add(createItem);
        fileMenu.add(readItem);
        fileMenu.add(updateItem);
        fileMenu.add(deleteItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        setVisible(true);

        addFriend= new AddFriend();

        inicio();
    }

    public void inicio(){
        contenedor = getContentPane();
        contenedor.setLayout(null);

        nombreAmigoC = new JLabel("Nombre:");
        nombreAmigoC.setBounds(100,60,80,23);
        contenedor.add(nombreAmigoC);

        nombreAmigoCF = new JTextField();
        nombreAmigoCF.setBounds(190,60,150,23);
        contenedor.add(nombreAmigoCF);

        numeroAmigoC = new JLabel("Numero:");
        numeroAmigoC.setBounds(100,100,80,23);
        contenedor.add(numeroAmigoC);

        numeroAmigoCF = new JTextField();
        numeroAmigoCF.setBounds(190,100,150,23);
        contenedor.add(numeroAmigoCF);

        crear = new JButton("Crear");
        crear.setBounds(170,160,100,23);
        crear.addActionListener(this);
        contenedor.add(crear);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==crear){
            String nombre = nombreAmigoCF.getText();
            String numero = numeroAmigoCF.getText();
            if(addFriend.crearAmigo(nombre,numero)){
                JOptionPane.showMessageDialog(this,nombre+ " ha sido creado con exito!", "Creacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
                nombreAmigoCF.setText("");
                numeroAmigoCF.setText("");
            }else{
                JOptionPane.showMessageDialog(this,"Al parecer estos datos ya estan registrados", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == createItem) {
            nombreAmigoC.setVisible(true);
            nombreAmigoCF.setVisible(true);
            nombreAmigoCF.setText("");
            numeroAmigoC.setVisible(true);
            numeroAmigoCF.setText("");
            numeroAmigoCF.setVisible(true);
            crear.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interfaz::new);
    }

}
