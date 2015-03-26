package pt.iul.sida.iulquiz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtPerguntaSecreta;
	private JTextField txtRespostaSegurana;
	private JTextField txtCurso;

	/**
	 * Create the frame.
	 */
	public GUI() {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setText("Email:                  @iscte.pt");
		txtEmail.setBounds(20, 25, 160, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPerguntaSecreta = new JTextField();
		txtPerguntaSecreta.setText("Pergunta Seguran\u00E7a:");
		txtPerguntaSecreta.setColumns(10);
		txtPerguntaSecreta.setBounds(20, 56, 160, 20);
		contentPane.add(txtPerguntaSecreta);
		
		txtRespostaSegurana = new JTextField();
		txtRespostaSegurana.setText("Resposta Seguran\u00E7a:");
		txtRespostaSegurana.setColumns(10);
		txtRespostaSegurana.setBounds(20, 87, 160, 20);
		contentPane.add(txtRespostaSegurana);
		
		txtCurso = new JTextField();
		txtCurso.setText("Curso (opcional)");
		txtCurso.setColumns(10);
		txtCurso.setBounds(20, 118, 160, 20);
		contentPane.add(txtCurso);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(335, 178, 89, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(236, 178, 89, 23);
		contentPane.add(btnCancelar);
		
		setTitle("Formulário de Registo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		setVisible(true);
	}
}
