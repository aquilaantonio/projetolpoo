package controleView;

import java.net.URL;
import java.util.ResourceBundle;

import dao.RepositorioUsuario;
import fachada.Fachada;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;
import src.main.Index;

public class ControladorLogin implements Initializable {
    @FXML
    Button btCadastrar, btLogar;
    @FXML
    TextField txtLogin;
    @FXML
    PasswordField txtSenha;

    @FXML
    private void logar() throws Exception {
        Usuario u = new Usuario();
        u.setLogin(txtLogin.getText());
        u.setSenha(txtSenha.getText());
        try {
           
            if (Fachada.getInstance().pesquisaUsuario(u)) {

                Index.getMyInstance().start(Index.getMyStage(), ConstatantesTelas.TELA_MENU);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setHeaderText("Usuario não existe ");
                alert.setContentText("Informe o usuario Correto");
                alert.showAndWait();
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @FXML
    public void cadastrar() throws Exception {

        Stage s = new Stage();
        Index.getMyInstance().start(s, ConstatantesTelas.TELA_CADASTRO);

    }

    public void preparaTela() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}
