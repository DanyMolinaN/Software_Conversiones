import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.bpodgursky.jbool_expressions.*;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.rules.RuleSet;

public class AlgebraBooleana extends JFrame {
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
        setMinimumSize(new Dimension(1920, 1080));
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
                Opciones opcion = new Opciones(nombreusuario);
                setVisible(false);
                opcion.setVisible(true);
            }
        });
    }

    private boolean isValidExpression(String expression) {
        try {
            // Intenta parsear la expresión
            ExprParser.parse(expression);
            return true;
        } catch (Exception e) {
            // Si hay un error de parseo, la expresión no es válida
            return false;
        }
    }


    private String simplifyBooleanExpression(String expression) {
        try {
            // Parsear la expresión desde una cadena
            Expression<String> parsedExpression = ExprParser.parse(expression);

            // Simplificar la expresión
            Expression<String> simplifiedExpression = RuleSet.simplify(parsedExpression);

            // Devolver solo la expresión simplificada
            return simplifiedExpression.toString();
        } catch (Exception e) {
            return "Error al simplificar la expresión: " + e.getMessage();
        }
    }
}