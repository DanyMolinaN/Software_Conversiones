import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Opciones extends  JFrame{
    private JPanel OpcionesJ;
    private JButton conversionesNúmericasButton;
    private JLabel Label_Nombre;
    private JButton operacionesAlgebraicasBinariasButton;
    private JButton simplificaciónBooleanaButton;
    private JButton conversionesEnIEEEButton;
    private JLabel EtiquetaUsuario;
    private JButton salirButton;
    private JButton regresarButton;

    public Opciones(String nombreUsuario) {
        setTitle("Boolean Binary Master");
        setContentPane(OpcionesJ);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1080, 720));

        // Establecer el nombre del usuario en la etiqueta EtiquetaUsuario
        Label_Nombre.setText("Bienvenido, " + nombreUsuario + "!");
        Label_Nombre.setForeground(Color.WHITE);

        conversionesNúmericasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConversionBinaria conversion = new ConversionBinaria(nombreUsuario);
                setVisible(false);
                conversion.setVisible(true);

            }
        });


        operacionesAlgebraicasBinariasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlgebraBooleana booleana = new AlgebraBooleana(nombreUsuario);
                setVisible(false);
                booleana.setVisible(true);
            }
        });


        simplificaciónBooleanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        conversionesEnIEEEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                setVisible(false);
                login.setVisible(true);
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
