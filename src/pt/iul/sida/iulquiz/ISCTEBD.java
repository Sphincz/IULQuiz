package pt.iul.sida.iulquiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ISCTEBD {

	/** O resultado de uma query. */
	private ResultSet result;
	
	/** Uma query de acesso a base de dados. */
	private Statement statement;
	
	/** O URL para estabelecer ligacao a base de dados. localhost2638 significa que o servidor da base de dados e local, ligado na porta 2638. O nome da base de dados e ISCTEDB. */
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
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ISCTEDB - Base de dados desligada ou username e/ou password inválidos!");
		}
	}
	
	public boolean Select(Utilizador user) {
		try {
			statement = con.createStatement();

			if(user.getCurso().equals("Curso: (opcional)")) {
				
				result = statement.executeQuery("SELECT Nome FROM Aluno WHERE Aluno.Email = '"+user.getEmail()+"' AND Aluno.Curso IS NULL;");
				
				if(result.next()) {
					user.Set_Nome(result.getString(1));
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
			
			
			} else {
				
				result = statement.executeQuery("SELECT Nome, Sigla_Curso, Email FROM Aluno WHERE Aluno.Email = '"+user.getEmail()+"' AND Aluno.Curso = '"+user.getCurso()+"';");
				
				if(result.next()) {
					user.Set_Nome(result.getString(1));
					user.Set_SiglaCurso(result.getString(2));
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
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na procura do utilizador na base de dados ISCTEDB.");
		}
		return false;
	}
}
