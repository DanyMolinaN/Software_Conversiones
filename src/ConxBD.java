import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConxBD {
    // Variables para la conexión
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    // Datos para la conexión
    private String base = "boolean_binary_master";
    private String url = "jdbc:mysql://localhost:3306/" + base;
    private String user = "root";
    private String password = "12345";

    public ConxBD() {
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa");

            // Crear la declaración
            System.out.println("Creando la declaración...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM usuarios";
            rs = stmt.executeQuery(sql);

            // Extraer datos del conjunto de resultados
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

