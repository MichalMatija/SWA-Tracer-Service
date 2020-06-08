package cz.swa.carmart.core.service;

import cz.swa.carmart.core.entity.Tracer;
import cz.swa.carmart.core.entity.TracerOffer;
import cz.swa.carmart.core.repository.TracerOfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void removeByTracerId(Long tracerId) {
        tracerOfferRepository.deleteByTracerId(tracerId);
    }

    @Override
    public List<Long> getAllOffersByTracerId(Long tracerId) {
        List<TracerOffer> allByTracerId = tracerOfferRepository.getAllByTracerId(tracerId);
        List<Long> carIds = new ArrayList<>();
        for (TracerOffer tracerOffer : allByTracerId) {
            carIds.add(tracerOffer.getCarId());
        }

        return carIds;
//        return allByTracerId.stream()
//            .map(TracerOffer::getCarId)
//            .collect(Collectors.toList());
    }

    @Override
    public List<Long> getAllOffersByTracerIds(List<Long> tracerIds) {
        return tracerOfferRepository.findByTracerIdIn(tracerIds).stream()
            .map(TracerOffer::getCarId)
            .collect(Collectors.toList());
    }
}