package pro.Utilidades;

import java.sql.*;
public class ConexionSQL{
    private static String usuario="root";
    private static String contraseña="ger123";
    private static String url="jdbc:mysql://localhost:3306/providussa?useSSL=false&serverTimezone=UTC";


    public static Connection realizarConexion() throws SQLException{
        Connection conexion;
        try {
            conexion =DriverManager.getConnection(url,usuario,contraseña); 
            return conexion;

            
        } catch (SQLException e) {
            System.out.println("Error de conexion a la base de datos "+ e.getMessage());
            
        }
        return null;
    }


    public static ResultSet consultar(String consutla){
        try {
            Connection conexion= ConexionSQL.realizarConexion();
            //Interfaz para realizar la consulta sql
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(consutla);
            return resultado;
        } catch (SQLException e) {
           System.out.println("Error al procesar la consulta");
        }
        return null;

    }

    public static int operacionIUD(String sql){
        try {
            Connection conexion= ConexionSQL.realizarConexion();
            //Interfaz para realizar la inserccion
            Statement statement = conexion.createStatement();
            int filasAfectadas = statement.executeUpdate(sql);
            return filasAfectadas;
        } catch (SQLException e) {
           System.out.println("Error en operacion IUD");
           return 0;
        }
    }

}
