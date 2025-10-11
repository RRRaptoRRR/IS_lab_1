package Data;

public class MusicBand {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private MusicGenre genre; //Поле может быть null
    private int numberOfParticipants; //Значение поля должно быть больше 0
    private long singlesCount; //Значение поля должно быть больше 0
    private String description; //Поле не может быть null
    private Album bestAlbum; //Поле может быть null
    private long albumsCount; //Значение поля должно быть больше 0
    private java.time.LocalDate establishmentDate; //Поле не может быть null
    private Person frontMan; //Поле не может быть null

    public MusicBand(Long id, String name, Coordinates coordinates, java.time.LocalDate creationDate, MusicGenre musicGenre, int numberOfParticipants, long singlesCount, String description, Album bestAlbum, long albumsCount, java.time.LocalDate establishmentDate, Person frontMan ) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.genre = musicGenre;
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.description = description;
        this.bestAlbum = bestAlbum;
        this.albumsCount = albumsCount;
        this.establishmentDate = establishmentDate;
        this.frontMan = frontMan;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public java.time.LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(java.time.LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public MusicGenre getGenre() {
        return genre;
    }
    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }
    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
    public long getSinglesCount() {
        return singlesCount;
    }
    public void setSinglesCount(long singlesCount) {
        this.singlesCount = singlesCount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Album getBestAlbum() {
        return bestAlbum;
    }
    public void setBestAlbum(Album bestAlbum) {
        this.bestAlbum = bestAlbum;
    }
    public long getAlbumsCount() {
        return albumsCount;
    }
    public void setAlbumsCount(long albumsCount) {
        this.albumsCount = albumsCount;
    }
    public java.time.LocalDate getEstablishmentDate() {
        return establishmentDate;
    }
    public void setEstablishmentDate(java.time.LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }
    public Person getFrontMan() {
        return frontMan;
    }
    public void setFrontMan(Person frontMan) {
        this.frontMan = frontMan;
    }
}
