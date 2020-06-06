package cz.swa.carmart.core.service;

import cz.swa.carmart.core.entity.TracerOffer;

public interface TracerOfferService {

    void save(TracerOffer tracerOffer);

    void remove(Long carId);
}