package Cinema;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Description {
    private ImageView imgTime;
    private Label describe;

    public Description(ImageView imgTime, Label describe) {
        this.imgTime = imgTime;
        this.describe = describe;
    }

    public ImageView getImgTime() {
        return imgTime;
    }

    public void setImgTime(ImageView imgTime) {
        this.imgTime = imgTime;
    }

    public Label getDescribe() {
        return describe;
    }

    public void setDescribe(Label describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Describe{" +
                "imgTime=" + imgTime +
                ", describe=" + describe +
                '}';
    }
}
