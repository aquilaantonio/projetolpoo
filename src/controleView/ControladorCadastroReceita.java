package controleView;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import dao.RepositorioReceita;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Periodos;
import model.Receita;
import model.Tipo;
import model.Util;
import src.main.Index;

public class ControladorCadastroReceita implements Initializable {
    public boolean modoAltera = false;
    @FXML
    Button btCadastrar, btCancelar;
    @FXML
    TextField nome, dataInicio, valor, dataFim;
    @FXML
    ComboBox<Tipo> cbTipo = new ComboBox<Tipo>();
    @FXML
    ComboBox<Periodos> cbPeriodo = new ComboBox<Periodos>();
    @FXML
    Label titulo;
    private static Receita receitaUpdate = null;

    private Integer idUpdate;

    @FXML
    public void salvar() throws ParseException {

        Receita receita = criarReceita();

        try {
            if (receita != null) {

                RepositorioReceita repo = new RepositorioReceita();
                repo.init();
                repo.incluir(receita);
                this.cancelar();
            }
        } catch (Exception e1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao cadastrat usuaro");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbPeriodo.setItems(FXCollections.observableArrayList(Periodos.values()));
        cbTipo.setItems(FXCollections.observableArrayList(Tipo.values()));
        Util.dateField(dataFim);
        Util.dateField(dataInicio);
        Util.monetaryField(valor);
        modoTela();

    }

    public static void setReceita(Receita receita) {

        if (receita != null) {
            ControladorCadastroReceita.receitaUpdate = receita;

        }

    }

    private void modoTela() {
        if (receitaUpdate != null) {
            nome.setText(receitaUpdate.getNome());
            dataInicio.setText(Util.converterDataParaStringSemHora(receitaUpdate.getDataIncio()));
            if (receitaUpdate.getDataFim()!=null) {				
            	dataFim.setText(Util.converterDataParaStringSemHora(receitaUpdate.getDataFim()));
			}
            valor.setText(String.valueOf(receitaUpdate.getValor()));
            cbTipo.getSelectionModel().select(Tipo.valueOf(receitaUpdate.getTipo()));
            cbPeriodo.getSelectionModel().select(Periodos.valueOf(receitaUpdate.getPeriodo()));
            idUpdate = receitaUpdate.getId();
            receitaUpdate = null;
            titulo.setText("Atualizar Receita");
        }
    }

    private Receita criarReceita() {

        SimpleDateFormat formatador = null;
        formatador = new SimpleDateFormat("dd/MM/yyyy");

        Receita receita = new Receita();
        if (idUpdate != null) {
            receita.setId(idUpdate);
        }
        boolean vazio = false;
        try {
            if (!nome.getText().isEmpty()) {
                receita.setNome(nome.getText());
            } else {
                vazio = true;
            }
            if (!dataInicio.getText().isEmpty()) {
                receita.setDataIncio(formatador.parse(dataInicio.getText()));
            } else {
                vazio = true;
            }
            if (!dataFim.getText().isEmpty()) {
                receita.setDataFim(formatador.parse(dataFim.getText()));
            }
            if (cbTipo.getSelectionModel().getSelectedIndex() > -1) {
                receita.setTipo(cbTipo.getValue().toString());
            } else {
                vazio = true;
            }
            if (!valor.getText().isEmpty()) {
                receita.setValor(Double.parseDouble(valor.getText()));
            } else {
                vazio = true;
            }

            if (cbPeriodo.getSelectionModel().getSelectedIndex() > -1) {
                receita.setPeriodo(cbPeriodo.getValue().toString());
            } else {
                vazio = true;
            }

            if (vazio) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Verifique se todos os campoes est�o Preenchidos todos os campos");
                alert.showAndWait();
                receita = null;
            }
        } catch (NumberFormatException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return receita;
    }

}
