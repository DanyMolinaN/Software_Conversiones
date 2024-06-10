import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversionBinaria extends JFrame {
    private JPanel jPanel1;
    private JComboBox<String> cmbMenu;
    private JButton btnLimpiar;
    private JButton btnCalcular;
    private JTextField txtIngresar;
    private JTextField txtResultado;
    private JPanel panel;
    private JButton regresarButton;

    public ConversionBinaria(String nombreusuario) {
        setTitle("Conversion Binaria");
        setContentPane(this.panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1920, 1080));
        setLocationRelativeTo(null);
        setVisible(true);

        // Asegúrate de que los componentes no sean null
        cmbMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarConversion();
            }
        });


        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularConversion();
            }
        });



        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        //Accion Regresar
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Opciones opcion  = new Opciones(nombreusuario);
                setVisible(false);
                opcion.setVisible(true);
            }
        });
    }



    private void calcularConversion() {
        try {
            String input = txtIngresar.getText();
            String resultado = "";
            String opcionSeleccionada = (String) cmbMenu.getSelectedItem();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo de entrada está vacío.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (opcionSeleccionada) {
                case "Binario a Decimal":
                    if (!validarBinario(input)) {
                        JOptionPane.showMessageDialog(null, "Entrada no válida. Ingrese un número binario.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    resultado = binarioADecimal(input);
                    break;
                case "Decimal a Binario":
                    if (!validarDecimal(input)) {
                        JOptionPane.showMessageDialog(null, "Entrada no válida. Ingrese un número decimal.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    resultado = decimalABinario(input);
                    break;
                case "Binario a Hexadecimal":
                    if (!validarBinario(input)) {
                        JOptionPane.showMessageDialog(null, "Entrada no válida. Ingrese un número binario.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    resultado = binarioAHexadecimal(input);
                    break;
                default:
                    resultado = "Opción no válida";
            }

            txtResultado.setText(resultado);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error en el formato de entrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void actualizarConversion() {
        if (!txtIngresar.getText().isEmpty()) {
            calcularConversion();
        }
    }

    private void limpiarCampos() {
        txtIngresar.setText("");
        txtResultado.setText("");
    }

    private String binarioADecimal(String binario) {
        boolean negative = binario.startsWith("-");
        if (negative) {
            binario = binario.substring(1);
        }

        int decimal = 0;
        for (int i = 0; i < binario.length(); i++) {
            if (binario.charAt(binario.length() - 1 - i) == '1') {
                decimal += Math.pow(2, i);
            }
        }

        return negative ? "-" + decimal : String.valueOf(decimal);
    }

    private String decimalABinario(String decimalStr) {
        try {
            // Convertir el String a double
            double decimal = Double.parseDouble(decimalStr);

            // Verificar si es negativo
            boolean negative = decimal < 0;
            if (negative) {
                decimal = -decimal;
            }

            long integerPart = (long) decimal;
            double fractionalPart = decimal - integerPart;
            StringBuilder binaryString = new StringBuilder();

            // Añadir el signo negativo si es necesario
            if (negative) {
                binaryString.append("-");
            }

            // Convertir la parte entera a binario
            binaryString.append(Long.toBinaryString(integerPart));

            // Convertir la parte fraccional a binario si hay fracción
            if (fractionalPart > 0) {
                binaryString.append(".");
                while (fractionalPart > 0 && binaryString.length() - binaryString.indexOf(".") < 32) {
                    fractionalPart *= 2;
                    long bit = (long) fractionalPart;
                    binaryString.append(bit);
                    fractionalPart -= bit;
                }
            }

            return binaryString.toString();
        } catch (NumberFormatException e) {
            // Manejar el error si el String no puede convertirse a double
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número decimal válido.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private String binarioAHexadecimal(String binario) {
        // Convertir binario a decimal primero
        int decimal = Integer.parseInt(binarioADecimal(binario));
        // Convertir decimal a hexadecimal
        StringBuilder hex = new StringBuilder();

        while (decimal > 0) {
            int residuo = decimal % 16;
            if (residuo < 10) {
                hex.insert(0, (char) (residuo + '0'));
            } else {
                hex.insert(0, (char) (residuo - 10 + 'A'));
            }
            decimal /= 16;
        }

        return hex.length() > 0 ? hex.toString() : "0";
    }

    private boolean validarBinario(String binario) {
        for (char c : binario.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }

    private boolean validarDecimal(String decimal) {
        try {
            // Intentar parsear el String como un número decimal (double)
            Double.parseDouble(decimal);

            // Verificar si el valor no es un número decimal
            if (decimal.contains(".")) {
                // Puede agregar más validaciones específicas para formatos esperados
                // Por ejemplo, se puede verificar que después del punto decimal no haya más de dos dígitos
                String[] parts = decimal.split("\\.");
                if (parts.length > 2 || (parts.length == 2 && parts[1].length() > 2)) {
                    return false;
                }
            }

            return true;
        } catch (NumberFormatException e) {
            // Si ocurre una excepción de formato, el valor no es un número decimal válido
            return false;
        }
    }

    private void createUIComponents() {
        // Inicializar componentes
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(6, 1));

        cmbMenu = new JComboBox<>();
        btnLimpiar = new JButton("Limpiar");
        btnCalcular = new JButton("Calcular");
        txtIngresar = new JTextField();
        txtResultado = new JTextField();

        jPanel1.add(cmbMenu);
        jPanel1.add(txtIngresar);
        jPanel1.add(btnCalcular);
        jPanel1.add(txtResultado);
        jPanel1.add(btnLimpiar);

        panel.add(jPanel1, BorderLayout.CENTER);
    }

}