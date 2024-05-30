import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class    AlgebraBooleana {
    private JPanel jPanel1;
    private JButton btnCalcular;
    private JButton btnLimpiar;
    private JTextField txtIngresar;
    private JTextField txtResultado;

    public AlgebraBooleana() {
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

