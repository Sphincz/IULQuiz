package pt.iul.sida.iulquiz;

import java.sql.SQLException;
import java.util.Random;

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
	
	public boolean Confirma_Registo(Utilizador user) throws SQLException {
		boolean itsAluno = estudante.Verifica_Existencia(user);
		
		if(itsAluno) {
			boolean EmailJaRegistado = quizDB.Select(user);
			
			if(EmailJaRegistado) {
				System.out.println("Email Ja Registado!");
				JOptionPane.showMessageDialog(null, "O utilizador já existe na base de dados do IULQuiz!"
						+ "\nPor favor, efectue o Login.");
			
			} else {
				
				/** HEX PASSWORD GENERATOR */
				int passwordCharNumber = 8;
				Random r = new Random();
		        StringBuffer sb = new StringBuffer();
		        while(sb.length() < passwordCharNumber){
		            sb.append(Integer.toHexString(r.nextInt()));
		        }
		        String generatedPassword = sb.toString().substring(0, passwordCharNumber).toUpperCase();
		        /** HEX PASSWORD GENERATOR */
		        
				boolean RegistoEfetuado = quizDB.Insert(user, generatedPassword);
				
				if (RegistoEfetuado) {
					ctlEmail.Confirmacao_Envia_Password(user, generatedPassword);
					JOptionPane.showMessageDialog(null, "Bem-vindo ao IULQuiz!");
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao inserir o utilizador na base de dados!");
				}
					
			}
		} else {
			JOptionPane.showMessageDialog(null, "O utilizador nao existe na base de dados do ISCTE porque o email introduzido:"
					+ "\n- Não pertence à comunidade de utilizadores do ISCTE, ou"
					+ "\n- Não está associado ao curso introduzido."
					+ "\nCaso seja aluno e não possua curso associado, seleccione a opção por 'default'.");
		}
		return false;
	}
}
