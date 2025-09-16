package br.edu.progiitrabalho;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // carrega o FXML DIRETO do build (target/classes)
            Path fxmlPath = Paths.get("target/classes/br/edu/progiitrabalho/cadastro.fxml").toAbsolutePath();
            System.out.println("Carregando FXML de: " + fxmlPath);
            if (!Files.exists(fxmlPath)) {
                throw new IllegalStateException("FXML não existe em: " + fxmlPath);
            }
            URL url = fxmlPath.toUri().toURL();
            Parent root = FXMLLoader.load(url);

            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro de Clientes");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Falha ao carregar FXML: " + e.getMessage()).showAndWait();
        }
    }
    public static void main(String[] args) { launch(args); }
}
