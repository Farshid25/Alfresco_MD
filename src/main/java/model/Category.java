package model;

public class Category {
    private  String category;
    private Float confidence;


    public Category(){

    }

    public Category(String category, Float confidence) {
        this.category = category;
        this.confidence = confidence;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + category + '\'' +
                ", confidence=" + confidence +
                '}';
    }
}
