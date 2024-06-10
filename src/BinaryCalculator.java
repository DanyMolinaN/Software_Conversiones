import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryCalculator extends JFrame {

    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel panel1;
    private JPanel panel2;
    private JButton multiplyButton;
    private JButton subtractButton;
    private JButton divideButton;
    private JTextField resultField;
    private JButton addButton;
    private JTextArea detailsArea;
    private JButton cleanButton;
    private JLabel resultadoLabel;
    private JLabel BinarioLabel2;
    private JLabel BinarioLabel1;
    private JButton BackButton;

    public BinaryCalculator(String nombreusuario) {

        setTitle("Algebra Booleana");
        setContentPane(this.mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1920, 1080));
        setLocationRelativeTo(null);
        setVisible(true);
        // Deshabilitar la edición en el JTextArea
        detailsArea.setEditable(false);

        // Deshabilitar la edición en el JTextField
        resultField.setEditable(false);
        resultField.setFocusable(false);
        detailsArea.setBackground(Color.LIGHT_GRAY); // Cambia el color de fondo a GRIS
        resultField.setBackground(Color.LIGHT_GRAY);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String num1 = textField1.getText();
                String num2 = textField2.getText();

                if (isValidBinary(num1) && isValidBinary(num2)) {
                    // Explicación detallada de la suma binaria
                    String result = add(num1, num2);
                    String explanation = "Suma binaria:\n\n";
                    explanation += "   " + num1 + "\n+  " + num2 + "\n----------------\n   " + result;
                    displayOperationDetails(explanation);
                    resultField.setText(result);
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese solo números binarios válidos (0 y 1).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String num1 = textField1.getText();
                String num2 = textField2.getText();

                if (isValidBinary(num1) && isValidBinary(num2)) {
                    // Explicación detallada de la multiplicación binaria
                    String result = multiply(num1, num2);
                    String explanation = "Multiplicación binaria:\n\n";
                    explanation += "   " + num1 + "\n*  " + num2 + "\n----------------\n   " + result;
                    displayOperationDetails(explanation);
                    resultField.setText(result);
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese solo números binarios válidos (0 y 1).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String num1 = textField1.getText();
                String num2 = textField2.getText();

                if (isValidBinary(num1) && isValidBinary(num2)) {
                    // Explicación detallada de la resta binaria
                    String result = subtract(num1, num2);
                    String explanation = "Resta binaria:\n\n";
                    explanation += "   " + num1 + "\n-  " + num2 + "\n----------------\n   " + result;
                    displayOperationDetails(explanation);
                    resultField.setText(result);
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese solo números binarios válidos (0 y 1).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String num1 = textField1.getText();
                String num2 = textField2.getText();

                if (isValidBinary(num1) && isValidBinary(num2)) {
                    try {
                        // Explicación detallada de la división binaria
                        String result = divide(num1, num2);
                        String explanation = "División binaria:\n\n";
                        explanation += "   " + num1 + "\n/  " + num2 + "\n----------------\n   " + result;
                        displayOperationDetails(explanation);
                        resultField.setText(result);
                    } catch (ArithmeticException ex) {
                        resultField.setText("Error: División por cero");
                        displayOperationDetails("Intento de división por cero:\n\n" + num1 + "\n/\n" + num2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese solo números binarios válidos (0 y 1).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                resultField.setText("");
                detailsArea.setText("");
            }
        });
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Opciones opcion  = new Opciones(nombreusuario);
                setVisible(false);
                opcion.setVisible(true);
            }
        });
    }

    // Método para validar que la cadena solo contiene '0' y '1'
    private boolean isValidBinary(String input) {
        return input.matches("[01]+");
    }

    // Método para mostrar detalles de la operación
    private void displayOperationDetails(String details) {
        detailsArea.setText(details);
    }

    // Métodos para las operaciones binarias
    private String add(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        int maxLen = Math.max(len1, len2);
        StringBuilder result = new StringBuilder();
        int carry = 0;

        for (int i = 0; i < maxLen; i++) {
            int bit1 = i < len1 ? a.charAt(len1 - 1 - i) - '0' : 0;
            int bit2 = i < len2 ? b.charAt(len2 - 1 - i) - '0' : 0;
            int sum = bit1 + bit2 + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }

    private String subtract(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        int maxLen = Math.max(len1, len2);
        StringBuilder result = new StringBuilder();
        int borrow = 0;

        for (int i = 0; i < maxLen; i++) {
            int bit1 = i < len1 ? a.charAt(len1 - 1 - i) - '0' : 0;
            int bit2 = i < len2 ? b.charAt(len2 - 1 - i) - '0' : 0;
            int sub = bit1 - bit2 - borrow;
            if (sub < 0) {
                sub += 2;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(sub);
        }
        return result.reverse().toString().replaceFirst("^0+(?!$)", "");
    }

    private String multiply(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        int[] result = new int[len1 + len2];

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int mul = (a.charAt(i) - '0') * (b.charAt(j) - '0');
                int sum = mul + result[i + j + 1];
                result[i + j + 1] = sum % 2;
                result[i + j] += sum / 2;
            }
        }

        StringBuilder resultStr = new StringBuilder();
        for (int r : result) {
            resultStr.append(r);
        }
        return resultStr.toString().replaceFirst("^0+(?!$)", "");
    }

    private String divide(String a, String b) {
        if (b.equals("0")) {
            throw new ArithmeticException("División por cero");
        }

        int dividend = Integer.parseInt(a, 2);
        int divisor = Integer.parseInt(b, 2);
        int quotient = dividend / divisor;
        return Integer.toBinaryString(quotient);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
