import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.sql.DriverManager.getConnection;

public class Login extends JDialog {
    private JPanel jPanelLogin;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
    private JButton btnRegistrar;

    public Login(JFrame parent) {
        super(parent);
        setTitle("Boolean Binary Master");
        setContentPane(jPanelLogin);
        setMinimumSize(new Dimension(400, 580));
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);

        // Crear instancia de ConxBD
        //conexion = new ConxBD();

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicioSesion();
            }
        });
    }

    //Metodo de InicioSesion
    private void inicioSesion() {
        String usuario = String.valueOf(txtUser.getText());
        String contrasena = String.valueOf(txtPassword.getText());

        ConxBD db = new ConxBD();
        boolean credencialesValidas = db.verificarCredenciales(usuario, contrasena);

        // Actuar en consecuencia
        if (credencialesValidas) {
            System.out.println("Inicio de sesión exitoso para el usuario: " + usuario);
        } else {
            System.out.println("Usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.");
        }

    }
}
