package pt.iul.sida.iulquiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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

	/** Variavel global que verifica se o email introduzido se trata de um aluno ou nao na BD **/
	public static boolean ItsAluno;
	
	/**
	 * Construtor que cria e estabelece uma ligacao ao servidor da base de dados.
	 */
	public ISCTEBD() {
        // Connect to Sybase Database
        try {
			con = DriverManager.getConnection(dburl, user, password);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Base de dados desligada ou username e/ou password inválidos!");
			System.exit(0);
			
		}
	}
	
	public boolean Select(Utilizador email) throws SQLException {
		ItsAluno = false;
		statement = con.createStatement();
		result = statement.executeQuery("SELECT * FROM Alunos WHERE Alunos.email = "+email+";");
		
		if(result.next()) {	
			// Email_Existente = ItsAluno==true
			ItsAluno = true;
			return ItsAluno;
		} else {
			// Email_Nao_Existente = ItsAluno==false
			ItsAluno = false;
			return ItsAluno;
		}
	}
}
