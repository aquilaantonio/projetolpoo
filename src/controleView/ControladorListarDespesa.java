package controleView;

import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import dao.RepositorioDespesa;
import fachada.Fachada;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Despesas;
import model.Periodos;
import model.Tipo;
import model.TipoDespesa;

public class ControladorListarDespesa implements Initializable {
   
    @FXML
    private Button btPesquisar;
    @FXML
    private TextField txtValor;
    @FXML
    private TableView<Despesas> tvLista;
    @FXML
    private ComboBox<Periodos> cbPeriodo = new ComboBox<Periodos>();
    @FXML
    private ComboBox<TipoDespesa> cbTipo = new ComboBox<TipoDespesa>();
    @FXML
    private RadioButton rbPeriodos, rbTipos, rbValor;

    ToggleGroup tgRadios = new ToggleGroup();

    TableColumn colunaNome = new TableColumn<>("Nome");
    TableColumn colunaData = new TableColumn<>("Data");
    TableColumn colunaValor = new TableColumn<>("Valor");
    TableColumn colunaTipo = new TableColumn<>("Tipo");
    TableColumn colunaPeriodo = new TableColumn<>("Periodo");

    @FXML
    public void pesquisar() throws ParseException {
        String tipoConsulta = null;
        String valor = null;
        try {
            if (rbPeriodos.isSelected()) {
                tipoConsulta = "PERIODO";
                valor = cbPeriodo.getValue().toString();
            } else if (rbTipos.isSelected()) {
                tipoConsulta = "TIPO";
                valor = cbTipo.getValue().toString();
            } else {
                tipoConsulta = "VALOR";
                valor = txtValor.getText();
            }
            if (valor == null && valor.isEmpty()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Informe um filtro");
                alert.showAndWait();

            } else {

                List<Despesas> listaDespesa = Fachada.getInstance().buscarTodasDespesas(tipoConsulta, valor);
                if (listaDespesa != null) {

                    tvLista.setItems(FXCollections.observableList(listaDespesa));
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("N�o Ha despesas para esta consulta");
                    alert.showAndWait();
                }
            }
        } catch (Exception e1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao Pesquisar Despesas");
            alert.setContentText(e1.getMessage());

            alert.showAndWait();
        }
    }

    public void init() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbPeriodo.setItems(FXCollections.observableArrayList(Periodos.values()));
        cbTipo.setItems(FXCollections.observableArrayList(TipoDespesa.values()));
        adicionaColuna();
        rbPeriodos.setSelected(true);

    }

    public void adicionaColuna() {

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
        tvLista.getColumns().addAll(colunaNome, colunaData, colunaTipo, colunaValor, colunaPeriodo);

    }

    @FXML
    private void delete() {
        if (tvLista.getSelectionModel() != null) {
            try {
                Despesas despesa = tvLista.getSelectionModel().getSelectedItem();                
                Fachada.getInstance().deleteDespesa(despesa);               
                pesquisar();
            } catch (Exception e) {
               e.printStackTrace();
            }
        }

    }

}
