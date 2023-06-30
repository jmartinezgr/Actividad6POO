package Interfaz;

import Operaciones.AddFriend;
import Operaciones.DeleteFriend;
import Operaciones.DisplayFriends;
import Operaciones.UpdateFriend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Interfaz extends JFrame implements ActionListener{
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem createItem,readItem, updateItem,deleteItem;
    private Container contenedor;
    private JLabel nombreAmigoC,numeroAmigoC,nombreAmigoR,numeroAmigoR,informacion;
    private JTextField nombreAmigoCF,numeroAmigoCF;
    private AddFriend addFriend;
    private DisplayFriends displayFriends;
    private DeleteFriend deleteFriend;
    private UpdateFriend updateFriend;
    private JButton crear,mostrar,update,delete;
    private JList<String> myList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;

    public Interfaz() {
        setTitle("CRUD-ADD");
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
        displayFriends = new DisplayFriends();
        updateFriend = new UpdateFriend();
        deleteFriend = new DeleteFriend();

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
        crear.setBounds(180,160,100,23);
        crear.addActionListener(this);
        contenedor.add(crear);

        listModel = new DefaultListModel<>();
        myList = new JList<>(listModel);

        scrollPane = new JScrollPane(myList);
        scrollPane.setBounds(10, 10, 200, 270);
        contenedor.add(scrollPane);
        myList.setVisible(false);
        scrollPane.setVisible(false);

        informacion = new JLabel("Informacion");
        informacion.setBounds(295,10,100,23);
        informacion.setVisible(false);
        contenedor.add(informacion);

        nombreAmigoR = new JLabel("Nombre: ");
        nombreAmigoR.setBounds(230,50,200,23);
        nombreAmigoR.setVisible(false);
        contenedor.add(nombreAmigoR);

        numeroAmigoR = new JLabel("Numero: ");
        numeroAmigoR.setBounds(230,100,200,23);
        numeroAmigoR.setVisible(false);
        contenedor.add(numeroAmigoR);

        mostrar = new JButton("Mostrar Informacion");
        mostrar.addActionListener(this);
        mostrar.setBounds(220,180,200,23);
        mostrar.setVisible(false);
        contenedor.add(mostrar);

        update = new JButton("Actualizar");
        update.addActionListener(this);
        update.setBounds(180,160,100,23);
        update.setVisible(false);
        contenedor.add(update);

        delete = new JButton("Eliminar");
        delete.addActionListener(this);
        delete.setBounds(280,130,100,23);
        delete.setVisible(false);
        contenedor.add(delete);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==crear){
            String nombre = nombreAmigoCF.getText();
            String numero = numeroAmigoCF.getText();
            setTitle("CRUD-ADD");
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
            update.setVisible(false);
            numeroAmigoCF.setText("");
            nombreAmigoCF.setText("");

            delete.setVisible(false);
            myList.setVisible(false);
            scrollPane.setVisible(false);
            informacion.setVisible(false);
            nombreAmigoR.setVisible(false);
            numeroAmigoR.setVisible(false);
            mostrar.setVisible(false);

            setTitle("CRUD-ADD");

        }else if(e.getSource() == readItem){
            setTitle("CRUD-READ");
            nombreAmigoC.setVisible(false);
            nombreAmigoCF.setVisible(false);
            numeroAmigoC.setVisible(false);
            numeroAmigoCF.setVisible(false);
            crear.setVisible(false);
            update.setVisible(false);

            myList.setVisible(true);
            scrollPane.setVisible(true);
            informacion.setVisible(true);
            nombreAmigoR.setVisible(true);
            nombreAmigoR.setText("Nombre: ");
            numeroAmigoR.setText("Numero: ");
            numeroAmigoR.setVisible(true);
            mostrar.setVisible(true);
            delete.setVisible(false);

            cargarInfo();
        } else if (e.getSource() == mostrar) {
            String nombre = myList.getSelectedValue();
            String telefono = displayFriends.obtenerTelefono(nombre);
            nombreAmigoR.setText("Nombre: "+nombre);
            numeroAmigoR.setText("Numero: "+telefono);
            setTitle("CRUD-READ");
        }else if(e.getSource() == updateItem){
            setTitle("CRUD-UPDATE");
            nombreAmigoC.setVisible(true);
            nombreAmigoCF.setVisible(true);
            numeroAmigoC.setVisible(true);
            numeroAmigoCF.setVisible(true);
            update.setVisible(true);
            crear.setVisible(false);

            myList.setVisible(false);
            scrollPane.setVisible(false);
            informacion.setVisible(false);
            nombreAmigoR.setVisible(false);
            numeroAmigoR.setVisible(false);
            mostrar.setVisible(false);
            delete.setVisible(false);
        } else if (e.getSource() == update) {
            String nombre = nombreAmigoCF.getText();
            String numero = numeroAmigoCF.getText();
            if(updateFriend.updateFriend(nombre,numero)){
                JOptionPane.showMessageDialog(this,nombre+ " ha sido actualizo con exito!", "Actualizacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this,"No existe un amigo con esas credenciales", "Error", JOptionPane.ERROR_MESSAGE);
            }
            numeroAmigoCF.setText("");
            nombreAmigoCF.setText("");
            setTitle("CRUD-UPDATE");
        } else if (e.getSource() == deleteItem) {
            setTitle("CRUD-DELETE");

            myList.setVisible(true);
            scrollPane.setVisible(true);
            delete.setVisible(true);

            informacion.setVisible(false);
            nombreAmigoR.setVisible(false);
            numeroAmigoR.setVisible(false);
            mostrar.setVisible(false);
            nombreAmigoC.setVisible(false);
            nombreAmigoCF.setVisible(false);
            numeroAmigoC.setVisible(false);
            numeroAmigoCF.setVisible(false);
            update.setVisible(false);
            crear.setVisible(false);

            cargarInfo();
        } else if (e.getSource() == delete) {
            String nombre = myList.getSelectedValue();
            if(deleteFriend.eliminarAmigo(nombre)){
                JOptionPane.showMessageDialog(this,nombre+ " ha sido eliminado!", "Actualizacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
                cargarInfo();
            }else{
                JOptionPane.showMessageDialog(this,"No se ha podido eliminar el amigo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void cargarInfo(){
        listModel.clear();
        ArrayList<String> listaAmigos= displayFriends.listaAmigos();
        String nombre;
        for (String amigo : listaAmigos) {
            nombre = amigo.split("!")[0];
            listModel.addElement(nombre);
        }
    }
}
