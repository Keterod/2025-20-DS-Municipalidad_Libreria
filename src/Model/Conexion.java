package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conectar() {
        Connection conn = null;
        try {
            // Cambia los datos de conexión según tu configuración
            String url = "jdbc:sqlserver://UHYXE30126\\MSSQLSERVER1:52643;databaseName=Biblioteca;encrypt=false;trustServerCertificate=true";
            String user = "sa"; // o tu usuario de SQL Server
            String password = "continental";

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión exitosa a SQL Server");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a SQL Server: " + e.getMessage());
        }
        return conn;
    }

    // Método principal SOLO para probar la conexión
    public static void main(String[] args) {
        Connection conn = conectar();
        if (conn != null) {
            System.out.println("✅ Conectado correctamente a la base de datos.");
        } else {
            System.out.println("❌ No se pudo establecer la conexión.");
        }
    }
}
