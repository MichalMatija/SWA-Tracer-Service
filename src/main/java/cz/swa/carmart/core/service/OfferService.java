package cz.swa.carmart.core.service;

import cz.swa.carmart.core.entity.CarInfo;

public interface OfferService {

    void addCar(Long userId, CarInfo carInfo);

    void removeCar(Long carId);
}