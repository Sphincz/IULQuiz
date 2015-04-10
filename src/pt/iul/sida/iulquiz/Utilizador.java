package pt.iul.sida.iulquiz;

public class Utilizador {

	private String Email;
	private String Pergunta;
	private String Curso;
	private String Resposta;
	
	/** Atributos adicionais para guardar dados nas pesquisas a BD **/
	private String Nome;
	private String siglaCurso;
	
	
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
	public void Set_Nome(String nome) {
		Nome = nome;
	}
	public void Set_SiglaCurso(String Sigla_Curso) {
		siglaCurso = Sigla_Curso;
	}
	public String getEmail() {
		return Email;
	}
	public String getPergunta() {
		return Pergunta;
	}
	public String getCurso() {
		return Curso;
	}
	public String getResposta() {
		return Resposta;
	}
	public String getNome() {
		return Nome;
	}
	public String getSigla_Curso() {
		return siglaCurso;
	}
	
	/* METODO EXCLUIDO
	public boolean Confirma_Registo() {
		return false;
	}
	METODO EXCLUIDO */
}
