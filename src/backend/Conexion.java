package backend;

import java.sql.*;
import java.time.Instant;
import java.util.logging.*;
import javax.swing.JOptionPane;

/**
 * @author Grupo1
 */

public class Conexion {

    protected String database, url, user, password, driver;
    protected Connection ct;

    public Conexion() {
        this.database = "bibliotecadb";
        this.url = "jdbc:mysql://localhost:3306/";
        this.user = "root";
        this.password = "password";
        this.driver = "com.mysql.cj.jdbc.Driver";
    }

    public Conexion(String user, String password) {
        this.database = "bibliotecadb";
        this.url = "jdbc:mysql://localhost:3306/";
        this.user = user;
        this.password = password;
        this.driver = "com.mysql.cj.jdbc.Driver";
    }
    
    // se conecta a la base de datos
    public Connection conectarBaseDeDatos() throws SQLException {
        try {
            // para conectarse a la base de datos, hay que indicar el nombre del conector
            Class.forName(driver);
            // aqui ct se conecta a la base de datos, despues de indicar la ubicacion (url), el nombre de la base de datos (database)
            // el usuario (user), y la contraseña (password)
            ct = DriverManager.getConnection(this.url + this.database, this.user, this.password);
            
            // Se imprime la fecha de la conexion si se pudo realizar sin inconvenientes
            // Instant.now llama a la fecha actual
            System.out.println("Conexion realizada a las " + Instant.now().toString());
        } catch (ClassNotFoundException | SQLException e) {
            // si existiera un error se guarda en un logger
            JOptionPane.showMessageDialog(null, "No se pudo establecer una conexión con la base de datos", "Error de conexión", 0);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace(System.out);
        }
        return ct;
    }
}
