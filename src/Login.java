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
        setMinimumSize(new Dimension(400, 580));
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
        String correo = String.valueOf(txtUser.getText());
        String contrasena = String.valueOf(txtPassword.getText());

        //Si las credenciales estan en la base de datos entramos a la otra ventana
        if(conexion.verificarCredenciales(correo, contrasena)){
            ConversionBinaria conversion = new ConversionBinaria();
            setVisible(false);
            conversion.setVisible(true);
        }else {
            System.out.println("Acceso denegado");
        }
        ;
    }

//    private void createUIComponents() {
//        jPanelLogin = new JPanel();
//        txtUser = new JTextField(20);
//        txtPassword = new JPasswordField(20);
//        btnIngresar = new JButton("Ingresar");
//        btnRegistrar = new JButton("Registrar");
//
//        jPanelLogin.setLayout(new GridLayout(4, 1));
//        jPanelLogin.add(new JLabel("Usuario:"));
//        jPanelLogin.add(txtUser);
//        jPanelLogin.add(new JLabel("Contraseña:"));
//        jPanelLogin.add(txtPassword);
//        jPanelLogin.add(btnIngresar);
//        jPanelLogin.add(btnRegistrar);
//
//        setContentPane(jPanelLogin);
//    }
}