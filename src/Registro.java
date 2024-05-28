import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro  extends JFrame{
    private JPanel Jpanel_Registro;
    private JLabel name;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JLabel last;
    private JLabel email;
    private JTextField textFieldEmail;
    private JTextField textFieldPhone;
    private JLabel phone;
    private JTextField textFieldUser;
    private JLabel User;
    private JPasswordField passwordField;
    private JLabel Password;
    private JButton registrateButton;
    private JButton cancelarButton;
    private JLabel Confirm;
    private JPasswordField ConfirmpasswordField;
    private ConxBD conexion;

    public Registro(){
        setTitle("Ultimate Code Development");
        setContentPane(Jpanel_Registro);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(735, 700));
        setLocationRelativeTo(null);

        // Crear instancia de ConxBD
        conexion = new ConxBD();

        //Accion del boton Registrate
        registrateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    registroUsuario();
            }
        });
        //Accion del boton Cancelar
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de la ventana Login y hacerlo visible
                Login login = new Login();
                login.setVisible(true);

                // Ocultar este formulario si es necesario
                setVisible(false);
            }
        });
        setVisible(true);
    }

    //Metodo de registro
    private  void registroUsuario(){
        String name = textFieldNombre.getText();
        String last = textFieldApellido.getText();
        String email = textFieldEmail.getText();
        String phone = textFieldPhone.getText();
        String user = textFieldUser.getText();
        String contrasenia = String.valueOf(passwordField.getPassword());
        String confcontrasenia = String.valueOf(ConfirmpasswordField.getPassword());
        //Validacion de Campos
        if (!validarCampos(name, last, email, phone, user, contrasenia, confcontrasenia)) {
            return;
        }

        // Insertar usuario en la base de datos
        conexion.insertarUsuario(name, last, phone, user, email,contrasenia);
        // Mostrar mensaje de éxito y limpiar campos
        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente!", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();

    }
    // Método para validar los campos del formulario
    private boolean validarCampos(String name, String last, String email, String phone, String user, String contrasenia, String confcontrasenia) {
        if (name.isEmpty() || last.isEmpty() || email.isEmpty() || phone.isEmpty() || user.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese todos los Campos!", "Intentelo de nuevo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!contrasenia.equals(confcontrasenia)) {
            JOptionPane.showMessageDialog(this, "La Contraseña no Coincide", "Intentelo de nuevo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Ingrese un correo electrónico válido", "Intentelo de nuevo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!phone.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese un número de teléfono válido", "Intentelo de nuevo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    // Método para limpiar los campos del formulario
    private void limpiarCampos() {
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldEmail.setText("");
        textFieldPhone.setText("");
        textFieldUser.setText("");
        passwordField.setText("");
        ConfirmpasswordField.setText("");
    }


}
