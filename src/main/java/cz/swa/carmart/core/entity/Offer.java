package cz.swa.carmart.core.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Offer {

    private Long carId;
    private Long userId;
    private Integer price;
    private String model;

    @Id
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}