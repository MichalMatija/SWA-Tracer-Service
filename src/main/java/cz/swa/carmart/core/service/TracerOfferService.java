package cz.swa.carmart.core.service;

import java.util.List;

import cz.swa.carmart.core.entity.TracerOffer;

public interface TracerOfferService {

    void save(TracerOffer tracerOffer);

    void remove(Long carId);

    void removeByTracerId(Long tracerId);

    List<Long> getAllOffersByTracerId(Long tracerId);

    List<Long> getAllOffersByTracerIds(List<Long> tracerIds);
}