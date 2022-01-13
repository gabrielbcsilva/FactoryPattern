import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
String dbURL = "jdbc:mysql://localhost/database?userTimezone=true&serverTimezone=UTC";

public Connection returnConnection() throws SQLException{
	return DriverManager.getConnection(dbURL, "",""); //nome e senha 
}
}
