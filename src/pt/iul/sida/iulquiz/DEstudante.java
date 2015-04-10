package pt.iul.sida.iulquiz;

public class DEstudante {

	private String Email;
	private String Curso;
	private String Nome;
	private ISCTEBD iscteDB;
	
	public DEstudante() {
		iscteDB = new ISCTEBD();
	}
	
	/* METODO EXCLUIDO
	
	public void Select(Utilizador email) throws SQLException {};
	
	METODO EXCLUIDO */ 

	public boolean Verifica_Existencia(Utilizador user) {
		return iscteDB.Select(user);
	}
}
