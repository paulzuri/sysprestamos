package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author dacol
 */
public class Eliminacion {
    PreparedStatement pps;
    String sql = "DELETE FROM ";
    Connection ct;
    
    public Eliminacion(Connection ct) {
        this.ct = ct;
    }
    
    public void eliminarEst(int cod){
       this.delete("estudiantes WHERE codigo_es = ", cod);
    }
    
   public void eliminarLib(int cod) {
        this.delete("libros WHERE codigo_li = ", cod);
    }

    public void eliminarPre(int cod) {
        this.delete("prestamos WHERE id_prestamo = ", cod);
    }

    
    private void delete(String line, int cod){
        try {
            // crea un comando sql que contiene: "DELETE FROM prestamos WHERE id_prestamo = 1"
            // line indica la tabla de donde se va a borrar un prestamo
            pps = this.ct.prepareStatement(sql + line + cod);
            // ejecuta el comando en la base de datos
            pps.executeUpdate();
        } catch (SQLException ex) {
            // guarda los errores en un objeto Logger
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al intentar eliminar datos", "No se pudo eliminar", 0);
        } 
    }
}
