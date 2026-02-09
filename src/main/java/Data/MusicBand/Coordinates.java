package Data.MusicBand;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Float x; //Поле не может быть null
    private float y;

    public Coordinates(){

    }
    public Coordinates(Float x, Float y) {
        this.x = x;
        this.y = y;
    }
    public Float getX() {
        return x;
    }
    public void setX(Float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(Float y) {
        this.y = y;
    }
}
