package Cinema;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Profile {
    @FXML
    ImageView backBtn;
    public void backOnAction(MouseEvent event){
        javafx.scene.image.ImageView b = (javafx.scene.image.ImageView) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));//new scene
        try {
            stage.setScene(new Scene(loader.load(), 338, 404));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();

    }
    public void setHower(){
        backBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                backBtn.setOpacity(1);
            }
        });
        backBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                backBtn.setOpacity(0.5);
            }
        });

    }
}
