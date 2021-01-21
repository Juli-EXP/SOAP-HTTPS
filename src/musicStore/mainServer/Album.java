package musicStore.mainServer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Album")
public class Album {
    @XmlElement(name = "name")
    private final String name;
    @XmlElement(name = "price")
    private double price;

    public Album() {
        this("", 0f);
    }

    public Album(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
