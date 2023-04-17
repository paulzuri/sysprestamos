package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author dacol
 */
public class Modificacion {
    
    // PreparedStatement es una clase de la libreria sql, que guarda un comando sql
    PreparedStatement pps;
    // Connection crea un objeto que permite conectarse a sql
    Connection ct;
    
    public Modificacion(Connection ct) {
        this.ct = ct;
    }
    
    public void modificarEstudiante(ArrayList<String> atributos, int cod){
        this.modificar(atributos,"estudiantes", cod, "codigo_es");
    }
    
    public void modificarLibro(ArrayList<String> atributos, int cod){
        this.modificar(atributos,"libros", cod, "codigo_li");
    }
    
    // hace lo mismo que modificarCantidadLib, pero modificarCantidadLib no se uso porque no aparecia en el gestor
    public void modificarCantidadLib1(ArrayList<String> atributos, int cod){
        this.modificarCantidadLib(atributos, cod);
    }
    
    private void modificarCantidadLib(ArrayList<String> atributos, int cod){       
        
        /* este metodo, recibe una lista de atributos y un codigo de libro
            y prepara el comando sql
            luego lo intenta ejecutar y modifica los datos en la base datos
            y sino, registra los errores
        */
        
        // declara el comando sql
        String sql;
        
        // se comienza a armar el comando sql
        // (UPDATE) se va a cambiar un dato en la tabla de la base de datos 
        //  (bibliotecadb.libros) la ubicacion es la base bibliotecadb, la tabla es la tabla de libros 
        // (SET) se va a cambiar un valor
        // atributos.get indica qué atributo se desea cambiar, p. ej. cantidad de libros
        // WHERE codigo_li =, indica la fila donde se desea cambiar el valor
        // cod, es el codigo del libro que se desea modificar
        
        sql = "UPDATE bibliotecadb.libros SET "+atributos.get(0)+" WHERE codigo_li = "+ cod;
        
        // las instrucciones que se mandan a SQL son propensas a dar errores, por eso siempre estan en bloques try-catch
        try {
            // pps es el comando sql que se va a ejecutar
            // primero se crea la conexion con la base de datos
            pps = this.ct.prepareStatement(sql);
            
            // luego se ejecuta el comando
            pps.executeUpdate();
            
        } catch (SQLException ex) {
            // los errores se guardan en un objeto logger
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            // saca una ventana de errores que indica que no se pudo modificar
            JOptionPane.showMessageDialog(null, "Error al intentar modificar datos", "Error de modificación", 0);
            // imprime el error en la consola
            System.out.println(ex);
        }
    }

    private void modificar(ArrayList<String> atributos, String tabla, int cod, String cd){
        String parameter = "";
        Iterator i = atributos.iterator();
        while(i.hasNext()){
            parameter += i.next() + ",";
        }
        parameter = parameter.substring(0,parameter.length()-1);
        
        String sql ;
        
        sql = "UPDATE bibliotecadb."+tabla+" SET "+parameter+" WHERE "+cd+" = "+cod;
        try {
            pps = this.ct.prepareStatement(sql);
            pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al intentar modificar datos", "Error de modificación", 0);
        }
    }
}
