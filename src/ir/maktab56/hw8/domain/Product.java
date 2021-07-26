package ir.maktab56.hw8.domain;

public class Product {


    private int id;

    private String name;

    private int price;

    private int inventory;


    private Category category = new Category();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getCategoryId() {
        return category.getId();
    }

    public void setCategoryId(int categoryId) {
        this.category.setId(categoryId);
    }

    @Override
    public String toString() {
        return "Product id " + id +
                ", name = '" + name + '\'' +
                ", price = " + price +
                ", inventory = " + inventory +
                ", category id = " + getCategoryId();

    }
}
