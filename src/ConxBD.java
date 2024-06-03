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

    // Método para comparar usuario y contraseña
    public boolean verificarCredenciales(String correo, String contrasena) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean credencialesValidas = false; // Variable para almacenar el resultado de la verificación

        try {
            conn = getConnection();
            String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasena);
            rs = pstmt.executeQuery();

            // Verificar si hay algún resultado
            credencialesValidas = rs.next();

            // Si hay un resultado, imprimir los valores obtenidos de la base de datos
            if (credencialesValidas) {
                int id = rs.getInt("id");
                String usuario = rs.getString("usuario");
                String email = rs.getString("correo");
                // Imprimir los valores obtenidos
                System.out.println("ID: " + id);
                System.out.println("Usuario: " + usuario);
                System.out.println("correo: "+ email);
                // Puedes imprimir otros valores aquí si los necesitas

            } else {
                System.out.println("No se encontraron credenciales válidas para el correo proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return credencialesValidas;


    }
    // Obtener nombre del usuario
    public String obtenerNombreUsuario(String correo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT nombre FROM usuarios WHERE correo = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, correo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
