package Data.MusicBand;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Location location; //Поле не может быть null
    private java.time.LocalDate birthday; //Поле не может быть null
    private Float height; //Поле может быть null, Значение поля должно быть больше 0
    private Country nationality; //Поле может быть null

    public Person(){
    }

    public Person(String name, Color eyeColor, Color hairColor, Location location, java.time.LocalDate birthday, Float height, Country nationality) {
        this.name = name;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
        this.birthday = birthday;
        this.height = height;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Color getEyeColor() {
        return eyeColor;
    }
    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }
    public Color getHairColor() {
        return hairColor;
    }
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public java.time.LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(java.time.LocalDate birthday) {
        this.birthday = birthday;
    }
    public Float getHeight() {
        return height;
    }
    public void setHeight(Float height) {
        this.height = height;
    }
    public Country getNationality() {
        return nationality;
    }
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

}
