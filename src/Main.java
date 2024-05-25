import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        System.out.println("Primer cambio LeninReyesEPN");

        Statement stmt = null;

        try {
            //conexion a la base de datos
            Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/boolean_binary_master","root","basededatos");
            System.out.println("conexion exitosa "+obj);

            //Empleando la busquedad
            System.out.println("Creando la declaración...\n");
            stmt = obj.createStatement();
            String sql;
            sql = "SELECT * FROM usuarios";
            ResultSet rs = stmt.executeQuery(sql);

            //Extraer datos del conjunto de resultados
            while (rs.next()) {
                // Recuperar por columna
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                String usuario = rs.getString("usuario");
                // Imprimir los valores
                System.out.print("ID: " + id);
                System.out.print(", Nombre: " + nombre);
                System.out.print(", Apellido: " + apellido);
                System.out.print(", Correo: " + correo);
                System.out.print(", Telefono: " + telefono);
                System.out.print(", Usuario: " + usuario);
                System.out.println();
            }
            //cerrar recursos
            rs.close();
            stmt.close();
            obj.close();

        }catch (Exception e){
            System.out.println(e);
        }

    }
}