package Data;

import java.io.Serializable;

public class Album implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Double sales; //Поле может быть null, Значение поля должно быть больше 0

    public Album(){
    }
    public Album(String name, Double sales) {
        this.name = name;
        this.sales = sales;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getSales() {
        return sales;
    }
    public void setSales(Double sales) {
        this.sales = sales;
    }
}
