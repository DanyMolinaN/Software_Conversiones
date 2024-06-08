import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.util.Stack;
import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.And;
import com.bpodgursky.jbool_expressions.Or;
import com.bpodgursky.jbool_expressions.Not;
import com.bpodgursky.jbool_expressions.Literal;
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
                Opciones opcion = new Opciones(nombreusuario);
                setVisible(false);
                opcion.setVisible(true);
            }
        });
    }

    private boolean isValidExpression(String expression) {
        String validPattern = "^[ABC01\\s()+*']+$";
        return Pattern.matches(validPattern, expression);
    }

    private Expression<String> parseExpression(String expression) {
        Stack<Expression<String>> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            switch (c) {
                case ' ':
                    break; // Ignorar espacios
                case 'A':
                case 'B':
                case 'C':
                    stack.push(Literal.of(Boolean.parseBoolean(String.valueOf(c))));
                    break;
                case '0':
                    stack.push(Literal.of(Boolean.parseBoolean("false")));
                    break;
                case '1':
                    stack.push(Literal.of(Boolean.parseBoolean("true")));
                    break;
                case '+':
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Expresión no válida: falta operandos para OR");
                    }
                    Expression<String> rightOr = stack.pop();
                    Expression<String> leftOr = stack.pop();
                    stack.push(Or.of(leftOr, rightOr));
                    break;
                case '*':
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Expresión no válida: falta operandos para AND");
                    }
                    Expression<String> rightAnd = stack.pop();
                    Expression<String> leftAnd = stack.pop();
                    stack.push(And.of(leftAnd, rightAnd));
                    break;
                case '\'':
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Expresión no válida: falta operando para NOT");
                    }
                    stack.push(Not.of(stack.pop()));
                    break;
                case '(':
                    // Ignorar, solo se usa para la estructura
                    break;
                case ')':
                    // Ignorar, solo se usa para la estructura
                    break;
                default:
                    throw new IllegalArgumentException("Carácter no válido en la expresión: " + c);
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Expresión no válida");
        }
        return stack.pop();
    }

    private String simplifyBooleanExpression(String expression) {
        try {
            // Parsear la expresión
            Expression<String> parsedExpression = parseExpression(expression);

            // Simplificar la expresión usando jbool
            Expression<String> simplifiedExpression = RuleSet.simplify(parsedExpression);

            // Convertir la expresión simplificada a cadena y devolverla
            return simplifiedExpression.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al simplificar la expresión: " + e.getMessage();
        }
    }
}
