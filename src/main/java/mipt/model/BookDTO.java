package mipt.model;

public class BookDTO {
    private String name;
    private Integer year;

    // Конструктор
    public BookDTO(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }
}
