package src.main;



import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controleView.ConstatantesTelas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;


public class Index extends Application {
	
	private static final Index index = new Index() ;
	private static Stage stage =null ;
	private static Stage stageModal = null;
	public static Index getMyInstance() {
		return index;
	}
	
	public static Stage getMyStage() {
		if (stage ==null) {
			stage = new Stage();
		}
		return stage;
	}
	
	public static Stage getMyStageModal() {
		if (stageModal ==null) {
			stageModal = new Stage();
		}
		return stageModal;
	}
	public static void main(String[] args) throws Exception {
	launch();
		
	}
	
	public void start(Stage primaryStage,String local) throws Exception {
		  stage = primaryStage;
		  Parent fxmlParent = (Parent) FXMLLoader.load(getClass().getResource(local));
		  primaryStage.setScene(new Scene(fxmlParent));		 
		  primaryStage.show();
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		stage = arg0;
		Parent fxmlParent = (Parent) FXMLLoader.load(getClass().getResource(ConstatantesTelas.TELA_LOGIN));
		arg0.setScene(new Scene(fxmlParent, 400, 400));
		arg0.setTitle("Login");
		arg0.show();
		
	}
	public void showModal(Stage primaryStage,String local) throws Exception {
		  stageModal = primaryStage;
		  Parent fxmlParent = (Parent) FXMLLoader.load(getClass().getResource(local));
		  primaryStage.setScene(new Scene(fxmlParent));
		  primaryStage.initModality(Modality.WINDOW_MODAL);
		  primaryStage.show();
		
	}
	


}
