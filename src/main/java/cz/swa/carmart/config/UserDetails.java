package cz.swa.carmart.config;

import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("user_id")
    private long userId;
    @SerializedName("user_mail")
    private String email;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}