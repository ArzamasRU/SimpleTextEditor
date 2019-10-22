package textEditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {

	@FXML
	private TextArea textArea;

	private Stage stage;
	private File file;
	private final FileChooser fileChooser = new FileChooser();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Text", "*.txt"),
						new FileChooser.ExtensionFilter("All Files", "*.*"));
	}

	@FXML
	private void close() {
		if (textArea.getText().isEmpty()) {
			Platform.exit();
			return;
		}

		Alert alert = new Alert(Alert.AlertType.NONE, "Exit without saving?", ButtonType.YES,
				ButtonType.NO, ButtonType.CANCEL);

		alert.setTitle("Confirm");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			Platform.exit();
		}
		if (alert.getResult() == ButtonType.NO) {
			save();
			Platform.exit();
		}
	}

	@FXML
	private void saveAs() {
		try {
			fileChooser.setTitle("Save As");
			file = fileChooser.showSaveDialog(stage);

			if (file != null) {
				BufferedWriter out = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
				out.write(textArea.getText());
				out.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void save() {
		if (file != null) {
			try {
				BufferedWriter out = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
				out.write(textArea.getText());
				out.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error: " + e);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			saveAs();
		}
	}

	@FXML
	private void openFile() {
		fileChooser.setTitle("Open File");
		File file = fileChooser.showOpenDialog(stage);

		if (file != null) {
			textArea.clear();
			readText(file);
		}
	}

	private void readText(File file) {
		String text;

		try (BufferedReader buffReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
			while ((text = buffReader.readLine()) != null) {
				textArea.appendText(text + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedReader buffReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
			while ((text = buffReader.readLine()) != null) {
				textArea.appendText(text + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void newFile() {
		textArea.clear();
	}
}
