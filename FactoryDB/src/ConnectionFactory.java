import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

String dbURL = "jdbc:mysql://localhost/database?userTimezone=true&serverTimezone=UTC";
DataSource dataSource;


  public ConnectionFactory() {
	  ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
	  comboPooledDataSource.setJdbcUrl(dbURL);
	  comboPooledDataSource.setUser("root");
	  comboPooledDataSource.setPassword("");
	  
	  comboPooledDataSource.setMaxPoolSize(15);
	  
	  this.dataSource = comboPooledDataSource;
  }

public Connection returnConnection() throws SQLException{
	return this.dataSource.getConnection(); //nome e senha 
}
}
