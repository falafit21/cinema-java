package Cinema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Register{//} implements Initializable {
    @FXML Button go;
    @FXML
    Label warning;
    @FXML
    TextField name,pass,confirmpass,email,age,username;
    @FXML
    Button applyBtn;
    @FXML ImageView backBtn;
    @FXML
    DatePicker birthdates;
    ArrayList<String> user = new ArrayList<>();
    HashMap<String, String> map;
    public void initialize() throws IOException {
        readReagist();
    }
    public void closeOnAction(MouseEvent event) throws IOException {
        ImageView a = (ImageView) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(loader.load(), 338, 404));
//        CinemaController cinemaController = loader.getController();
//        cinemaController.setUsername(username.getText());
        stage.show();

    }
    public void createNewFile() throws IOException {
        File file = new File("user");
        if(!file.exists()){
            file.mkdirs();
        }
        File files = new File("user/UserDetails.csv");
        files.createNewFile();

    }

//    private String path = "user"+ File.separator+"UserDetails.csv";

    public void readReagist()throws IOException{
        createNewFile();
        FileReader fileReader = new FileReader("user/UserDetails.csv");
        BufferedReader br = new BufferedReader(fileReader);
        String line = br.readLine();
        while (line != null){
            String[] arr = line.split(",");
            user.addAll(Arrays.asList(arr));
//            if(arr[4].equals(username.getText())){
//                warning.setText("** Username already exist.**");
//
//
//            }
//            user.add(arr[4]);
            line = br.readLine();


        }
    }

    public void writeRegist() throws IOException {
        createNewFile();
        FileWriter fileWriter = new FileWriter("user/UserDetails.csv");
//        BufferedWriter bw = new BufferedWriter(fileWriter);
        String userdata = "";
        userdata+=name.getText()+","+age.getText()+","+email.getText()+","+birthdates.getValue().toString()+","+username.getText()+","+pass.getText()+","+confirmpass.getText()+"\n";
        for(int i = 0; i< user.size();i+=7){
            userdata += user.get(i)+","+user.get(i+1)+","+user.get(i+2)+","+user.get(i+3)+","+user.get(i+4)+","+user.get(i+5)+","+user.get(i+6)+"\n";
        }
        fileWriter.write(userdata);
        fileWriter.flush();
        fileWriter.close();

    }

    public boolean checkApply() throws IOException {
        if(name.getText().equals ("")||age.getText().equals("")||email.getText().equals("")||birthdates.getValue()==null||username.getText().equals("")||pass.getText().equals("")||confirmpass.getText().equals("")){
            warning.setText("** Please enter your information. **");
            System.out.println("Please");
            return false;
        }
        else if(user.contains(username.getText())){
            System.out.println(username.getText());
            warning.setText("** Username already exist.**");
            System.out.println("exist");
            return false;
        }
        System.out.println("Success");
       return  true;
    }


    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    @FXML public void applyOnAction(ActionEvent event) throws IOException {
        if (checkApply()) {
            System.out.println("can register");
            writeRegist();
            map.put(username.getText(), pass.getText());
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            stage.setScene(new Scene(loader.load(), 338, 404));
            stage.show();
//            AnchorPane thisPage = (AnchorPane) button.getParent();
//            AnchorPane previousPage = (AnchorPane) thisPage.getParent();
//            previousPage.getChildren().remove(thisPage);
//            previousPage.getChildren().setAll(thisPage);
        }
        else{
            System.out.println("can't register");
        }
    }

}







