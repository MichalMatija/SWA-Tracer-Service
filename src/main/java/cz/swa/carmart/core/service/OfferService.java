package cz.swa.carmart.core.service;

import java.util.List;

import cz.swa.carmart.core.entity.CarInfo;
import cz.swa.carmart.core.entity.Offer;

public interface OfferService {

    void addCar(Long userId, CarInfo carInfo);

    void removeCar(Long carId);

    List<Offer> getAllValidOffer(int priceFrom, int priceTo, String model);

    List<Offer> getAllByCarIds(List<Long> carIds);
}