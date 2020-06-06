package cz.swa.carmart.core.service;

import cz.swa.carmart.api.model.EmailRequest;
import cz.swa.carmart.core.entity.CarInfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${email.service.url}")
    private String emailServiceUrl;

    @Override
    public void notifyUsers(String recipient, String subject, String message) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<EmailRequest> request = new HttpEntity<>(new EmailRequest(recipient, subject, message));
        String response = restTemplate.postForObject(emailServiceUrl, request, String.class);
    }

    @Override
    public String getEmailMessage(CarInfo carInfo) {
        return MessageFormat.format(
            "Dobry den, \nMáme pro Vás skvelu správu. Váš sledovač vysledoval nové auto. \nParametre auta: \n    Id auta: {0}\n    Model: {1}\n    Cena: {2}\n\n\nS pozdravom\nVáš autobazár LFFM",
            carInfo.getCarId(), carInfo.getModel(), carInfo.getPrice());
    }
}