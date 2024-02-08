package Models;

public class IngredientType {
    private int id;
    private String name;
    private double ingredientPrice;
    private String ImageUrl;

    private int ingredenientQuantity;

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

    public double getPrice() {
        return ingredientPrice;
    }

    public void setPrice(double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}

