package internationalization.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public ResourceBundle bundle;

    public void setLblProduct(double price, int percentage) {
        String productString = bundle.getString("key.ProductString");
        NumberFormat curformat = NumberFormat.getCurrencyInstance(bundle.getLocale());
        NumberFormat percentformat = NumberFormat.getPercentInstance(bundle.getLocale());

        lblProduct.setText(MessageFormat.format(productString, curformat.format(price), percentformat.format(percentage)));
    }

    public void setLblTime(int min, int h) {
        String timeString = bundle.getString("key.TimeString");
        this.lblTime.setText(MessageFormat.format(timeString, min, h));
    }

    public void setLblDate(LocalDate date) {
        String dateString = bundle.getString("key.DateString");

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(bundle.getLocale());
        String formateDAte = formatter.format(date);
        lblDate.setText(MessageFormat.format(dateString, formateDAte));
    }

    @FXML
    private Label lblProduct;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblDate;

    public void selectGerman(ActionEvent actionEvent) {
        Scene scene = this.lblDate.getScene();
        bundle =  ResourceBundle.getBundle("internationalization.sample.main", new Locale("de", "DE"));

        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"), bundle);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectEnglish(ActionEvent actionEvent) {
        Scene scene = this.lblDate.getScene();
        bundle =  ResourceBundle.getBundle("internationalization.sample.main", new Locale("en", "EN"));

        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"), bundle);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        setLblTime(25, 12);
        setLblDate(LocalDate.now());
        setLblProduct(36.23, 12);
    }

    public void updateView(ActionEvent actionEvent) {
        setLblDate(LocalDate.now().minusDays(10));
        setLblTime(17, 20);
        setLblTime(32, 10);

    }
}
