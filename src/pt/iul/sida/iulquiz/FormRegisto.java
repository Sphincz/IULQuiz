package pt.iul.sida.iulquiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FormRegisto extends JFrame {

	private static final long serialVersionUID = 1L;
	private Utilizador user;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtPerguntaSecreta;
	private JTextField txtRespostaSegurana;
	private JComboBox<String> txtCurso;
	private CtlRegisto controlador;
	private String[] cursos = {"Curso: (opcional)","Arquitectura","Antropologia","Ciência Política","Economia",
							   "Engenharia de Telecomunicações e Informática", "Engenharia Informática","Finanças e Contabilidade",
							   "Gestão","Gestão de Marketing","Gestão de Recursos Humanos","Gestão Industrial e Logística",
							   "Gestão e Engenharia Industrial","História Moderna e Contemporânea","Informática e Gestão de Empresas",
							   "Psicologia","Serviço Social","Sociologia"};
	
	public static void main(String[] args) {
		new FormRegisto();
	}

	/**
	 * Create the frame.
	 */
	public FormRegisto() {
		user = new Utilizador();
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		controlador = new CtlRegisto();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setText("Email:                  @iscte.pt");
		txtEmail.setBounds(20, 25, 250, 20);
		txtEmail.setColumns(10);
		contentPane.add(txtEmail);
		
		txtEmail.addMouseListener(new MouseAdapter(){
            int clearTimes = 1;
            public void mousePressed(MouseEvent e){
            	if(clearTimes == 1) {
            		clearTimes = 0;
            		txtEmail.setText("");
            	}
            }
        });
		
		txtPerguntaSecreta = new JTextField();
		txtPerguntaSecreta.setText("Pergunta Seguran\u00E7a:");
		txtPerguntaSecreta.setColumns(10);
		txtPerguntaSecreta.setBounds(20, 56, 250, 20);
		contentPane.add(txtPerguntaSecreta);
		
		txtPerguntaSecreta.addMouseListener(new MouseAdapter(){
            int clearTimes = 1;
            public void mousePressed(MouseEvent e){
            	if(clearTimes == 1) {
            		clearTimes = 0;
            		txtPerguntaSecreta.setText("");
            	}
            }
        });
		
		txtRespostaSegurana = new JTextField();
		txtRespostaSegurana.setText("Resposta Seguran\u00E7a:");
		txtRespostaSegurana.setColumns(10);
		txtRespostaSegurana.setBounds(20, 87, 250, 20);
		contentPane.add(txtRespostaSegurana);
		
		txtRespostaSegurana.addMouseListener(new MouseAdapter(){
            int clearTimes = 1;
            public void mousePressed(MouseEvent e){
            	if(clearTimes == 1) {
            		clearTimes = 0;
            		txtRespostaSegurana.setText("");
            	}
            }
        });
		
		txtCurso = new JComboBox<String>(cursos);
		txtCurso.setBounds(20, 118, 250, 20);
		contentPane.add(txtCurso);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(385, 178, 89, 23);
		contentPane.add(btnConfirmar);
		
		btnConfirmar.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				user.Set_Email(txtEmail.getText());
				user.Set_Pergunta(txtPerguntaSecreta.getText());
				user.Set_Resposta(txtRespostaSegurana.getText());
				user.Set_Curso(txtCurso.getSelectedItem().toString());
				
				try {
					Confirma_Registo(user);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Base de dados desligada ou username e/ou password inválidos!");
					e.printStackTrace();
					System.exit(0);
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(286, 178, 89, 23);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		setTitle("Formulário de Registo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		setVisible(true);
	}

	private void Confirma_Registo(Utilizador user) throws SQLException {
		if(controlador.Confirma_Registo(user)) {
			System.exit(0);
		}
	}
}
