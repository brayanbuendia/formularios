/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package formularioregistro;

import javax.swing.*;
import java.awt.*;

public class FormularioRegistro extends JFrame {
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField correoField;
    private JTextField direccionField;

    public FormularioRegistro() {
        setTitle("Formulario de Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        // Etiquetas
        add(new JLabel("Nombre: "));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Apellido: "));
        apellidoField = new JTextField();
        add(apellidoField);

        add(new JLabel("Correo: "));
        correoField = new JTextField();
        add(correoField);

        add(new JLabel("Dirección: "));
        direccionField = new JTextField();
        add(direccionField);

        // Botón de envío
        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(e -> enviarFormulario());
        add(enviarButton);

        setVisible(true);
    }

    private void enviarFormulario() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String correo = correoField.getText();
        String direccion = direccionField.getText();

        // Aquí puedes hacer lo que desees con los datos del formulario,
        // como guardarlos en una base de datos o mostrarlos en pantalla.
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Correo: " + correo);
        System.out.println("Dirección: " + direccion);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioRegistro());
    }
}
