package controleView;

import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import dao.RepositorioReceita;
import fachada.Fachada;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Receita;
import src.main.Index;

public class ControladorListarReceita implements Initializable {
   
    @FXML
    private Button btPesquisar;
    @FXML
    private TextField txtValor;
    @FXML
    private TableView<Receita> tvLista;

    @FXML
    private RadioButton rbPeriodos, rbTipos, rbValor;

    ToggleGroup tgRadios = new ToggleGroup();

    TableColumn colunaNome = new TableColumn<>("Nome");
    TableColumn colunaData = new TableColumn<>("Data");
    TableColumn colunaDataFim = new TableColumn<>("DataFim");
    TableColumn colunaValor = new TableColumn<>("Valor");
    TableColumn colunaTipo = new TableColumn<>("Tipo");
    TableColumn colunaPeriodo = new TableColumn<>("Periodo");

    @FXML
    public void pesquisar() throws ParseException {

        try {

            List<Receita> listaReceita = Fachada.getInstance().buscarTodasReceitas();

            tvLista.setItems(FXCollections.observableList(listaReceita));
            this.cancelar();
        } catch (Exception e1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao Pesquisar Receitas");
            alert.setContentText(e1.getMessage());

            alert.showAndWait();
        }
    }

    public void init() {

    }

    @FXML
    public void cancelar() {

        try {
            // Index.getMyInstance().start(Index.getMyStage(),ConstatantesTelas.TELA_LOGIN);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adicionaColuna();
        // rbPeriodos = new RadioButton();
        // rbTipos= new RadioButton();
        // rbValor= new RadioButton();

    }

    public void adicionaColuna() {

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataIncio"));
        colunaDataFim.setCellValueFactory(new PropertyValueFactory<>("dataFim"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
        tvLista.getColumns().addAll(colunaNome, colunaData, colunaDataFim, colunaTipo, colunaValor, colunaPeriodo);

    }

    @FXML
    private void delete() {
        if (tvLista.getSelectionModel() != null) {
            try {
                Receita receita = tvLista.getSelectionModel().getSelectedItem();
               
                Fachada.getInstance().deleteReceita(receita);
                pesquisar();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

    @FXML
    private void alterar() {
        if (tvLista.getSelectionModel() != null) {
            try {
                final Receita receita = tvLista.getSelectionModel().getSelectedItem();
                final ControladorCadastroReceita cr = new ControladorCadastroReceita();
                cr.setReceita(receita);
                Index.getMyInstance().showModal(new Stage(), ConstatantesTelas.TELA_CADASTRORECEITA);
                pesquisar();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

}
