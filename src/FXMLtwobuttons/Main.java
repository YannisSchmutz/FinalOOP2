package FXMLtwobuttons;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("View_H.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View_V.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);

            setUserAgentStylesheet(getClass().getResource("Style.css").toExternalForm());

            primaryStage.setTitle("Two Buttons");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
