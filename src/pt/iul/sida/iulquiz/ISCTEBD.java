package pt.iul.sida.iulquiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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
			JOptionPane.showMessageDialog(null, "ISCTEDB - Base de dados desligada ou username e/ou password inválidos!");
			System.exit(0);
		}
	}
	
	public boolean Select(Utilizador user) {
		try {
			
			
			statement = con.createStatement();
				
				String selectStatement = "SELECT Nome, Sigla_Curso, Email FROM Aluno WHERE Aluno.Email = ? AND Aluno.Curso = ?";
				PreparedStatement prepStmt = con.prepareStatement(selectStatement);
				prepStmt.setString(1,user.getEmail());
				prepStmt.setString(2,user.getCurso());
				ResultSet rs = prepStmt.executeQuery();
						
				if(rs.next()) {
					user.Set_Nome(rs.getString(1));
					user.Set_SiglaCurso(rs.getString(2));
					System.out.println("Email existe na ISCTEDB.");
					boolean Email_Existente = true;
					prepStmt.close();
					rs.close();
					return Email_Existente;
				} else {
					System.out.println("Email não existe na ISCTEDB.");
					boolean Email_Nao_Existente = false;
					prepStmt.close();
					rs.close();
					return Email_Nao_Existente;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na procura do utilizador na base de dados ISCTEDB.");
		}
		return false;
	}
	
	/** METODO ADICIONADO */
	public Vector<String> SelectCursos() {
		Vector<String> list = new Vector<String>();
		try {
			String selectStatement = "SELECT Designacao_Curso FROM Curso ORDER BY Sigla_Curso";
			statement = con.createStatement();
			result = statement.executeQuery(selectStatement);
			
			while(result.next()) {
				list.add(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na procura de cursos na base de dados ISCTEDB.");
			System.exit(0);
		}
		return list;
	}
	/** METODO ADICONADO */
}
