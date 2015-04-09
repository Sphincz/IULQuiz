package pt.iul.sida.iulquiz;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class QuizBD {

	/** O resultado de uma query. */
	private ResultSet result; 
	
	/** Uma query de acesso a base de dados. */
	private Statement statement;
	
	/** O URL para estabelecer ligacao a base de dados. localhost2638 significa que o servidor da base de dados e local, ligado na porta 2638. O nome da base de dados e EatDrink. */
	private String dburl = "jdbc:sqlanywhere:Tds:localhost:2638?eng=QuizBD";
	
	/** O username e password de acesso a base de dados */
	private String user = "IULQuiz", password = "QuizIULDB";
	
	/** A ligacao a base de dados. */
	private Connection con;

	/** Variavel global que verifica se o email ja se encontra registado **/
	public static boolean EmailJaRegistado;
	
	/**
	 * Construtor que cria e estabelece uma ligacao ao servidor da base de dados.
	 */
	public QuizBD() {
        // Connect to Sybase Database
        try {
			con = DriverManager.getConnection("jdbc:sqlanywhere:uid=IULQuiz;pwd=QuizIULDB;eng=QuizBD");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Base de dados desligada ou username e/ou password inválidos!");
		}
	}
	
	public boolean Select(String email) {
		boolean Email_Ja_Registado;
		boolean Email_Nao_Existente;
			
		try {
		statement = con.createStatement();
		result = statement.executeQuery("SELECT * FROM Estudante WHERE Estudante.Email_Aluno = '"+email+"';");
		
		if(result.next()) {
			System.out.println("Email já registado na QuizBD.");
			Email_Ja_Registado = true;
			statement.close();
			result.close();
			return Email_Ja_Registado;
		} else {
			System.out.println("Email não registado na QuizBD.");
			Email_Nao_Existente = false;
			statement.close();
			result.close();
			return Email_Nao_Existente;
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na pesquisa de alunos na base de dados QuizBD.");
		}
		return false;
	}

	public synchronized boolean Insert(String email) {
		boolean Registo_Efectuado = false;
		try{
			statement = con.createStatement();
			statement.executeQuery("INSERT INTO Estudante VALUES ('"+email+"', 'LEI', 'Engenharia Informática', 'Farrobado', 'MEADAS');");
			
			System.out.println("Insert com sucesso na QuizDB.");
			Registo_Efectuado = true;
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na inserção do utilizador na base de dados QuizBD.");
		}
		return Registo_Efectuado;	
	}

}
