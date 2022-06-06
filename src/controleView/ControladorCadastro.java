package controleView;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import dao.RepositorioUsuario;
import fachada.Fachada;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Usuario;
import src.main.Index;

public class ControladorCadastro  implements Initializable {

	@FXML
	Button btSalvar , btCacel;
	@FXML
	TextField txtLogin;
	@FXML
	PasswordField txtSenha;
	

	
	@FXML
	public void salvar() {
	  Usuario u = new Usuario();
  	  u.setLogin(txtLogin.getText());
  	  u.setSenha(txtSenha.getText());
  	  try {
  		Fachada.getInstance().incluirUsuario(u);
		this.voltar();	
		} catch (Exception e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Erro ao cadastrar usuario");
			alert.setContentText(e1.getMessage());

			alert.showAndWait();
		}
	}
	

	@FXML
	public void voltar() {
	
	try {
		Index.getMyInstance().start(Index.getMyStage(),ConstatantesTelas.TELA_LOGIN);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
