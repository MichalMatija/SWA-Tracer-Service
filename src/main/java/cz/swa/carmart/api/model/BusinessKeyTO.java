package cz.swa.carmart.api.model;

public class BusinessKeyTO {

    Long userId;
    Long tracerId;

    public BusinessKeyTO(Long userId, Long tracerId) {
        this.userId = userId;
        this.tracerId = tracerId;
    }

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
}