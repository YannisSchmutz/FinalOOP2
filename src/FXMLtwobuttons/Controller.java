package FXMLtwobuttons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label label_a;

    @FXML
    private Label label_b;

    private Integer count_a = 0;
    private Integer count_b = 0;

    @FXML
    protected void handleClickA(ActionEvent event){
        this.label_a.setText("A = " + count_a.toString());
        count_a++;
    }

    @FXML
    protected void handleClickB(ActionEvent event){
        this.label_b.setText("B = " + count_b.toString());
        count_b++;
    }


}
