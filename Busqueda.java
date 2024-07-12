import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Busqueda extends JFrame{
    private JPanel panel3;
    private JTextField cedula1_busca;
    private JButton volverButton;
    private JButton salirButton;
    private JButton buscarButton;
    private JLabel visualiza_datos;

    public Busqueda(){
        super("Busqueda");
        setContentPane(panel3);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
                String user = "root";
                String password = "";

                try (Connection conecta = DriverManager.getConnection(url,user,password)){
                    System.out.println("Conectado a la base de datos");

                    String cedula_buscar0 = cedula1_busca.getText();

                    // Consulta de la base de datos
                    String sql = "select * from usuario where cedula=?";
                    PreparedStatement pst = conecta.prepareStatement(sql);
                    pst.setString(1, cedula_buscar0);
                    ResultSet rs = pst.executeQuery();

                    StringBuilder datos = new StringBuilder("<html>");


                    if (rs.next()){
                        System.out.println("Paciente encontrado");
                        JOptionPane.showMessageDialog(null,"Paciente Encontrado", "Exito", JOptionPane.INFORMATION_MESSAGE);

                        datos.append("</html>");
                        visualiza_datos.setText(datos.toString());

                        //visualiza_datos.setText(toString(datos));

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No existen registros","ERROR",JOptionPane.ERROR_MESSAGE);
                    }

                }
                catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "No se conectdo a la base de datos","ERROR",JOptionPane.ERROR_MESSAGE);

                }

            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registro registrar = new Registro();
                registrar.iniciar();
                dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login salir = new Login();
                salir.iniciar();
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
