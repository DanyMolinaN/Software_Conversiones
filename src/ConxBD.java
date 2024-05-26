import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConxBD {
    // Datos para la conexión
    private String base = "boolean_binary_master";
    private String url = "jdbc:mysql://localhost:3306/" + base;
    private String user = "root";
    private String password = "12345";

    // Constructor
    public ConxBD() {
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una nueva conexión
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Método para insertar un usuario
    public void insertarUsuario(String nombre, String apellido, String telefono, String usuario, String correo, String contrasena) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO usuarios (nombre, apellido, telefono, usuario, correo, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, telefono);
            pstmt.setString(4, usuario);
            pstmt.setString(5, correo);
            pstmt.setString(6, contrasena);

            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Un nuevo usuario fue insertado exitosamente!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener usuarios (ejemplo adicional)
    public void obtenerUsuarios() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM usuarios";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                String usuario = rs.getString("usuario");
                String correo = rs.getString("correo");

                System.out.print("ID: " + id);
                System.out.print(", Nombre: " + nombre);
                System.out.print(", Apellido: " + apellido);
                System.out.print(", Telefono: " + telefono);
                System.out.print(", Usuario: " + usuario);
                System.out.print(", Correo: " + correo);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
