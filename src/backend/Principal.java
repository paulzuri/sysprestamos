package backend;

import frontend.Login;
import java.sql.SQLException;

/**
 * @author dacol
 */
public class Principal {

    public static void main(String[] args) throws SQLException {
        Login login = new Login();
        login.setVisible(true);
    }
    
}
