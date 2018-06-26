package FXMLclickme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SimpleController {

  @FXML
  private Text message;

  @FXML
  protected void handleClickMe(ActionEvent event) {

    this.message.setText("Hello World!");
  }
}
