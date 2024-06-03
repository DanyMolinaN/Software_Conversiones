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
        setMinimumSize(new Dimension(1080, 720));
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
