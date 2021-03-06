package cz.swa.carmart.core.service;

import cz.swa.carmart.core.entity.CarInfo;
import cz.swa.carmart.core.entity.Offer;
import cz.swa.carmart.core.repository.OfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void addCar(Long userId, CarInfo carInfo) {
        offerRepository.save(createOffer(userId, carInfo));
    }

    @Override
    public void removeCar(Long carId) {
        offerRepository.deleteByCarId(carId);
    }

    @Override
    public List<Offer> getAllValidOffer(int priceFrom, int priceTo, String model) {
        return offerRepository.getAllValidOffers(priceFrom, priceTo, model);
    }

    @Override
    public List<Offer> getAllByCarIds(List<Long> carIds) {
        return offerRepository.findByCarIdIn(carIds);
    }

    private Offer createOffer(Long userId, CarInfo carInfo) {
        Offer offer = new Offer();

        offer.setCarId(carInfo.getCarId());
        offer.setUserId(userId);
        offer.setModel(carInfo.getModel());
        offer.setPrice(carInfo.getPrice());

        return offer;
    }
}