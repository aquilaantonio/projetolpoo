package controleView;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TextField;
import model.Despesas;
import model.Periodos;
import model.Tipo;
import model.TipoDespesa;
import model.Util;
import src.main.Index;

public class ControladorCadastroDespesa implements Initializable {

    @FXML
    Button btCadastrarDespesa, btCancelar;
    @FXML
    TextField nomeDespesa, dataDespesa, valorDespesa, numParcelaDespesa;
    @FXML
    ComboBox<TipoDespesa> cbTipo = new ComboBox<TipoDespesa>();
    @FXML
    ComboBox<Periodos> cbPeriodo = new ComboBox<Periodos>();

    @FXML
    public void salvar() throws ParseException {

        try {
            Despesas despesa = criarDespesa();
            if (despesa != null) {

               Fachada.getInstance().saveDespesa(despesa);
                
                this.cancelar();
            }
        } catch (Exception e1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao cadastrar Despesa");
            alert.setContentText(e1.getMessage());

            alert.showAndWait();
        }
    }

    public void init() {

    }

    @FXML
    public void cancelar() {

        try {
            Index.getMyStageModal().hide();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static java.util.Date convertFromSQLDateToJAVADate(java.sql.Date sqlDate) {
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return sqlDate;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbPeriodo.setItems(FXCollections.observableArrayList(Periodos.values()));
        cbTipo.setItems(FXCollections.observableArrayList(TipoDespesa.values()));
        Util.addMaskInteiro(numParcelaDespesa, 2);
        Util.monetaryField(valorDespesa);
        Util.dateField(dataDespesa);
        numParcelaDespesa.setText("0");

    }

    private Despesas criarDespesa() {

        SimpleDateFormat formatador = null;
        formatador = new SimpleDateFormat("dd/MM/yyyy");
        boolean vazio = false;
        Despesas despesa = new Despesas();
        try {
            if (!nomeDespesa.getText().isEmpty()) {
                despesa.setNome(nomeDespesa.getText());
            } else {
                vazio = true;
            }
            if (!dataDespesa.getText().isEmpty()) {
                despesa.setData(formatador.parse(dataDespesa.getText()));
            } else {
                vazio = true;
            }
            if (cbTipo.getSelectionModel().getSelectedIndex() > -1) {
                despesa.setTipo(cbTipo.getValue().toString());
            } else {
                vazio = true;
            }
            if (!valorDespesa.getText().isEmpty()) {
                despesa.setValor(Double.parseDouble(valorDespesa.getText()));
            } else {
 	
            	vazio = true;
            }

            if (!numParcelaDespesa.getText().isEmpty()) {
                despesa.setNumeroParcela(Integer.parseInt(numParcelaDespesa.getText()));
            } else {
                vazio = true;
            }

            if (cbPeriodo.getSelectionModel().getSelectedIndex() > -1) {
                despesa.setPeriodo(cbPeriodo.getValue().toString());
            } else {
                vazio = true;
            }

            if (vazio) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Verifique se todos os campoes est�o Preenchidos todos os campos");
                alert.showAndWait();
                despesa = null;
            }
        } catch (NumberFormatException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return despesa;
    }
}
