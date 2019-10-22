package textEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage myStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		Parent root = fxmlLoader.load();

		myStage.setTitle("Text Editor");
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("/css/caspian.css").toExternalForm());
		myStage.setScene(scene);
		myStage.show();
	}
}
