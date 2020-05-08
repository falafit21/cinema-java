package Cinema;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

public class Video {
    @FXML
    MediaView media;
    @FXML ImageView close;
    @FXML
    Label titles;

    private Movie movie;
    private Media media1;
    private MediaPlayer mediaPlayer;
    private String username;
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setHover(){
        close.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                close.setOpacity(0.5);
            }
        });
        close.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                close.setOpacity(1);
            }
        });


    }

    @FXML public void closeAciton(MouseEvent event) throws IOException {
        mediaPlayer.stop();
        media.setMediaPlayer(mediaPlayer);
        ImageView a = (ImageView) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieDetails.fxml"));
        stage.setScene(new Scene(loader.load(), 620, 439));
        MovieDetailsController movieDetailsController = loader.getController();
        movieDetailsController.setMovieDetail(movie);
        movieDetailsController.setUsername(username);

        //describeMovieController.
        stage.show();

    }

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String pathVideo = getClass().getResource("/"+movie.getTrailer()).toExternalForm();
                    media1 = new Media(pathVideo);
                    mediaPlayer = new MediaPlayer(media1);
                    mediaPlayer.play();
                    media.setMediaPlayer(mediaPlayer);
                    titles.setText(movie.getMovie());
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });
        media.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(mediaPlayer.isAutoPlay()){
                    mediaPlayer.stop();
                    mediaPlayer.setAutoPlay(false);
                }
                else{
                    mediaPlayer.stop();
                    mediaPlayer.setAutoPlay(true);

                }
            }
        });
    }
}
