package pt.iul.sida.iulquiz;

import java.sql.SQLException;

public class DEstudante {

	private String Email;
	private String Curso;
	private String Nome;
	
	public void Select(Utilizador email) throws SQLException {
		ISCTEBD iscteBD = new ISCTEBD();
		iscteBD.Select(email);
	}
}
