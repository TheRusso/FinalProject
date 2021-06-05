package com.example.demo.db.entities;

public class Item extends Entity{
    private String title;
    private String description;
    private float price;
    private int count;
    private String img;
    private int quantity;
    private int disable;
    private int category_id;


    public static class Builder{
        private Item item;

        public Builder() {
            item = new Item();
        }

        public Builder withTitle(String title){
            item.setTitle(title);
            return this;
        }

        public Builder withDescription(String description){
            item.setDescription(description);
            return this;
        }

        public Builder withPrice(Float price){
            item.setPrice(price);
            return this;
        }

        public Builder withCount(int count){
            item.setCount(count);
            return this;
        }

        public Builder withImg(String img){
            item.setImg(img);
            return this;
        }

        public Builder withQuantity(int quantity){
            item.setQuantity(quantity);
            return this;
        }

        public Builder withDisable(int disable){
            item.setDisable(disable);
            return this;
        }

        public Builder withCategory_id(int category_id){
            item.setCategory_id(category_id);
            return this;
        }

        public Item build(){
            return item;
        }
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", img='" + img + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
