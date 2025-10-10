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
}
