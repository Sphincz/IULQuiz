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
	
	/** A ligacao a base de dados. */
	private Connection con;
	
	/**
	 * Construtor que cria e estabelece uma ligacao ao servidor da base de dados.
	 */
	public ISCTEBD() {
        // Connect to Sybase Database
        try {
			con = DriverManager.getConnection("jdbc:sqlanywhere:uid=Fenix;pwd=FenixDB;eng=ISCTEDB");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ISCTEDB - Base de dados desligada ou username e/ou password inv�lidos!");
			System.exit(0);
		}
	}
	
	public boolean Select(Utilizador user) {
		try {
			
			
			statement = con.createStatement();

			if(user.getCurso().equals("Curso: (opcional)")) {
				String selectStatement = "SELECT Nome FROM Aluno WHERE Aluno.Email = ? AND Aluno.Curso IS NULL";
				PreparedStatement prepStmt = con.prepareStatement(selectStatement);
				prepStmt.setString(1,user.getEmail());
				ResultSet rs = prepStmt.executeQuery();
				
				//result = statement.executeQuery("SELECT Nome FROM Aluno WHERE Aluno.Email = '"+user.getEmail()+"' AND Aluno.Curso IS NULL;");
				
				if(rs.next()) {
					user.Set_Nome(result.getString(1));
					System.out.println("Email existe na ISCTEDB.");
					boolean Email_Existente = true;
					prepStmt.close();
					rs.close();
					return Email_Existente;
				} else {
					System.out.println("Email n�o existe na ISCTEDB.");
					boolean Email_Nao_Existente = false;
					prepStmt.close();
					rs.close();
					return Email_Nao_Existente;
				}
			
			
			} else {
				
				String selectStatement = "SELECT Nome, Sigla_Curso, Email FROM Aluno WHERE Aluno.Email = ? AND Aluno.Curso = ?";
				PreparedStatement prepStmt = con.prepareStatement(selectStatement);
				prepStmt.setString(1,user.getEmail());
				prepStmt.setString(2,user.getCurso());
				ResultSet rs = prepStmt.executeQuery();
				
				
				//result = statement.executeQuery("SELECT Nome, Sigla_Curso, Email FROM Aluno WHERE Aluno.Email = '"+user.getEmail()+"' AND Aluno.Curso = '"+user.getCurso()+"';");
				
				if(rs.next()) {
					user.Set_Nome(rs.getString(1));
					user.Set_SiglaCurso(rs.getString(2));
					System.out.println("Email existe na ISCTEDB.");
					boolean Email_Existente = true;
					prepStmt.close();
					rs.close();
					return Email_Existente;
				} else {
					System.out.println("Email n�o existe na ISCTEDB.");
					boolean Email_Nao_Existente = false;
					prepStmt.close();
					rs.close();
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
