package cz.swa.carmart.core.service;

import cz.swa.carmart.core.entity.CarInfo;

public interface EmailService {

    void notifyUsers(String recipient, String subject, String message);

    String getEmailMessage(CarInfo carInfo);
}