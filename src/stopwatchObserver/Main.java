package stopwatchObserver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {

		Timer timer = new Timer(500);

		Scene scene = new Scene(new Stopwatch(timer), 350, 300);
		stage.setTitle("Stopwatch");
		stage.setScene(scene);
		stage.show();
		
		Stage stage2 = new Stage();
		stage2.setTitle("Stopwatch 2");
		Scene scene2 = new Scene(new Stopwatch(timer), 350, 300);
		stage2.setScene(scene2);
		stage2.show();
		
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}