package Cinema;

public class Movie {
    private String title;
    private String release;
    private String img;
    private String describe;
    private String describe1;
    private String[] time;
    private String trailer;



    public Movie(String title, String release, String img, String describe, String describe1, String[] time, String trailer) {
        this.title = title;
        this.release = release;
        this.img = img;
        this.describe = describe;
        this.describe1 = describe1;
        this.time = time;
        this.trailer = trailer;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getDescribe1() {
        return describe1;
    }

    public void setDescribe1(String describe1) {
        this.describe1 = describe1;
    }

    public String getTitle() {
        return title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public String getMovie() {
        return title;
    }

    public void setMovie(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
