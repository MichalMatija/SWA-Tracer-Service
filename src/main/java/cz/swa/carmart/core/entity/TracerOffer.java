package cz.swa.carmart.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TracerOffer {

    private Long tracerOfferId;
    private Long tracerId;
    private Long carId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getTracerOfferId() {
        return tracerOfferId;
    }

    public void setTracerOfferId(Long tracerOfferId) {
        this.tracerOfferId = tracerOfferId;
    }

    public Long getTracerId() {
        return tracerId;
    }

    public void setTracerId(Long tracerId) {
        this.tracerId = tracerId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}