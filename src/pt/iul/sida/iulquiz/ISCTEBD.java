package pt.iul.sida.iulquiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ISCTEBD {

	/** O resultado de uma query. */
	private ResultSet result; 
	
	/** Uma query de acesso a base de dados. */
	private Statement statement;
	
	/** O URL para estabelecer ligacao a base de dados. localhost2638 significa que o servidor da base de dados e local, ligado na porta 2638. O nome da base de dados e EatDrink. */
	private String dburl = "jdbc:sqlanywhere:Tds:localhost:2638?eng=ISCTEBD";
	
	/** O username e password de acesso a base de dados */
	private String user = "Fenix", password = "FenixDB";
	
	/** A ligacao a base de dados. */
	private Connection con;
	
	/**
	 * Construtor que cria e estabelece uma ligacao ao servidor da base de dados.
	 */
	public ISCTEBD() {
        // Connect to Sybase Database
        try {
			con = DriverManager.getConnection(dburl, user, password);
		} catch (SQLException e) {
			System.out.println("ERRO na IULQUIZ");
			//JOptionPane.showMessageDialog(null, "Base de dados desligada ou username e/ou password inválidos!");
			System.exit(0);
			
		}
	}
	
	public boolean Select(String email) throws SQLException {
		statement = con.createStatement();
		result = statement.executeQuery("SELECT * FROM Aluno WHERE Aluno.Email = '"+email+"';");
		
		
		if(result.next()) {
			System.out.println("Email existe na ISCTEDB.");
			boolean Email_Existente = true;
			statement.close();
			result.close();
			return Email_Existente;
		} else {
			System.out.println("Email não existe na ISCTEDB.");
			boolean Email_Nao_Existente = false;
			statement.close();
			result.close();
			return Email_Nao_Existente;
		}
	}
}
