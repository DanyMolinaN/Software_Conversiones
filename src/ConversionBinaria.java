import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversionBinaria {
    private JPanel jPanelLogin;
    private JComboBox<String> cmbMenu;
    private JButton btnLimpiar;
    private JButton btnCalcular;
    private JTextField txtIngresar;
    private JTextField txtResultado;

    public ConversionBinaria() {
        // Asegúrate de que los componentes no sean null
        if (cmbMenu != null) {
            cmbMenu.addItem("Binario a Decimal");
            cmbMenu.addItem("Decimal a Binario");
            cmbMenu.addItem("Binario a Hexadecimal");

            cmbMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actualizarConversion();
                }
            });
        }

        if (btnCalcular != null) {
            btnCalcular.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calcularConversion();
                }
            });
        }

        if (btnLimpiar != null) {
            btnLimpiar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    limpiarCampos();
                }
            });
        }
    }

    private void calcularConversion() {
        try {
            String input = txtIngresar.getText();
            String resultado = "";
            String opcionSeleccionada = (String) cmbMenu.getSelectedItem();

            if (input.isEmpty()) {
                txtResultado.setText("El campo de entrada está vacío.");
                return;
            }

            switch (opcionSeleccionada) {
                case "Binario a Decimal":
                    if (!validarBinario(input)) {
                        txtResultado.setText("Entrada no válida. Ingrese un número binario.");
                        return;
                    }
                    resultado = binarioADecimal(input);
                    break;
                case "Decimal a Binario":
                    if (!validarDecimal(input)) {
                        txtResultado.setText("Entrada no válida. Ingrese un número decimal.");
                        return;
                    }
                    resultado = decimalABinario(input);
                    break;
                case "Binario a Hexadecimal":
                    if (!validarBinario(input)) {
                        txtResultado.setText("Entrada no válida. Ingrese un número binario.");
                        return;
                    }
                    resultado = binarioAHexadecimal(input);
                    break;
                default:
                    resultado = "Opción no válida";
            }

            txtResultado.setText(resultado);
        } catch (NumberFormatException ex) {
            txtResultado.setText("Error en el formato de entrada");
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
        int decimal = 0;
        for (int i = 0; i < binario.length(); i++) {
            if (binario.charAt(binario.length() - 1 - i) == '1') {
                decimal += Math.pow(2, i);
            }
        }
        return String.valueOf(decimal);
    }

    private String decimalABinario(String decimal) {
        int num = Integer.parseInt(decimal);
        StringBuilder binario = new StringBuilder();

        while (num > 0) {
            binario.insert(0, num % 2);
            num /= 2;
        }

        return binario.length() > 0 ? binario.toString() : "0";
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
            Integer.parseInt(decimal);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void inicializarInterfaz() {
        JFrame frame = new JFrame("Conversión Binaria");
        frame.setContentPane(this.jPanelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
