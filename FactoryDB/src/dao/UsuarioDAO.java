package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class UsuarioDAO {
//Percistencia, essa classe é responsável por acessar os dados da tabela Data Acess Object(DAO)
	private Connection conn;

	public UsuarioDAO(Connection connection) {
		this.conn = connection;
	}

	public void Save(Usuario user) throws SQLException {
		conn.setAutoCommit(false);
		try ( // try with resources autocloseable
				PreparedStatement stm = conn.prepareStatement("insert into tbusu(nome,cpf) values (?,?)",
						Statement.RETURN_GENERATED_KEYS);// Prepared Statement against sql injection
		) {

			prepareUser(stm, user);
			conn.commit(); // commit if the transaction was successful
			System.out.println("User inserted");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Rollback executed");
			conn.rollback();
		}
	}
	public List<Usuario> listar() throws SQLException{
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT * FROM TBUSU";
		try(PreparedStatement pstm = conn.prepareStatement(sql)){
			pstm.execute();
			try(ResultSet rs = pstm.getResultSet()){
				while (rs.next()) {
					Usuario usuario = new Usuario(
							rs.getInt(1), rs.getString(2), rs.getString(3)
							);
					usuarios.add(usuario);
				}
			}
		}
		
		return usuarios;
		
	}
	private static void prepareUser(PreparedStatement stm, Usuario user) throws SQLException {
		stm.setString(1, user.getNome());
		
		stm.setString(2, user.getCpf());
		
		stm.execute();

		try (ResultSet rs = stm.getGeneratedKeys()) {
			while (rs.next()) {

				user.setId(rs.getInt(1));
			}
		}
	}
}
