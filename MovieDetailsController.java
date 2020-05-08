package Cinema;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieDetailsController {
    @FXML
    ImageView img,backBtn,video;
    @FXML
    Label title;
    @FXML Label describe,date,user,describe1;
    @FXML
    Text theater1,theater2,theater3,theater4,d,k,normal;

//    @FXML
//    Button time11,time12,time13,time21,time22,time23,time31,time32,time33;
    @FXML
    GridPane gridpane;

    String username;
    private Movie movie;


    public void setUsername(String username) {
        this.username = username;
    }


    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                video.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        videoTrailer(event);
//                    }
//                });
                setHover();
                user.setText(username);
                describe1.setText(movie.getDescribe());
                describe.setText(movie.getDescribe1());
                title.setText(movie.getTitle());
                img.setImage(new Image(movie.getImg()));
                setTimeOnButton();
                //videoTrailer

            }
        });
    }
    private void setHover(){
        backBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                backBtn.setOpacity(0.5);
            }
        });
        backBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                backBtn.setOpacity(1);
            }
        });

    }
    @FXML public void backOnAciton(MouseEvent event) throws IOException {
        ImageView a = (ImageView) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cinema.fxml"));
        stage.setScene(new Scene(loader.load(), 581, 606));
        CinemaController cinemaController = loader.getController();
        cinemaController.setUsername(username);
        stage.show();

    }
    public void setMovieDetail(Movie movie){
        this.movie = movie;

    }
    @FXML public void videoTrailer(MouseEvent event) throws IOException {
        ImageView button = (ImageView) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Video.fxml"));
        stage.setScene(new Scene(loader.load(), 573, 429));
        Video video = loader.getController();
        video.setMovie(movie);
        video.setUsername(username);
        video.setTitle(title.getText());
        stage.show();

    }
    @FXML public void handleTimeNormalBtnOnAction(ActionEvent event,String theater,String system) {
        try{
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NormalSeat.fxml"));
            stage.setScene(new Scene(loader.load(), 634, 406));
            NormalSeat normalSeat = loader.getController();
            normalSeat.setMovie(movie);
            normalSeat.setTheater(theater);
            normalSeat.setSystem(system);
            normalSeat.setUsers(username);
            normalSeat.setTime(button.getText());
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML public void handleTimeMixedBtnOnAction(ActionEvent event,String theater,String system){
        try{
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UniteSeat.fxml"));
            stage.setScene(new Scene(loader.load(), 613, 430));
            UniteSeatController uniteSeatController = loader.getController();
            uniteSeatController.setMovie(movie);
            uniteSeatController.setTheater(theater);
            uniteSeatController.setSystem(system);
            uniteSeatController.setUsername(username);
            uniteSeatController.setTime(button.getText());
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void setTimeOnButton(){
        for(int i =0 ;i<5;i++){
            for(int j=0;j<3;j++){
                if(i==0){
                    //theater1.setText("Theater 1");
                    Button button = new Button(movie.getTime()[j]);//0 1 2
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            handleTimeNormalBtnOnAction(event,"Theater 1","Normal");
                        }
                    });
                    gridpane.add(button,j,i);
                }
                else if(i==1){
                    //theater2.setText("Theater 2");
                    Button button = new Button(movie.getTime()[j+3]);//3 4 5
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            handleTimeMixedBtnOnAction(event,"Theater 2","4K");
                        }
                    });
//                    button.setOnAction(this::handleTimeMixedBtnOnAction);
                    gridpane.add(button,j,i);
                }
                else if(i==2){
                    //theater3.setText("Theater 3");
                    Button button = new Button(movie.getTime()[j+6]);//6 7 8
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            handleTimeNormalBtnOnAction(event,"Theater 3","4K");
                        }
                    });
                    //button.setOnAction(this::handleTimeNormalBtnOnAction);
                    gridpane.add(button,j,i);
                }
                else if(i==3){
                    //theater4.setText("Theater 4");
                    Button button = new Button(movie.getTime()[j+6]);//6 7 8
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            handleTimeMixedBtnOnAction(event,"Theater 4","3D");
                        }
                    });
                    //button.setOnAction(this::handleTimeMixedBtnOnAction);
                    gridpane.add(button,j,i);
                }

            }
        }
    }
}
