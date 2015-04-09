package pt.iul.sida.iulquiz;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CtlRegisto {
	
	private DEstudante estudante;
	private QuizBD quizDB;
	private Email ctlEmail;
	

	public CtlRegisto() {
		estudante = new DEstudante();
		quizDB = new QuizBD();
		ctlEmail = new Email();
	}

	/* METODO EXCLUIDO
	private boolean Verifica_Existencia(Utilizador email) throws SQLException {};
	METODO EXCLUIDO */
	
	/* METODO EXCLUIDO
	private void Select(String email) throws SQLException {};
	METODO EXCLUIDO */
	
	/* METODO EXCLUIDO 
	private void Insert(Utilizador email) throws SQLException {};
	METODO EXCLUIDO */
	
	/* METODO EXCLUIDO
	private void Confirmacao_Envia_Password(Utilizador email) {};
	METODO EXCLUIDO */
	
	public boolean Confirma_Registo(String email) throws SQLException {
		boolean itsAluno = estudante.Verifica_Existencia(email);
		
		if(itsAluno) {
			boolean EmailJaRegistado = quizDB.Select(email);
			
			if(EmailJaRegistado) {
				System.out.println("Email Ja Registado!");
				JOptionPane.showMessageDialog(null, "O utilizador já existe na base de dados do IULQuiz!");
			
			} else {
				boolean RegistoEfetuado = quizDB.Insert(email);
				
				if (RegistoEfetuado) {
					String generatedPassword = String.format("%x",(int)(Math.random()*100));
					System.out.println(generatedPassword);
					ctlEmail.Confirmacao_Envia_Password(email, generatedPassword);
					JOptionPane.showMessageDialog(null, "Bem-vindo ao IULQuiz!");
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir o utilizador na base de dados!");
				}
					
			}
		} else {
			JOptionPane.showMessageDialog(null, "O utilizador nao existe na base de dados do ISCTE!");
		}
		return false;
	}
}
