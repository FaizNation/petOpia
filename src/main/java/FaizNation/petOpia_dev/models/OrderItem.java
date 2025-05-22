package FaizNation.petOpia_dev.models;

public class OrderItem {
    private String petName;
    private String petType;
    private int quantity;
    private double price;
    private double discount;

    public OrderItem(String petName, String petType, int quantity, double price, double discount) {
        this.petName = petName;
        this.petType = petType;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public double getSubtotal() {
        return price * (1 - discount) * quantity;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
//     public void setPhone(String phone) {