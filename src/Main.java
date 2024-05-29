

public class Main {
    public static void main(String[] args) {
        new ConxBD();
        //Ventana del registro
        //Registro RegistroForm = new Registro(null);
        Login LoginForm = new Login();
        LoginForm.setVisible(true);

        new ConversionBinaria().inicializarInterfaz();


    }


}