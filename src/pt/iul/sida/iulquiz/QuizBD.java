package pt.iul.sida.iulquiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class QuizBD {

	/** O resultado de uma query. */
	private ResultSet result; 
	
	/** Uma query de acesso a base de dados. */
	private Statement statement;
	
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
			JOptionPane.showMessageDialog(null, "QuizBD - Base de dados desligada ou username e/ou password inválidos!");
			System.exit(0);
		}
	}
	
	public boolean Select(Utilizador user) {
		boolean Email_Ja_Registado;
		boolean Email_Nao_Existente;
			
		try {
			
			String selectStatement = "SELECT * FROM Estudante WHERE Estudante.Email_Aluno = ?";
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1,user.getEmail());
			ResultSet rs = prepStmt.executeQuery();
	
		if(rs.next()) {
			System.out.println("Email já registado na QuizBD.");
			Email_Ja_Registado = true;
			prepStmt.close();
			rs.close();
			return Email_Ja_Registado;
		} else {
			System.out.println("Email não registado na QuizBD.");
			Email_Nao_Existente = false;
			prepStmt.close();
			rs.close();
			return Email_Nao_Existente;
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na pesquisa de alunos na base de dados QuizBD.");
		}
		return false;
	}

	public synchronized boolean Insert(Utilizador user, String generatedPassword) {
		boolean Registo_Efectuado = false;
		
		try{
			
			PreparedStatement prepStmt;
				System.out.println(user.getSigla_Curso());
				String insertStatement = "INSERT INTO Estudante VALUES (?,?,?, ?, ?, ?,?)";
				prepStmt = con.prepareStatement(insertStatement);
				prepStmt.setString(1,user.getEmail());
				prepStmt.setString(2,user.getSigla_Curso());
				prepStmt.setString(3,user.getCurso());
				prepStmt.setString(4,user.getNome());
				prepStmt.setString(5,generatedPassword);
				prepStmt.setString(6,user.getPergunta());
				prepStmt.setString(7,user.getResposta());
				prepStmt.executeQuery();

			System.out.println("Insert com sucesso na QuizDB.");
			Registo_Efectuado = true;
			prepStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na inserção do utilizador na base de dados QuizBD.");
		}
		return Registo_Efectuado;	
	}
	
	
	/** METODO ADICIONADO */
	public Vector<String> SelectPerguntas() {
		Vector<String> list = new Vector<String>();
		list.add("Pergunta Segurança:");
		try {
			String selectStatement = "SELECT Pergunta_Seguranca FROM PerguntaSeguranca";
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
