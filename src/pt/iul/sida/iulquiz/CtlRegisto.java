package pt.iul.sida.iulquiz;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CtlRegisto {

	private boolean Verifica_Existencia(Utilizador email) throws SQLException {
		DEstudante estudante = new DEstudante();
		estudante.Select(email);
		
		if(ISCTEBD.ItsAluno) {
			Select(email);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Utilizador não existente na base de dados do ISCTE!");
		}
		
		return false;
	}
	
	private void Select(Utilizador email) throws SQLException {
		if(Verifica_Existencia(email)) {
			QuizBD quizBD = new QuizBD();
			quizBD.Select(email);
			
			if(!QuizBD.EmailJaRegistado) {
				Insert(email);
			} else {
				JOptionPane.showMessageDialog(null, "Utilizador já existente na base de dados do IULQuiz!");
			}
		}
	}
	
	private void Insert(Utilizador email) throws SQLException {
		QuizBD quizBD = new QuizBD();
		quizBD.Insert(email);
		Confirmacao_Envia_Password(email);
	}
	
	private void Confirmacao_Envia_Password(Utilizador email) {
		Email sendEmail = new Email();
		sendEmail.sendPasswordTo(email);
	}
}
