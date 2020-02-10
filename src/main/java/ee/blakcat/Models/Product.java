package ee.blakcat.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;
@Table (name = "products")
@Entity
public class Product {
    @Id
    private String id;
    private String name, description;
    private double price;
    private Category category;
    private Status status;
    private int countAvailable;

    public Product(String name, double price, Category category, String description, int countAvailable) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.description=description;
        this.category = category;
        this.status=Status.ACTIVE;
        this.countAvailable=countAvailable;
    }

    public Product(String id, String name, String description, double price, Category category, int countAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.status=Status.ACTIVE;
        this.countAvailable=countAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Product() {
    }

public void added () {
        countAvailable++;
        if (countAvailable>0) {
            status=Status.ACTIVE;
        }
}
public void removed () {
        countAvailable--;
        if (countAvailable<=0) {
            status=Status.LOCKED;
        }
}

    public int getCountAvailable() {
        return countAvailable;
    }

    public void setCountAvailable(int countAvailable) {
        this.countAvailable = countAvailable;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                countAvailable == product.countAvailable &&
                id.equals(product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                category == product.category &&
                status == product.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, category, status, countAvailable);
    }
}
