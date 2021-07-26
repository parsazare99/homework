package ir.maktab56.hw8.domain;

public class Cart {
    private int userId;
    private int productId;
    private int number;
    private boolean isPaid;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }


    @Override
    public String toString() {
        return
                ", productId=" + productId +
                        ", number=" + number +
                        ", isPaid=" + isPaid;
    }
}
