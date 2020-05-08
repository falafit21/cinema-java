package Cinema;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class UniteSeat {
    @FXML
    VBox normalBox,vipBox,premiumBox;
    @FXML public void initialize(){
        seatGenerate();
    }
    @FXML
    public void reserveOnAction(ActionEvent event) throws IOException {
//        writeSeatFile();
//        System.out.println(seatControl.toString());
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        Optional<ButtonType> result = alert.showAndWait();
//        if(result.get()==ButtonType.OK&&result.isPresent()){
//            alert.close();
//            showReciept();
//            return;
//        }
//        else if(result.isPresent()&&result.get()==ButtonType.CANCEL){
//            alert.close();
//            return;
//        }

    }
    private void seatGenerate(){
        for(int i=0 ; i<9 ;i++){
            HBox hBox = new HBox();
            ImageView seat = null;
            for(int j=0; j<7 ;j++){
                if(i<3){
                    ImageView normal = new ImageView("image/chair1crop.png");
                    seat=normal;
                }
                else if(i>=3&&i<=4){
                    ImageView vip = new ImageView("image/vipchair.png");
                    seat=vip;
                }
                else if(i>4){
                    ImageView premium = new ImageView("image/premiumchair.png");
                    seat = premium;
                }
                seat.setFitWidth(30);
                seat.setFitHeight(30);
                ImageView seatCopy = seat;
                seat.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Object s = event.getSource();
                        int a = normalBox.getChildren().indexOf(s);
                        ImageView imageView = new ImageView(new Image("image/check-mark.png"));
                        imageView.setFitWidth(34);
                        imageView.setFitHeight(34);
                        normalBox.getChildren().set(a, imageView);
                        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Object ss = event.getSource();
                                int b = normalBox.getChildren().indexOf(ss);
                                normalBox.getChildren().set(b, seatCopy);
                            }
                        });
                    }
                });
                hBox.getChildren().add(seat);
            }
            normalBox.getChildren().add(hBox);
        }
    }
}
