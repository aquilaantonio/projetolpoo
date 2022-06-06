package controleView;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import fachada.Fachada;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Despesas;
import model.Receita;
import model.Util;

public class ControladorRelatorio implements Initializable {
 
    @FXML
    private Button btPesquisar;
    @FXML
    private TextField txtTotalizador, txtMes, txtAno;
    @FXML
    private TableView<Receita> tvListaReceita;
    @FXML
    private TableView<Despesas> tvListaDespesa;

    TableColumn colunaNome = new TableColumn<>("Nome");
    TableColumn colunaValor = new TableColumn<>("Valor");
    TableColumn colunaNome2 = new TableColumn<>("Nome");
    TableColumn colunaValor2 = new TableColumn<>("Valor");

    @FXML
    public void pesquisar() throws ParseException {

        try {
            if (!txtMes.getText().isEmpty() && !txtAno.getText().isEmpty()) {
                carregarListas();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Informe um Mes e Ano");
                alert.showAndWait();
            }

        } catch (Exception e1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro montar Relatorio");
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
        Util.addMaskInteiro(txtMes, 2);
        Util.addMaskInteiro(txtAno, 4);

    }

    public void adicionaColuna() {

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        colunaNome2.setCellValueFactory(new PropertyValueFactory<>("nome"));

        colunaValor2.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tvListaReceita.getColumns().addAll(colunaNome2, colunaValor2);
        tvListaDespesa.getColumns().addAll(colunaNome, colunaValor);

    }

    private void carregarListas() {
        

        List<Receita> listaReceita = Fachada.getInstance().buscarTodasReceitas();
  
        List<Despesas> listaDespesas = Fachada.getInstance().buscarTodasDespesas("", null);
        List<Despesas> ld = new ArrayList<Despesas>();
        List<Receita> lr = new ArrayList<Receita>();
        double somaDespesa = 0;
        double somaReceita = 0;
        Calendar c = Calendar.getInstance();  
        c.set(Integer.parseInt(txtAno.getText()), Integer.parseInt(txtMes.getText())-1, 01);
       
        for (Despesas despesas : listaDespesas) {
            if (despesas.getData().getMonth() == c.getTime().getMonth() &&
                    despesas.getData().getYear() == c.getTime().getYear()) {
                ld.add(despesas);
                somaDespesa += despesas.getValor();
            }
        }

        for (Receita receita : listaReceita) {
        	    int mesRinicio = receita.getDataIncio().getMonth();
        	    int anoRinicio =  receita.getDataIncio().getYear();
            if ((mesRinicio == c.getTime().getMonth() && anoRinicio == c.getTime().getYear()) || (receita.getDataFim() !=null &&
            		receita.getDataFim().getMonth() >= c.getTime().getMonth() && receita.getDataFim().getYear() == c.getTime().getYear() )                  
            		) {
            	
                lr.add(receita);
                somaReceita += receita.getValor();
            }
        }

        tvListaReceita.setItems(FXCollections.observableList(lr));
        tvListaDespesa.setItems(FXCollections.observableList(ld));
        somaReceita -= somaDespesa;
        txtTotalizador.setText(String.valueOf(somaReceita));
    }
}
