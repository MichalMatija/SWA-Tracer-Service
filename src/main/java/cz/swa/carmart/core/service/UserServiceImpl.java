package cz.swa.carmart.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cz.swa.carmart.core.entity.User;

@Service
public class UserServiceImpl implements UserService {

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Override
    public User getUser() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(userServiceUrl, User.class);
    }
}