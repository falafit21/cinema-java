package Cinema;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RecieptConfirm {
    @FXML
    Label user,theater,price,amount,title,system;
    @FXML
    Text seat;
    String username;
    String theaters;
    String seats;
    double prices;
    double amounts;
    String titles;
    String systems;
    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                user.setText(username);
                theater.setText(theaters);
                price.setText(prices+"");
                amount.setText(amounts+"");
                title.setText(titles);
                system.setText(systems);
                seat.setText(seats);

            }
        });
    }
    public void quitChangeScene(MouseEvent event) throws IOException {
        ImageView image = (ImageView) event.getSource();
        Stage stage = (Stage) image.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(loader.load(), 338, 404));
//        UniteSeatController uniteSeatController = loader.getController();
//        uniteSeatController.setMovie(movie);
//        uniteSeatController.setTheater(theater);
//        uniteSeatController.setSystem(system);
//        uniteSeatController.setUsername(username);
        stage.show();
    }

    public void setSystems(String systems) {
        this.systems = systems;
    }

    public void setUser(Label user) {
        this.user = user;
    }


    public void setPrices(double prices) {
        this.prices = prices;
    }

    public void setAmounts(double amounts) {
        this.amounts = amounts;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setTheaters(String theaters) {
        this.theaters = theaters;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }



    public void setTitles(String titles) {
        this.titles = titles;
    }
}
