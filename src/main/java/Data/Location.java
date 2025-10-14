package Data;

import java.io.Serializable;

public class Location implements Serializable {
    private Long x; //Поле не может быть null
    private double y;
    private String name; //Строка не может быть пустой, Поле не может быть null

    public Location(){

    }
    public Location(Long x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
