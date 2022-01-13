import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestCon {

	public static void main(String[] args) throws SQLException {
	     
		System.out.println("------------DB connection Factory----------\n");
		
		ConnectionFactory conDB = new ConnectionFactory();
	
		try(Connection con = conDB.returnConnection()){
		
		con.setAutoCommit(false);
		//controle de transação, atomicidade ou adiciona todos ou dá rollback
	
		try( // try with resources autocloseable
				PreparedStatement stm = con.prepareStatement("insert into tbusu(nome,cpf) values (?,?)", Statement.RETURN_GENERATED_KEYS);//Prepared Statement against sql injection
				){
		
	
       
		AdicionaTBUSU(stm, "Maria", "00000");
        AdicionaTBUSU(stm, "José", "0005050505");
        AdicionaTBUSU(stm, "João Batista", "0005050505");
        AdicionaTBUSU(stm, "Pio", "0005050505");
        AdicionaTBUSU(stm, "José Maria Escrivá", "0005050505");
        AdicionaTBUSU(stm, "Afonso Maria", "0005050505");
        AdicionaTBUSU(stm, "Bento", "0005050505");
        AdicionaTBUSU(stm, "Teresinha de Jesus", "0005050505");
		
        con.commit(); // commit if the transaction was successful
        }
		catch (Exception e) {
		e.printStackTrace();
		System.out.println("Rollback executed");
		con.rollback();
		}
	}
}

	private static void AdicionaTBUSU(PreparedStatement stm, String nome, String cpf) throws SQLException {
        	stm.setString(1, nome);;
        	stm.setString(2, cpf);;
        	stm.execute();
        	
        	try(ResultSet rs = stm.getGeneratedKeys())
        	{while(rs.next()) {
        		System.out.println(rs.getInt(1));
        	}}
	}
	

}
