import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.sql.DriverManager.getConnection;

public class Login extends JFrame {
    private JPanel jPanelLogin;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
    private JButton btnRegistrar;
    private ConxBD conexion;

    public Login() {
        setTitle("Boolean Binary Master");
        setContentPane(jPanelLogin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1080, 720));
        setLocationRelativeTo(null);

//        createUIComponents();

         //Crear instancia de ConxBD
        conexion = new ConxBD();

        //Accion de Boton iniciar sesion
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicioSesion();
            }
        });

        setVisible(true);

        //Accion de boton Registrarse
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana Registro y hacerlo visible
                Registro registro = new Registro();
                registro.setVisible(true);

                // Ocultar este formulario si es necesario
                setVisible(false);
            }
        });
    }

    //Metodo de InicioSesion
    private void inicioSesion() {
        String correo = txtUser.getText();
        String contrasena = new String(txtPassword.getPassword());

        // Si las credenciales están en la base de datos, entramos a la otra ventana
        if (conexion.verificarCredenciales(correo, contrasena)) {
            String nombreUsuario = conexion.obtenerNombreUsuario(correo);
            Opciones menu = new Opciones(nombreUsuario);
            setVisible(false);
            menu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Acceso denegado. Verifique su correo y contraseña.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }


}