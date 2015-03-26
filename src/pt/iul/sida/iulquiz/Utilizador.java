package pt.iul.sida.iulquiz;

public class Utilizador implements FormRegisto {

	private String Email;
	private String Pergunta;
	private String Curso;
	private String Resposta;
	
	public void Set_Email(String email) {
		Email = email;
	}
	public void Set_Pergunta(String pergunta) {
		Pergunta = pergunta;
	}
	public void Set_Curso(String curso) {
		Curso = curso;
	}
	public void Set_Resposta(String resposta) {
		Resposta = resposta;
	}
	
	@Override
	public boolean Confirma_Registo() {
		return false;
	}
	
	@Override
	public FormRegisto new_Formulario() {
		return null;
	}
	
}
