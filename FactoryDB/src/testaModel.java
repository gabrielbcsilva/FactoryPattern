import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.UsuarioDAO;
import modelo.Usuario;

public class testaModel {
public static void main(String[] args) throws SQLException {

Connection conn = new ConnectionFactory().returnConnection();
UsuarioDAO userDao = new UsuarioDAO(conn);

Usuario user = new Usuario("Gabriel","0202065060");
userDao.Save(user);

List<Usuario> listUsers = userDao.listar();
listUsers.stream().forEach(u -> System.out.println(u));


}
}