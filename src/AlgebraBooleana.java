import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class    AlgebraBooleana extends JFrame{
    private JPanel jPanel1;
    private JButton btnCalcular;
    private JButton btnLimpiar;
    private JTextField txtIngresar;
    private JTextField txtResultado;
    private JPanel panel;
    private JButton regresarButton;

    private void createUIComponents() {
        // Inicializar componentes
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(6, 1));

        btnLimpiar = new JButton("Limpiar");
        btnCalcular = new JButton("Calcular");
        txtIngresar = new JTextField();
        txtResultado = new JTextField();

        jPanel1.add(txtIngresar);
        jPanel1.add(btnCalcular);
        jPanel1.add(txtResultado);
        jPanel1.add(btnLimpiar);

        panel.add(jPanel1, BorderLayout.CENTER);
    }

    public AlgebraBooleana(String nombreusuario) {

        setTitle("Algebra Booleana");
        setContentPane(this.panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1080, 720));
        setLocationRelativeTo(null);
        setVisible(true);

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = txtIngresar.getText();
                if (isValidExpression(input)) {
                    String result = simplifyBooleanExpression(input);
                    txtResultado.setText(result);
                } else {
                    JOptionPane.showMessageDialog(jPanel1, "Expresión no válida. Use solo A, B, C, +, *, ' y paréntesis.");
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIngresar.setText("");
                txtResultado.setText("");
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Opciones opcion  = new Opciones(nombreusuario);
                setVisible(false);
                opcion.setVisible(true);

            }
        });
    }
    private boolean isValidExpression(String expression) {
        String validPattern = "^[ABC01\\s()+*']+$";
        return Pattern.matches(validPattern, expression);
    }

    private String simplifyBooleanExpression(String expression) {
        // Leyes de identidad
        expression = expression.replace("0+A", "A");
        expression = expression.replace("A+0", "A");
        expression = expression.replace("1*A", "A");
        expression = expression.replace("A*1", "A");

        // Leyes de dominación
        expression = expression.replace("1+A", "1");
        expression = expression.replace("A+1", "1");
        expression = expression.replace("0*A", "0");
        expression = expression.replace("A*0", "0");

        // Leyes de idempotencia
        expression = expression.replace("A+A", "A");
        expression = expression.replace("A*A", "A");

        // Leyes de complementación
        expression = expression.replace("A+A'", "1");
        expression = expression.replace("A'+A", "1");
        expression = expression.replace("A*A'", "0");
        expression = expression.replace("A'*A", "0");

        // Ley de involución
        expression = expression.replace("(A')'", "A");
        expression = expression.replace("(B')'", "B");
        expression = expression.replace("(C')'", "C");

        // Leyes distributivas
        expression = expression.replace("A*(B+C)", "(A*B)+(A*C)");
        expression = expression.replace("A+(B*C)", "(A+B)*(A+C)");

        // Simplificaciones adicionales con tres variables
        expression = expression.replace("A*(A+B)", "A");
        expression = expression.replace("A+(A*B)", "A");
        expression = expression.replace("A*(A'+B)", "A*B");
        expression = expression.replace("A+(A'*B)", "A+B");
        expression = expression.replace("(A+B)*(A+C)", "A+(B*C)");
        expression = expression.replace("(A*B)+(A*C)", "A*(B+C)");

        // Leyes de absorción
        expression = expression.replace("A*(A+B)", "A");
        expression = expression.replace("A+(A*B)", "A");

        // Leyes de Morgan
        expression = expression.replace("(A+B)'", "A'*B'");
        expression = expression.replace("(A*B)'", "A'+B'");

        // Complemento del complemento
        expression = expression.replace("A''", "A");

        return expression;
    }

}

