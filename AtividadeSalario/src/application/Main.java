package application;
	
import com.ifsc.tds.controller.SalarioController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/view/SalarioView.fxml"));
			Parent arquivoXML = loader.load();
			
			primaryStage.setScene(new Scene(arquivoXML));
			primaryStage.setTitle("Aumento salário");
			
			SalarioController SalarioController = loader.getController();
			
			primaryStage.setOnCloseRequest(e -> {
				if(SalarioController.onCloseQuery()) {
					System.exit(0);
				} else {
					e.consume();
				}
				
			});
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}