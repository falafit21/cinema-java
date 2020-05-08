package Cinema;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class NormalSeat {
    ArrayList<String> seatControl = new ArrayList<>();
    ArrayList<String> seatWrite = new ArrayList<>();
    CinemaController cinemaController;
    @FXML VBox vbox;
    @FXML Label theaters;
    @FXML ImageView homeBtn;
    @FXML
    Button backNormalBtn,reserveNormalBtn;
    @FXML
    Label titles,user,seat,systems,amountSeat,totalprice,normalSeat;
//    String title;
    ArrayList<String > allDataBooking = new ArrayList<>();
    private ArrayList<String> seatReserved = new ArrayList<>();
    private Movie movie;
    private String theater;
    private String system;
    private double ticketPriceNormal = 0;
    private double totalPrice = 0;
    private String users;
    private String time;
    public void setTime(String time){
        this.time=time;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setSystem(String system) {
        this.system = system;
    }

//    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
//        Button b = (Button) event.getSource();
//        Stage stage = (Stage) b.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Movie.fxml"));//new scene
//        stage.setScene(new Scene(loader.load(), 594, 612));
//        stage.show();
//    }
    @FXML public void reserveOnAction(ActionEvent event) throws IOException{
        writeSeatFile();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Please confirm your seat! ");
//        alert.showAndWait();

        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.isPresent()&&buttonType.get()==ButtonType.CANCEL){
            alert.close();
            return;
        }
        else if(buttonType.isPresent()&&buttonType.get()==ButtonType.OK){
            alert.close();
            showReciept();
            return;
        }
        alert.show();
        //Button b = (Button)
        // event.getSource();
        //Stage stage = (Stage) b.getScene().getWindow();
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("Movie.fxml"));//new scene
        //stage.setScene(new Scene(loader.load(), 594, 612));
        //stage.show();
    }

    public void readSeatFile() throws IOException {
        createNewSeatFile();
        FileReader fileReader = new FileReader("user/BookingData.csv");
        BufferedReader br = new BufferedReader(fileReader);
        String line = br.readLine();
        while(line != null) {
            String theaterCheck = line.split(",")[1];
            String movieCheck = line.split(",")[2];
            String timeCheck = line.split(",")[4];
            String systemCheck = line.split(",")[3];
            if (theater.equals(theaterCheck) && movie.getTitle().equals(movieCheck) && system.equals(systemCheck) && time.equals(timeCheck)) {
                String[] arr = line.split(",")[5].split("-");
                for (String s : arr) {
                    seatWrite.add(s);
//                if(thea)
                    seatReserved.add(s);
                }
            }
            allDataBooking.add(line);
            line=br.readLine();
        }
    }

    public void createNewSeatFile() throws IOException {
        File file = new File("user");
        if(!file.exists()){
            file.mkdirs();
        }
        File files = new File("user/BookingData.csv");
        files.createNewFile();

    }
    public void writeSeatFile(){
        try {
            createNewSeatFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter("user/BookingData.csv");
            String seat = "";
            //การจองที่นั่ง Theatre 1,NameMovie,TimeShow,Seat A1-A2; pattern write on file seat reserved;
            seat+= user.getText()+","+theater+","+movie.getTitle()+","+systems.getText()+","+time+",";

            for(String ss : seatWrite){
                seat+=ss+"-";
            }
            for(String s:seatControl){
                seat+=s+"-";
            }
            seat+="\n";
            for(String data: allDataBooking){
                seat+=data+"\n";
            }

            fileWriter.write(seat);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void backHome(MouseEvent event){
        ImageView b = (ImageView) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cinema.fxml"));//new scene
        try {
            stage.setScene(new Scene(loader.load(), 581, 606));
            CinemaController cinemaController =  loader.getController();
            cinemaController.setUsername(users);

        } catch (IOException e) {
        }
        stage.show();

    }
    public void setHower(){
        homeBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                homeBtn.setOpacity(1);
            }
        });
        homeBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                homeBtn.setOpacity(0.5);
            }
        });

    }
    public void showReciept(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("confirm.fxml"));
            Parent root = loader.load();
            Scene scene = homeBtn.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root,456,559));
            RecieptConfirm recieptConfirm = loader.getController();
            recieptConfirm.setAmounts(seatControl.size());
            recieptConfirm.setPrices(totalPrice);
            recieptConfirm.setUsername(users);
            recieptConfirm.setTheaters(theater);
            recieptConfirm.setSeats(seatControl.toString());
            recieptConfirm.setPrices(totalPrice);
            recieptConfirm.setTitles(movie.getMovie());
            recieptConfirm.setSystems(system);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML public void initialize() throws IOException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                user.setText(users);
                try {
                    readSeatFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                user.setText(users);
                systems.setText(system);
                theaters.setText(theater);
                titles.setText(movie.getMovie());
                //title.setText(String.valueOf(cinemaController.title1));
                if(systems.getText().equals("Normal")){
                    ticketPriceNormal = 120;
                    System.out.println(ticketPriceNormal);
                }
                else if(systems.getText().equals("4K")){
                    if(theaters.getText().equals("Theater 3")){
                        ticketPriceNormal = 180;
                        System.out.println(ticketPriceNormal);
                    }
                }
                normalSeat.setText(ticketPriceNormal+"");
                for(int i = 0; i < 4;i++){//column
                    HBox hbox = new HBox();
                    HBox left = new HBox();
                    HBox right = new HBox();

                    hbox.getChildren().addAll(left,right);
                    hbox.setSpacing(35);
                    for(int j = 0;j < 4;j++){//row
                        int k=5;int jj=j;
                        char pos = ((char)((int)'A'+i));
                        String pos1 = pos+""+(jj+k);
                        ImageView R = new ImageView("image/chair1crop.png");
                        R.setFitHeight(50);
                        R.setFitWidth(34);
                        if(seatWrite.contains(pos1)){
                            ImageView imageView = new ImageView(new Image("image/Tickets-Icon.png"));
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(34);
                            right.getChildren().add(imageView);
                        }
                        else{
                            R.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
//                        String pos1 = pos+""+(k+jj);
                                    Object a = event.getSource();
                                    int c = right.getChildren().indexOf(a);
                                    ImageView imageView = new ImageView(new Image("image/check-mark.png"));
                                    imageView.setFitWidth(34);
                                    imageView.setFitHeight(34);
                                    right.getChildren().set(c,imageView);
                                    seatControl.add(pos1+"");
                                    seat.setText(seatControl.toString());
                                    amountSeat.setText(seatControl.size()+"");
                                    totalPrice+=ticketPriceNormal;
                                    totalprice.setText(totalPrice+"");
                                    imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            Object ss = event.getSource();
                                            int b = right.getChildren().indexOf(ss);
                                            right.getChildren().set(b,R);
                                            seatControl.remove(pos1+"");
                                            seat.setText(seatControl.toString());
                                            amountSeat.setText(seatControl.size()+"");
                                            totalPrice-=ticketPriceNormal;
                                            totalprice.setText(totalPrice+"");
                                        }
                                    });
                                }
                            });
                            right.getChildren().add(R);
                        }
                        ImageView L = new ImageView("image/chair1crop.png");
                        L.setFitHeight(50);
                        L.setFitWidth(34);
                        char pos2 = ((char)((int)'A'+i));
                        String pos3 = pos2+""+(j+1);
                        if(seatWrite.contains(pos3)){
                            ImageView imageView = new ImageView(new Image("image/Tickets-Icon.png"));
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(34);
                            left.getChildren().add(imageView);
                        }
                        else{
                            L.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    Object s = event.getSource();
                                    int a = left.getChildren().indexOf(s);
                                    ImageView imageView = new ImageView(new Image("image/check-mark.png"));
                                    imageView.setFitWidth(34);
                                    imageView.setFitHeight(34);
                                    left.getChildren().set(a,imageView);
                                    seatControl.add(pos1+"");
                                    seat.setText(seatControl.toString());
                                    amountSeat.setText(seatControl.size()+"");
                                    totalPrice+=ticketPriceNormal;
                                    totalprice.setText(totalPrice+"");
                                    imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            Object ss = event.getSource();
                                            int b = left.getChildren().indexOf(ss);
                                            left.getChildren().set(b,L);
                                            seatControl.remove(pos1+"");
                                            seat.setText(seatControl.toString());
                                            amountSeat.setText(seatControl.size()+"");
                                            totalPrice -= ticketPriceNormal;
                                            totalprice.setText(totalPrice+"");
                                        }
                                    });
                                }
                            });
                            left.getChildren().add(L);
                        }

//
//                left.getChildren().add(L);
//                right.getChildren().add(R);
                    }
                    vbox.getChildren().add(hbox);



                }

            }
        });

    }


}
