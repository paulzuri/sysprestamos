package backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.JOptionPane;

/**
 *
 * @author dacol
 */
public class Registro {
    
    // pps, es el comando sql que se va a ejecutar
    PreparedStatement pps;
    // el comando se lo construye en un string sql, y luego se lo va a asignar al pps
    // (INSERT INTO) indica que se va a añadir un registro a la base de datos
    String sql = "INSERT INTO bibliotecadb.";
    // ct permite conectarse a la base de datos
    Connection ct;

    public Registro(Connection ct) {
        this.ct = ct;
    }

    public void registrar(String tabla, ArrayList<String> atributos) {
        // partes del comando sql que se añaden para hacer un registro en las diferentes tablas
        String atributosSQLaRegistrarTabEst = "(nombre_es,correo_es,telefono_es) VALUES(";
        String atributosSQLaRegistrarTabLib = "(titulo_li,autor_li,fecha_ed,cantidad) VALUES(";
        String atributosSQLaRegistrarTabPrest = "(codigo_li,codigo_es,fecha_pr) VALUES(";
        // sqlR es otro String vacio que se usa para seguir construyendo el comando SQL
        String sqlR = "";
        
        if (tabla.equals("estudiantes")) {
            sqlR = sql + tabla + atributosSQLaRegistrarTabEst;
            // El comando sqlR resulta: "INSERT INTO bibliotecadb.estudiantes (nombre_es,correo_es,telefono_es) VALUES("
            
            // se completa el comando sql con los atributos respectivos, p. ej. "'Pepe','pepe@pepe.pepe','123154648')
            for (int i = 0; i < atributos.size(); i++) {
                if (i < atributos.size() - 1) {
                    sqlR += "'" + atributos.get(i) + "'" + ",";
                } else {
                    sqlR += "'" + atributos.get(i) + "'" + ")";
                }
            }
        } else if (tabla.equals("libros")) {
            sqlR = sql + tabla + atributosSQLaRegistrarTabLib;
            // El comando sqlR resulta: "INSERT INTO bibliotecadb.libros (titulo_li,autor_li,fecha_ed,cantidad) VALUES("
            
            // se completa el comando sql con los atributos respectivos, p. ej. "'El libro','Martin','12/12/2012','1')
            for (int i = 0; i < atributos.size(); i++) {
                if (i < atributos.size() - 1) {
                    sqlR += "'" + atributos.get(i) + "'" + ",";
                } else {
                    sqlR += atributos.get(i) + ")";
                }
            }
        } else {
            // El comando sqlR resulta: "INSERT INTO bibliotecadb.prestamos (codigo_li,codigo_es,fecha_pr) VALUES("
            
            // se completa el comando sql con los atributos respectivos, p. ej. "'12','4','12/12/2014')
            sqlR = sql + tabla + atributosSQLaRegistrarTabPrest;
            for (int i = 0; i < atributos.size(); i++) {
                if (i < atributos.size() - 1) {
                    sqlR += atributos.get(i) + ",";
                } else {
                    sqlR += "'" + atributos.get(i) + "'" + ")";
                }
            }
        }

        try {
            // primero se hace la conexion a la base de datos con ct
            pps = ct.prepareStatement(sqlR);
            // se ejecuta el comando y se registra un prestamo en la base de datos
            pps.execute();
        } catch (SQLException ex) {
            // si algo sale mal, se registra en un objeto logger el error
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en el registro de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            // imprime en la consola el error
            System.out.println(ex);
        }
    }

}
