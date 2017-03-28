package com.yegor.entity;

import javax.persistence.*;

/**
 * Created by YegorKost on 21.03.2017.
 */
@Entity
@Table(name = "laptops")
@NamedQuery(name = "LaptopEntity.getAllLaptops", query = "select l from LaptopEntity l")
public class LaptopEntity implements Comparable<LaptopEntity> {
    private String model;
    private String make;
    private Float screen;
    private String processor;
    private Integer memory;
    private String image;
    private Integer amount;
    private Double price;

    @Id
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Basic
    @Column(name = "screen")
    public Float getScreen() {
        return screen;
    }

    public void setScreen(Float screen) {
        this.screen = screen;
    }

    @Basic
    @Column(name = "processor")
    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Basic
    @Column(name = "memory")
    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LaptopEntity that = (LaptopEntity) o;

        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (make != null ? !make.equals(that.make) : that.make != null) return false;
        if (screen != null ? !screen.equals(that.screen) : that.screen != null) return false;
        if (processor != null ? !processor.equals(that.processor) : that.processor != null) return false;
        if (memory != null ? !memory.equals(that.memory) : that.memory != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = model != null ? model.hashCode() : 0;
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        result = 31 * result + (processor != null ? processor.hashCode() : 0);
        result = 31 * result + (memory != null ? memory.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(LaptopEntity o) {
        return o.getModel().compareTo(this.getModel());
    }
}
