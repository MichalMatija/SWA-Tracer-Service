package cz.swa.carmart.api.model;

public class UpdateTracerRequestTO {

    Long userId;
    Long tracerId;
    Integer priceFrom;
    Integer priceTo;
    String model;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTracerId() {
        return tracerId;
    }

    public void setTracerId(Long tracerId) {
        this.tracerId = tracerId;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}