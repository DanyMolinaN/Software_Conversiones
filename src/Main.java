import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        //variables para la conexion
        Connection conn = null;
        Statement stmt = null;

        /*Datos para la coneccion */
        String base="boolean_binary_master";
        String url= "jdbc:mysql://localhost:3306/"+base;
        String user="root";
        String password="basededatos";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,password);
            System.out.println("conexion exitosa");

            //script de la consulta
            System.out.println("Creando la declaración...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM usuarios";
            ResultSet rs = stmt.executeQuery(sql);

            //extraer datos del conjunto de resultados
            while (rs.next()) {
                // Recuperar por columna
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                String usuario = rs.getString("usuario");
                String correo = rs.getString("correo");
                // Imprimir los valores
                System.out.print("ID: " + id);
                System.out.print(", Nombre: " + nombre);
                System.out.print(", Apellido: " + apellido);
                System.out.print(", Telefono: " + telefono);
                System.out.print(", Usuario: " + usuario);
                System.out.print(", Correo: " + correo);
                System.out.println();
            }
            //cerrar recursos
            rs.close();
            stmt.close();
            conn.close();

        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }
}