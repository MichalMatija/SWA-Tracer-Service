package cz.swa.carmart.core.service;

import cz.swa.carmart.core.entity.TracerOffer;
import cz.swa.carmart.core.repository.TracerOfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TracerOfferServiceImpl implements TracerOfferService {

    private final TracerOfferRepository tracerOfferRepository;

    @Autowired
    public TracerOfferServiceImpl(TracerOfferRepository tracerOfferRepository) {
        this.tracerOfferRepository = tracerOfferRepository;
    }

    @Override
    public void save(TracerOffer tracerOffer) {
        tracerOfferRepository.save(tracerOffer);
    }

    @Override
    public void remove(Long carId) {
        tracerOfferRepository.deleteByCarId(carId);
    }
}