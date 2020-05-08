package Cinema;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class CinemaController {
    @FXML ImageView logout;
    @FXML
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    @FXML
    Label title1,title2,title3,title4,title5,title6,title7,title8,title9;

    @FXML
    Label release1,release2,release3,release4,release5,release6,release7,release8,release9,username;
    private Movie [] movies = new Movie[9];
    String usernameSet;
    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                addMovie();
                setHover();
                username.setText(usernameSet);
            }
        });
    }
    public void setUsername(String username){
        this.usernameSet = username;

    }
    public void addMovie(){
        movies[0] = new Movie("Maleficent",
                "16.10.19",
                "image/movie/maleficent.jpg",
                "Genres: Adventure | Family | Fantasy\n" +
                "Director: Robert Stromberg\n" +
                "Stars: Angelina Jolie, Elle Fanning,\n" +"\tSharlto Copley  \n",
                "Storyline:\n" +
                "\tA beautiful, pure-hearted young woman,\n"+ "Maleficent has an idyllic life growing up in a \n"+"peaceable forest kingdom,until one day when an \n"+"invading army threatens the harmony of the land. \n"+"Maleficent rises to be the land's fiercest protector,\n"+ "but she ultimately suffers a ruthless betrayal - \n"+"an act that begins to turn her pure heart to stone.\n"+ "Bent on revenge, Maleficent faces a battle with \n"+"the invading king's successor."+
                "\n",
                new String[]{"10.20", "14.15", "19.21", "09.30", "17.12", "16.00", "17.30", "18.50", "22.00", "16.12", "19.12","21.34\n"},
                "image/trailer/Malificenttrailer.mp4"
                );
        Image image = new Image(movies[0].getImg());
        img1.setImage(image);
        title1.setText(movies[0].getMovie());
        release1.setText(movies[0].getRelease());
        movies[1] = new Movie("Aladdin",
                "10.10.19",
                "image/movie/aladdin.jpg",
                "Genres: Adventure, Family, Fantasy \n" +
                "Director: Guy Ritchie\n" +
                "Stars: Will Smith, Mena Massoud, Naomi Scott\n",
                "Storyline: \n" +
                "\tA kind-hearted street urchin Aladdin vies for \n"+"the love of the beautiful princess Jasmine,\n"+"the princess of Agrabah. When he finds \n"+"a magic lamp,he uses a genie's magic power \n"+"to make himself a prince in order to marry her.\n"+ "He is also on a mission to stop the powerful Jafar \n"+"who plots to steal the magic lamp that could make \n"+"his deepest wishes come true.\n",
                new String[]{"10.20", "14.15", "19.21", "09.30", "17.12", "16.00", "17.30", "18.50", "22.00", "16.12", "19.12","21.34"},
                "image/trailer/Aladdintrailer.mp4");
        Image image1 = new Image(movies[1].getImg());
        img2.setImage(image1);
        title2.setText(movies[1].getMovie());
        release2.setText(movies[1].getRelease());
        movies[2] = new Movie("Kong"
                ,"2.10.19",
                "image/movie/kingkong.jpg",
                "Genres:Action | Adventure | Fantasy | Sci-Fi\n" +
                "Director: Jordan Vogt-Roberts\n" +
                "Stars: Tom Hiddleston, Samuel L. Jackson, Brie Larson\n",
                "Storyline:\n" +
                "\tA washed up monster chaser convinces the \n"+"U.S. Government to fund a trip to an unexplored \n"+"island in the South Pacific.Under the guise of\n"+ "geological research,the team travels to \"Skull \n"+"Island\".Upon arrival, the group discover \n"+"that their mission may be complicated by the wildlife\n"+"which inhabits the island. The beautiful vistas \n"+"and deadly creatures create a visually stunning \n"+"experience that is sure to keep your attention.\n",
                new String[]{"10.20", "14.15", "19.21", "09.30", "17.12", "16.00", "17.30", "18.50", "22.00", "16.12", "19.12","21.34"},
                "image/trailer/kongtrailer.mp4");
        Image image2 = new Image(movies[2].getImg());
        img3.setImage(image2);
        title3.setText(movies[2].getMovie());
        release3.setText(movies[2].getRelease());
        movies[3] = new Movie("The Grey",
                "2.10.19",
                "image/movie/thegrey.jpg",
                "Genre:Action | Adventure | Drama | Thriller\n" +
                "Director: Joe Carnahan\n" +
                "Stars: Liam Neeson, Dermot Mulroney, Frank Grillo \n",
                "Storyline:\n" +
                "\tIn Alaska, a team of oil workers board a flight\n"+"home;however, they cross a storm\n"+"and the airplane crashes.Only seven workers\n"+"survive in the wilderness and John Ottway,\n"+ "who is a huntsman that kills wolves to protect \n"+"the workers,assumes leadership of the group.\n"+"Shortly after they learn that they are surrounded by\n"+"a pack of wolves and Ottway advises that\n"+"they should seek protection in the woods. But while\n"+"they walk through the heavy snow, they are \n"+"chased and attackedby the carnivorous mammals.",
                new String[]{"09.15", "11.20", "15.30", "10.12", "14.15", "19.20", "11.30", "17.00", "21.12", "12.15", "20.20", "23.30","00.54"},
                "image/trailer/thegreytrailer.mp4");
        Image image3 = new Image(movies[3].getImg());
        img4.setImage(image3);
        title4.setText(movies[3].getMovie());
        release4.setText(movies[3].getRelease());
        movies[4] = new Movie("Star Wars",
                "30.09.19",
                "image/movie/starwar.jpg",
                "Kong Skull Island \n" +
                "Genres:Action | Adventure | Fantasy | Sci-Fi\n" +
                "Director: Jordan Vogt-Roberts\n" +
                "Stars: Tom Hiddleston, Samuel L. Jackson, Brie Larson\n",
                "Storyline:\n" +
                "\tJedi Master-in-hiding Luke Skywalker \n"+"unwillingly attempts to guide young hopeful Rey in \n"+"the ways of the force, while Leia,former \n"+"princess turned general, attempts to lead what \n"+"is left of the Resistance away from the ruthless \n"+"tyrannical grip of the First Order.\n",
                new String[]{"09.15", "11.20", "15.30", "10.12", "14.15", "19.20", "11.30", "17.00", "21.12", "12.15", "20.20", "23.30"},
                "image/trailer/starwalltrailer.mp4");
        Image image4 = new Image(movies[4].getImg());
        img5.setImage(image4);
        title5.setText(movies[4].getMovie());
        release5.setText(movies[4].getRelease());
        movies[5] = new Movie("Insight Out",
                "27.09.19",
                "image/movie/insideout.jpg",
                "Genres: Animation | Adventure | Comedy | Drama | Family | Fantasy\n" +
                "Directors: Pete Docter, Ronnie Del Carmen (co-director)\n" +
                "Stars: Amy Poehler, Bill Hader, Lewis Black \n",
                "Storyline:\n" +
                "\tGrowing up can be a bumpy road, and it's no \n"+"exception for Riley, who is uprooted from her\n"+"Midwest life when her father starts a new job in \n"+"San Francisco.Like all of us,Riley is guided by her\n"+"emotions - Joy, Fear, Anger, Disgust and Sadness.\n"+"The emotions live in Headquarters, the control\n"+"center inside Riley's mind, where they help advise her\n"+"through everyday life. As Riley and her emotions\n"+"struggle to adjust to a new life in San Francisco, turmoil ensues\n"+ "in Headquarters. Although Joy, Riley's main and most important emotion, tries to keep things positive, the emotions conflict on how best to navigate a new city, house and school.\n",
                new String[]{"09.15", "11.20", "15.30", "10.12", "14.15", "19.20", "11.30", "17.00", "21.12", "12.15", "20.20", "23.12","01.23"},
                "image/trailer/insightout.mp4");
        Image image5 = new Image(movies[5].getImg());
        img6.setImage(image5);
        title6.setText(movies[5].getMovie());
        release6.setText(movies[5].getRelease());
        movies[6] = new Movie("Hubble","22.10.19","image/movie/hubble.jpg","----", "",new String[]{""},"");
        Image image6 = new Image(movies[6].getImg());
        img7.setImage(image6);
        title7.setText(movies[6].getMovie());
        release7.setText(movies[6].getRelease());
        movies[7] = new Movie("Brave","1.11.19","image/movie/brave.jpg","---", "",new String[]{""},"");
        Image image7 = new Image(movies[7].getImg());
        img8.setImage(image7);
        title8.setText(movies[7].getMovie());
        release8.setText(movies[7].getRelease());
        movies[8] = new Movie("Another Earth","15.11.19","image/movie/anotherearth.jpg","--", "",new String[]{""},"");
        Image image8 = new Image(movies[8].getImg());
        img9.setImage(image8);
        title9.setText(movies[8].getMovie());
        release9.setText(movies[8].getRelease());
    }
    @FXML public void logout(MouseEvent event){
        ImageView a = (ImageView) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 338, 404));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML public void changeScene(MouseEvent event) throws IOException {
        ImageView a = (ImageView) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieDetails.fxml"));
        stage.setScene(new Scene(loader.load(), 620, 439));
        MovieDetailsController desc= loader.getController();
//        NormalSeat normal = loader.getController();
//        UniteSeatController unite = loader.getController();


        if(a.getId().equals("img1")){
            desc.setMovieDetail(movies[0]);

        }
        else if(a.getId().equals("img2")){
            desc.setMovieDetail(movies[1]);
        }
        else if(a.getId().equals("img3")){
            desc.setMovieDetail(movies[2]);
        }
        else if(a.getId().equals("img4")){
            desc.setMovieDetail(movies[3]);
        }
        else if(a.getId().equals("img5")){
            desc.setMovieDetail(movies[4]);
        }
        else if(a.getId().equals("img6")){
            desc.setMovieDetail(movies[5]);
        }
        desc.setUsername(usernameSet);

        stage.show();
    }
    @FXML
    private void setHover(){
        img1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img1.setOpacity(0.5);
            }
        });
        img1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img1.setOpacity(1);
            }
        });
        img2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img2.setOpacity(0.5);
            }
        });
        img2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img2.setOpacity(1);
            }
        });
        img3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img3.setOpacity(0.5);
            }
        });
        img3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img3.setOpacity(1);
            }
        });
        img4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img4.setOpacity(0.5);
            }
        });
        img4.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img4.setOpacity(1);
            }
        });
        img5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img5.setOpacity(0.5);
            }
        });
        img5.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img5.setOpacity(1);
            }
        });
        img6.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img6.setOpacity(0.5);
            }
        });
        img6.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img6.setOpacity(1);
            }
        });
        logout.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logout.setOpacity(0.5);
            }
        });
        logout.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logout.setOpacity(1);
            }
        });

    }




}
