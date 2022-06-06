package controleView;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;
import src.main.Index;

public class ControladorMenu implements Initializable {

	@FXML
	MenuBar menu;
	@FXML
	Menu menuDespesa,menuReceita,menuRelatorio; 
	@FXML
	MenuItem novaDespesa,pesquisarDespesa,excluirDespesa,novaReceita,pesquisarReceita,excluirReceita,alteraReceita,exibirRelatorio,sair;
	
	@FXML
public void cadastroDespesa(){
	try {
		Index.getMyInstance().showModal(new Stage(),ConstatantesTelas.TELA_CADASTRODESPESA);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	@FXML
public void listarDespesa(){
	try {
		Index.getMyInstance().showModal(new Stage(),ConstatantesTelas.TELA_LISTADESPESA);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	
	@FXML
public void cadastroReceita(){
	try {
		Index.getMyInstance().showModal(new Stage(),ConstatantesTelas.TELA_CADASTRORECEITA);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	@FXML
public void listarReceita(){
	try {
		Index.getMyInstance().showModal(new Stage(),ConstatantesTelas.TELA_LISTARECEITA);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@FXML	
private void sair(){
	Index.getMyStage().hide();
}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}

@FXML
public void exibirRelatorio(){
try {
	Index.getMyInstance().showModal(new Stage(),ConstatantesTelas.TELA_RELATORIO);

} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}




}
