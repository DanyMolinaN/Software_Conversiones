import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EEECalculator extends JFrame{
    private JPanel mainPanel;
    private JPanel Panel1;
    private JPanel Panel2;
    private JTextField decimalInputField;
    private JTextField binaryOutputField;
    private JButton convertButton;
    private JButton cleanButton;
    private JButton regresarButton;

    public EEECalculator(String nombreusuario) {
        setTitle("IEEE 754 Converter");
        setContentPane(this.mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1920, 1080));
        setLocationRelativeTo(null);
        setVisible(true);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirDecimalABinario();
            }
        });

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
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

    // Función para convertir un número decimal a binario en formato IEEE 754
    private void convertirDecimalABinario() {
        try {
            float numero = Float.parseFloat(decimalInputField.getText());
            String resultado = convertirDecimalABinario(numero);
            binaryOutputField.setText(resultado);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un número decimal válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Función para convertir un número decimal en formato IEEE 754 de 32 bits
    private String convertirDecimalABinario(float numero) {
        // Comprobamos si el número es negativo o positivo
        int signo;
        if (numero < 0) {
            signo = 1;
            numero = Math.abs(numero);
        } else {
            signo = 0;
        }

        // Partimos el número en su parte entera y decimal
        int parteEntera = (int) numero;
        float parteDecimal = numero - parteEntera;

        // Convertimos la parte entera a binario
        StringBuilder parteEnteraBinaria = new StringBuilder(Integer.toBinaryString(parteEntera));

        // Convertimos la parte decimal a binario
        StringBuilder parteDecimalBinaria = new StringBuilder();
        while (parteDecimal > 0) {
            parteDecimal *= 2;
            if (parteDecimal >= 1) {
                parteDecimalBinaria.append("1");
                parteDecimal -= 1;
            } else {
                parteDecimalBinaria.append("0");
            }
        }

        // Concatenamos la parte entera y decimal en una sola cadena binaria
        String binario = parteEnteraBinaria.toString() + "." + parteDecimalBinaria.toString();

        // Normalizamos el número en el formato IEEE 754
        int exponente = 127 + binario.indexOf('.'); // Calculamos el exponente sumando 127 al sesgo
        String mantisa = binario.replace(".", "").substring(1); // Eliminamos el punto y el bit del entero
        String exponenteBinario = Integer.toBinaryString(exponente);
        while (exponenteBinario.length() < 8) { // Aseguramos que el exponente tenga 8 bits
            exponenteBinario = "0" + exponenteBinario;
        }
        while (mantisa.length() < 23) { // Aseguramos que la mantisa tenga 23 bits
            mantisa += "0";
        }

        // Concatenamos el bit de signo, el exponente y la mantisa para obtener el número en IEEE 754
        String ieee754 = signo + exponenteBinario + mantisa;

        return ieee754;
    }

    // Función para limpiar los campos de entrada y salida
    private void limpiarCampos() {
        decimalInputField.setText("");
        binaryOutputField.setText("");
    }

}
