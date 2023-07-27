/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package formularioproductos;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FormularioProductos extends JFrame {
    private JTextField nombreCompletoField;
    private JTextField nombreProductoField;
    private JTextField cantidadField;
    private JTextField direccionField;
    private JComboBox<String> metodoPagoComboBox;
    private JComboBox<String> categoriaComboBox;
    private JLabel mensajeLabel;

    public FormularioProductos() {
        setTitle("Formulario de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2));

        // Etiquetas
        add(new JLabel("Nombre Completo: "));
        nombreCompletoField = new JTextField();
        add(nombreCompletoField);

        add(new JLabel("Nombre del Producto: "));
        nombreProductoField = new JTextField();
        add(nombreProductoField);

        add(new JLabel("Categoría: "));
        String[] opcionesCategoria = {
            "Lácteos",
            "Limpieza y Aseo Personal",
            "Bebidas",
            "Alimentos y Despensa",
            "Congelados",
            "Bebé",
            "Mascotas",
            "Otros"
        };
        categoriaComboBox = new JComboBox<>(opcionesCategoria);
        add(categoriaComboBox);

        add(new JLabel("Cantidad: "));
        cantidadField = new JTextField();
        add(cantidadField);

        add(new JLabel("Dirección: "));
        direccionField = new JTextField();
        add(direccionField);

        add(new JLabel("Método de Pago: "));
        String[] opcionesPago = {"Bancolombia", "DaviPlata", "Banco Agrario"};
        metodoPagoComboBox = new JComboBox<>(opcionesPago);
        add(metodoPagoComboBox);

        // Botón de envío
        JButton enviarButton = new JButton("Guardar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarInformacion();
            }
        });
        add(enviarButton);

        // Etiqueta para mostrar mensajes
        mensajeLabel = new JLabel("");
        mensajeLabel.setForeground(Color.RED);
        add(mensajeLabel);

        setVisible(true);
    }

    private void guardarInformacion() {
        String nombreCompleto = nombreCompletoField.getText();
        String nombreProducto = nombreProductoField.getText();
        int cantidad;

        try {
            cantidad = Integer.parseInt(cantidadField.getText());
        } catch (NumberFormatException e) {
            mensajeLabel.setText("La cantidad debe ser un número entero.");
            return;
        }

        if (cantidad <= 0 || cantidad > 30) {
            mensajeLabel.setText("La cantidad debe estar entre 1 y 30.");
            return;
        }

        String direccion = direccionField.getText();
        String metodoPago = (String) metodoPagoComboBox.getSelectedItem();
        String categoria = (String) categoriaComboBox.getSelectedItem();

        // Guardar en la base de datos
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Establecer la conexión con la base de datos (modifica los datos de conexión según tu configuración)
            String url = "jdbc:mysql://localhost:3306/formularioProdcuto";
            String usuario = "root";
            String contraseña = "";
            conn = DriverManager.getConnection(url, usuario, contraseña);

            // Crear la consulta SQL para insertar los datos en la tabla
            String sql = "INSERT INTO productos (nombre_completo, nombre_producto, cantidad, direccion, metodo_pago, categoria) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Asignar los valores de los campos del formulario a la consulta SQL
            stmt.setString(1, nombreCompleto);
            stmt.setString(2, nombreProducto);
            stmt.setInt(3, cantidad);
            stmt.setString(4, direccion);
            stmt.setString(5, metodoPago);
            stmt.setString(6, categoria);

            // Ejecutar la consulta
            stmt.executeUpdate();

            mensajeLabel.setText("Información guardada correctamente en la base de datos.");
        } catch (SQLException ex) {
            mensajeLabel.setText("Error al conectar o guardar los datos en la base de datos.");
            ex.printStackTrace();
        } finally {
            // Cerrar la conexión y liberar recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioProductos());
    }
}
