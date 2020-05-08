package Cinema;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Login{
    @FXML Button csv;
    @FXML
    TextField username,passcode;
    @FXML
    Pane regist;
    @FXML
    ImageView goBtn,info;
    @FXML
    AnchorPane loginPane;
    @FXML Label check;


    @FXML
    Label wrong;
//    private String path = "user"+ File.separator+"UserDetails.csv";
    HashMap<String,String> hashMap = new HashMap<>();

    public void initialize() throws IOException {
//        goBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                try {
//                    loginInfo(event);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        readFile();
        loginInfo();
    }
    public void personalinfo(MouseEvent event) throws IOException {
        ImageView a = (ImageView) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        stage.setScene(new Scene(loader.load(), 420, 308));
//        CinemaController cinemaController = loader.getController();
//        cinemaController.setUsername(username.getText());
        stage.show();

    }
    @FXML public void csvGo(MouseEvent event){
        ticket();
//        Stage stage = (Stage) csv.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cinema.fxml"));
//        try {
//            stage.setScene(new Scene(loader.load(), 581, 606));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        CinemaController cinemaController = loader.getController();
////        cinemaController.setUsername(username.getText());
//        stage.show();

    }

    @FXML public void goOnAction(MouseEvent event) throws IOException {
        if(hashMap.containsKey(username.getText())){
            System.out.println("enter username");
            if(passcode.getText().equals(hashMap.get(username.getText()))){
                ImageView a = (ImageView) event.getSource();
                Stage stage = (Stage) a.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Cinema.fxml"));
                stage.setScene(new Scene(loader.load(), 581, 606));
                CinemaController cinemaController = loader.getController();
                cinemaController.setUsername(username.getText());
                stage.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Wrong password");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent()&&result.get()==ButtonType.OK){
                    alert.close();
                    return;
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Username");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()&&result.get()==ButtonType.OK){
                alert.close();
                return;
            }
        }

    }
//

    public void loginInfo() {
//
        try {

            //createNewFile();
            FileReader fileReader = new FileReader("user/UserDetails.csv");
            BufferedReader br = new BufferedReader(fileReader);
            String line = br.readLine();
            System.out.println(line);
            while (line != null) {
                String[] arr = line.split(",");
                hashMap.put(arr[4], arr[5]);
                line = br.readLine();
            }
            br.close();
        }catch (IOException e) {e.printStackTrace();}

        System.out.println("Read");
    }


    @FXML public void registOnAciton(MouseEvent event) throws IOException {
        Pane a = (Pane) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
        stage.setScene(new Scene(loader.load(), 520, 315));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
//        Parent root = loader.load();
//        loginPane.getChildren().add(root);
        Register register = loader.getController();
        register.setMap(hashMap);
//        Pane a = (Pane) event.getSource();
//        Stage stage = (Stage) a.getScene().getWindow();
//
//        stage.setScene(new Scene(loader.load(), 412, 432
//        stage.show();
    }


    private String booking = "user/UserDetails.csv";
    String Dataline="";


        public void ticket() {

            Stage window = new Stage();
            Label label = new Label(Dataline);
            Button close = new Button("Close");

            HBox hBox = new HBox();
            VBox vBox = new VBox();
            close.setOnAction(e->window.close());
            hBox.getChildren().add(close);
            vBox.getChildren().addAll(hBox,label);
            Scene scene = new Scene(vBox);
            window.setScene(scene);
            window.showAndWait();

        }

        public void readFile() throws IOException {
            createFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("user/BookingData.csv"));
            String line = bufferedReader.readLine();
            while(line!=null){
                Dataline +=line+"\n";
                line = bufferedReader.readLine();
            }
        }
        public void createFile() throws IOException {
            File directory = new File("user");
            if(!directory.exists()){
                directory.mkdirs();
            }
            File file = new File(booking);
            file.createNewFile();
        }





    @FXML private void setHover( MouseEvent event) {
        regist.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                regist.setOpacity(0.5);
            }
        });
        regist.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                regist.setOpacity(1);
            }
        });
        goBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goBtn.setOpacity(0.5);
            }
        });
        goBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goBtn.setOpacity(1);
            }
        });
    }
}
