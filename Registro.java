import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Registro extends JFrame{
    private JPanel panel2;
    private JTextField historial1;
    private JTextField nombre1;
    private JTextField apellido1;
    private JTextField telefono1;
    private JTextField edad1;
    private JButton guardar_info;
    private JButton ver_info;
    private JTextField descripcion1;
    private JTextField cedula1;

    public Registro(){
        super("Registro");
        setContentPane(panel2);


        guardar_info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
                String user = "root";
                String password = "";

                try (Connection conecta = DriverManager.getConnection(url,user,password)){
                    JOptionPane.showMessageDialog(null,"Conectado a la base de datos","Éxito",JOptionPane.INFORMATION_MESSAGE);

                    String cedula0 = cedula1.getText();
                    String historial0 = historial1.getText();
                    String nombre0 = nombre1.getText();
                    String apellido0 = apellido1.getText();
                    String direccion0 = telefono1.getText();
                    String edad0 = edad1.getText();
                    String descripcion0 = descripcion1.getText();

                    String sql = "insert into paciente(cedula, n_historial_clinico,nombre,apellido,telefono,edad,descripcion_enfermedad) values(?,?,?,?,?,?,?)";

                    PreparedStatement pst = conecta.prepareStatement(sql);
                    pst.setInt(1,Integer.parseInt(cedula0));
                    pst.setString(2,historial0);
                    pst.setString(3,nombre0);
                    pst.setString(4,apellido0);
                    pst.setString(5,direccion0);
                    pst.setInt(6,Integer.parseInt(edad0));
                    pst.setString(7,descripcion0);

                    int afectar_filas = pst.executeUpdate();
                    if (afectar_filas > 0){
                        JOptionPane.showMessageDialog(null,"REGISTRO INSERTADO CORRECTAMENTE","ÉXITO",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"FALLO LA INSERCION DE DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                    pst.close();;
                    conecta.close();
                }
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        ver_info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Busqueda buscar_panel = new Busqueda();
                buscar_panel.iniciar();
                dispose();
            }
        });
    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
