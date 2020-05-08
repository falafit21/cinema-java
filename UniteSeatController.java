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

public class UniteSeatController {
    private double ticketPriceNormal=0;
    private double ticketPriceVIP = 0;
    private double ticketPricePre = 0;
    private double totalPrice = 0;
    ArrayList<String> seatControl = new ArrayList<>();
    @FXML
    VBox normalBox,vipBox,premiumBox;
    @FXML
    ImageView homeBtn;
    @FXML
    Label title;
    @FXML
    Label systems,times,seats,amountSeat,totalprice,user,normalSeat,premiumSeat,vipSeat;
    @FXML
    Button backUniteBtn,reserveUniteBtn;

    private String theater;
    private String system;
    private String time;
    private String seat;
    private String Username;


    public void setUsername(String username) {
        Username = username;
    }

    ArrayList<String> seatWrite = new ArrayList<>();
    ArrayList<String > allDataBooking = new ArrayList<>();
    private ArrayList<String> seatReserved = new ArrayList<>();

    public void setTime(String time) {
        this.time = time;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setSystem(String system){
        this.system = system;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    private Movie movie;
    public void setMovie(Movie movie){
        this.movie = movie;

    }
//    @FXML public void handleBackUniteBtnOnAction(ActionEvent event) throws IOException {
//        Button b = (Button) event.getSource();
//        Stage stage = (Stage) b.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Movie.fxml"));//new scene
//        stage.setScene(new Scene(loader.load(), 594, 612));
//        stage.show();
//    }
//    public void setTimeBtn(String timeText){
//
//    }
    MovieDetailsController movieDetailsController;

    @FXML public void reserveOnAction(ActionEvent event) throws IOException{
        writeSeatFile();
        System.out.println(seatControl.toString());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==ButtonType.OK&&result.isPresent()){
            alert.close();
            showReciept();
            return;
        }
        else if(result.isPresent()&&result.get()==ButtonType.CANCEL){
            alert.close();
            return;
        }

    }
    public void showReciept(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("confirm.fxml"));
            Parent root = loader.load();
            Scene scene = homeBtn.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root,456,551));
            RecieptConfirm recieptConfirm = loader.getController();
            recieptConfirm.setAmounts(seatControl.size());
            recieptConfirm.setPrices(totalPrice);
            recieptConfirm.setUsername(Username);
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
    public void readSeatFile() throws IOException {
//        createNewSeatFile();
        FileReader fileReader = new FileReader("user/BookingData.csv");
        BufferedReader br = new BufferedReader(fileReader);
        String line = br.readLine();
        while(line != null){
            String theaterCheck = line.split(",")[1].trim();
            String movieCheck = line.split(",")[2].trim();
            String timeCheck = line.split(",")[4].trim();
            String systemCheck = line.split(",")[3].trim();
            if(theater.equals(theaterCheck)&&movie.getTitle().equals(movieCheck)&&system.equals(systemCheck)&&time.equals(timeCheck)){
                String[] arr = line.split(",")[5].split("-");
                for(String s:arr){
                    seatWrite.add(s.trim());
                    seatReserved.add(s.trim());
                }
            }
            allDataBooking.add(line);
            line=br.readLine();
        }
        System.out.println("seat reserved"+seatReserved);
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
            seat+= user.getText()+","+theater+","+movie.getTitle()+","+systems.getText()+","+times.getText()+",";

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
        } catch (IOException e) {
            e.printStackTrace();
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
    @FXML Label theaters;

    @FXML public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    readSeatFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                user.setText(Username);
                times.setText(time);
                systems.setText(system);
                theaters.setText(theater);
                title.setText(movie.getMovie());
                setHower();
                homeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        backHome(event);
                    }
                });
                vipBox.setSpacing(4);
                premiumBox.setSpacing(3);
                if (systems.getText().equals("4K")) {
                    if (theaters.getText().equals("Theater 2")) {
                        ticketPriceNormal = 140;
                        ticketPriceVIP = 180;
                        ticketPricePre = 200;
                    }
                } else if (systems.getText().equals("3D")) {
                    if (theaters.getText().equals("Theater 4")) {
                        ticketPriceNormal = 180;
                        ticketPriceVIP = 220;
                        ticketPricePre = 260;
                    }

                }
                normalSeat.setText(ticketPriceNormal + "");
                vipSeat.setText(ticketPriceVIP + "");
                premiumSeat.setText(ticketPricePre + "");
                for (int i = 0; i < 6; i++) {//column
                    HBox hbox = new HBox();
                    HBox hbox1 = new HBox();
                    HBox boxes1 = new HBox();
                    HBox boxes2 = new HBox();
                    HBox boxes3 = new HBox();
                    HBox premium = new HBox();
                    hbox1.getChildren().addAll(boxes1, boxes2, boxes3);
                    hbox1.setSpacing(30);
                    premium.setSpacing(20);
                    hbox.setSpacing(5);

                    if (i <= 2) {
                        for (int j = 0; j < 7; j++) {//row
                            int k = 1;
                            int jj = j;
                            char pos = ((char) ((int) 'A' + i));
                            String pos1 = pos + "" + (jj + k);
                            ImageView box = new ImageView("image/chair1crop.png");
                            box.setFitHeight(34);
                            box.setFitWidth(30);
                            if (seatReserved.contains(pos1)) {
                                ImageView imageView = new ImageView(new Image("image/Tickets-Icon.png"));
                                imageView.setFitHeight(34);
                                imageView.setFitWidth(30);
                                hbox.getChildren().add(imageView);
                            } else {
                                box.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Object s = event.getSource();
                                        int a = hbox.getChildren().indexOf(s);
                                        ImageView imageView = new ImageView(new Image("image/check-mark.png"));
                                        imageView.setFitWidth(34);
                                        imageView.setFitHeight(34);
                                        hbox.getChildren().set(a, imageView);
                                        seatControl.add(pos1 + "");
                                        seats.setText(seatControl.toString() + "");
                                        amountSeat.setText(seatControl.size() + "");
                                        totalPrice += ticketPriceNormal;
                                        totalprice.setText(totalPrice + "");
                                        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent event) {
                                                Object ss = event.getSource();
                                                int b = hbox.getChildren().indexOf(ss);
                                                hbox.getChildren().set(b, box);
                                                seatControl.remove(pos1 + "");
                                                seats.setText(seatControl.toString());
                                                amountSeat.setText(seatControl.size() + "");
                                                totalPrice -= ticketPriceNormal;
                                                totalprice.setText(totalPrice + "");
                                            }
                                        });

                                    }
                                });
                                hbox.getChildren().add(box);
                            }
                        }
                        normalBox.getChildren().add(hbox);
                    } else if (i > 2 && i <= 4) {

                        for (int j = 0; j < 6; j++) {//row
                            int k = 1;
                            int jj = j;
                            //HBox hbox1 = new HBox();
                            ImageView coup = new ImageView("image/vipchair.png");
                            coup.setFitHeight(34);
                            coup.setFitWidth(32);
                            char pos2 = ((char) ((int) 'A' + i));
                            String pos3 = pos2 + "" + (jj + k);
                            if (seatWrite.contains(pos3)) {
                                ImageView imageView = new ImageView(new Image("image/Tickets-Icon.png"));
                                imageView.setFitHeight(31);
                                imageView.setFitWidth(34);
                                boxes1.getChildren().add(imageView);
                            } else {
                                coup.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Object s = event.getSource();
                                        int a = boxes1.getChildren().indexOf(s);
                                        ImageView imageView = new ImageView(new Image("image/check-mark.png"));
                                        imageView.setFitWidth(34);
                                        imageView.setFitHeight(34);
                                        boxes1.getChildren().set(a, imageView);
                                        seatControl.add(pos3 + "");
                                        seats.setText(seatControl.toString());
                                        amountSeat.setText(seatControl.size() + "");
                                        totalPrice += ticketPriceVIP;
                                        totalprice.setText(totalPrice + "");
                                        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent event) {
                                                Object ss = event.getSource();
                                                int b = boxes1.getChildren().indexOf(ss);
//                        ImageView imageView1 = new ImageView(new Image("image/chair1crop.png"));
                                                boxes1.getChildren().set(b, coup);
                                                seatControl.remove(pos3 + "");
                                                seats.setText(seatControl.toString());
                                                amountSeat.setText(seatControl.size() + "");
                                                totalPrice -= ticketPriceVIP;
                                                totalprice.setText(totalPrice + "");
                                            }
                                        });
                                    }
                                });
                                boxes1.getChildren().add(coup);
                            }
//                            char pos = ((char) ((int) 'A' + i));
//                            String pos1 = pos + "" + (j + 1);


//                            ImageView coup1 = new ImageView("image/vipchair.png");
//                            coup1.setFitHeight(36);
//                            coup1.setFitWidth(32);
//                            if (seatWrite.contains(pos3)) {
//                                ImageView imageView = new ImageView(new Image("image/Tickets-Icon.png"));
//                                imageView.setFitHeight(31);
//                                imageView.setFitWidth(34);
//                                boxes2.getChildren().add(imageView);
//                            } else {
//                                int posCol2 = 3;
//                                int jjCol2 = j;
//                                int finalI = i;
//                                coup1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                                    @Override
//                                    public void handle(MouseEvent event) {
//                                        char pos = ((char) ((int) 'A' + finalI));
//                                        String pos1 = pos + "" + (posCol2 + jjCol2);
//                                        Object s = event.getSource();
//                                        int a = boxes2.getChildren().indexOf(s);
//                                        ImageView imageView = new ImageView(new Image("image/check-mark.png"));
//                                        imageView.setFitWidth(34);
//                                        imageView.setFitHeight(34);
//                                        boxes2.getChildren().set(a, imageView);
//                                        seatControl.add(pos1 + "");
//                                        seats.setText(seatControl.toString());
//                                        amountSeat.setText(seatControl.size() + "");
//                                        totalPrice += ticketPriceVIP;
//                                        totalprice.setText(totalPrice + "");
//                                        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                                            @Override
//                                            public void handle(MouseEvent event) {
//                                                Object ss = event.getSource();
//                                                int b = boxes2.getChildren().indexOf(ss);
//                                                boxes2.getChildren().set(b, coup1);
//                                                seatControl.remove(pos1 + "");
//                                                seats.setText(seatControl.toString());
//                                                amountSeat.setText(seatControl.size() + "");
//                                                totalPrice -= ticketPriceVIP;
//                                                totalprice.setText(totalPrice + "");
//                                            }
//                                        });
//                                    }
//                                });
//                                boxes2.getChildren().add(coup1);
//                            }
//                            ImageView coup2 = new ImageView("image/vipchair.png");
//                            coup2.setFitHeight(36);
//                            coup2.setFitWidth(32);
//                            if (seatWrite.contains(pos3)) {
//                                ImageView imageView = new ImageView(new Image("image/Tickets-Icon.png"));
//                                imageView.setFitHeight(31);
//                                imageView.setFitWidth(34);
//                                boxes3.getChildren().add(imageView);
//                            } else {
//                                int posCol3 = 4;
//                                int jjCol3 = j;
//                                int finalI1 = i;
//                                coup2.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                                    @Override
//                                    public void handle(MouseEvent event) {
//                                        char pos = ((char) ((int) 'A' + finalI1));
//                                        String pos1 = pos + "" + (posCol3 + jjCol3);
//                                        Object s = event.getSource();
//                                        int a = boxes3.getChildren().indexOf(s);
//                                        ImageView imageView = new ImageView(new Image("image/check-mark.png"));
//                                        imageView.setFitWidth(34);
//                                        imageView.setFitHeight(31);
//                                        boxes3.getChildren().set(a, imageView);
//                                        seatControl.add(pos1 + "");
//                                        seats.setText(seatControl.toString());
//                                        amountSeat.setText(seatControl.size() + "");
//                                        totalPrice += ticketPriceVIP;
//                                        totalprice.setText(totalPrice + "");
//                                        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                                            @Override
//                                            public void handle(MouseEvent event) {
//                                                Object ss = event.getSource();
//                                                int b = boxes3.getChildren().indexOf(ss);
//                                                boxes3.getChildren().set(b, coup2);
//                                                seatControl.remove(pos1 + "");
//                                                seats.setText(seatControl.toString());
//                                                amountSeat.setText(seatControl.size() + "");
//                                                totalPrice -= ticketPriceVIP;
//                                                totalprice.setText(totalPrice + "");
//                                            }
//                                        });
//                                    }
//                                });
//                                boxes3.getChildren().add(coup2);
//                            }
                        }
                        vipBox.getChildren().add(hbox1);
                    }
                    else {
                        System.out.println("Test");
                        for (int j = 0; j < 5; j++) {
                            ImageView prem = new ImageView("image/premiumchair.png");
                            prem.setFitHeight(36);
                            prem.setFitWidth(32);
                            char pos = ((char) ((int) 'A' + i));
                            String pos1 = pos + "" + (j + 1);
                            if (seatWrite.contains(pos1)) {
                                ImageView imageView = new ImageView(new Image("image/Tickets-Icon.png"));
                                imageView.setFitHeight(31);
                                imageView.setFitWidth(34);
                                premium.getChildren().add(imageView);
                            } else {
                                char pos2 = ((char) ((int) 'A' + i));
                                String pos3 = pos2 + "" + (j + 1);
                                prem.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        Object s = event.getSource();
                                        int a = premium.getChildren().indexOf(s);
                                        ImageView imageView = new ImageView(new Image("image/check-mark.png"));
                                        imageView.setFitWidth(34);
                                        imageView.setFitHeight(31);
                                        premium.getChildren().set(a, imageView);
                                        seatControl.add(pos3 + "");
                                        seats.setText(seatControl.toString());
                                        amountSeat.setText(seatControl.size() + "");
                                        totalPrice += ticketPricePre;
                                        totalprice.setText(totalPrice + "");
                                        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent event) {
                                                Object ss = event.getSource();
                                                int b = premium.getChildren().indexOf(ss);
                                                premium.getChildren().set(b, prem);
                                                seatControl.remove(pos1 + "");
                                                seats.setText(seatControl.toString());
                                                amountSeat.setText(seatControl.size() + "");
                                                totalPrice -= ticketPricePre;
                                                totalprice.setText(totalPrice + "");
                                            }
                                        });
                                    }
                                });
                                premium.getChildren().add(prem);
                            }
                        }
                        premiumBox.getChildren().add(premium);
                    }
                }

            }

        });


    }
    }


