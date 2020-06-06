package cz.swa.carmart.api.facade;

import cz.swa.carmart.core.entity.CarInfo;
import cz.swa.carmart.core.entity.Tracer;
import cz.swa.carmart.core.entity.TracerOffer;
import cz.swa.carmart.core.service.EmailService;
import cz.swa.carmart.core.service.OfferService;
import cz.swa.carmart.core.service.TracerOfferService;
import cz.swa.carmart.core.service.TracerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class TracerFacadeImpl implements TracerFacade {

    private final TracerService tracerService;
    private final OfferService offerService;
    private final EmailService emailService;
    private final TracerOfferService tracerOfferService;

    @Autowired
    public TracerFacadeImpl(TracerService tracerService, OfferService offerService, EmailService emailService,
                            TracerOfferService tracerOfferService) {
        this.tracerService = tracerService;
        this.offerService = offerService;
        this.emailService = emailService;
        this.tracerOfferService = tracerOfferService;
    }

    @Override
    public void saveTracer(Tracer tracer) {
        tracerService.saveTracer(tracer);
    }

    @Override
    public Tracer getTracer(Long userId, Long tracerId) {
        return tracerService.getTracer(userId, tracerId);
    }

    @Override
    public List<Tracer> getAllTracersForUser(Long userId) {
        return tracerService.getAllTracersForUser(userId);
    }

    @Override
    public void removeTracer(Long tracerId) {
        tracerService.removeTracer(tracerId);
    }

    @Override
    public void check(Long userId, CarInfo carInfo) {
        List<Tracer> tracers = tracerService.getAllTracersByPrice(carInfo.getPrice());
        offerService.addCar(userId, carInfo);

        for (Tracer tracer : tracers) {
            // TODO get UserInfo
            String email = "michalmatija@gmail.com";
            String subject = "LFFM - Sledovač vysliedil";
            emailService.notifyUsers(email, subject, emailService.getEmailMessage(carInfo));
            tracerOfferService.save(createTracerOffer(tracer.getTracerId(), carInfo.getCarId()));
        }
    }

    @Override
    public void removeCar(Long carId) {
        offerService.removeCar(carId);
        tracerOfferService.remove(carId);
    }

    private TracerOffer createTracerOffer(Long tracerId, Long cardId) {
        TracerOffer tracerOffer = new TracerOffer();

        tracerOffer.setCarId(cardId);
        tracerOffer.setTracerId(tracerId);

        return tracerOffer;
    }

    private Map<Long, List<Tracer>> getTracersByUser(List<Tracer> tracers) {
        Map<Long, List<Tracer>> userTracerMap = new HashMap<>();

        for (Tracer tracer : tracers) {
            if (userTracerMap.containsKey(tracer.getUserId())) {
                userTracerMap.get(tracer.getUserId()).add(tracer);
            } else {
                List<Tracer> userTracers = new ArrayList<>();
                userTracers.add(tracer);
                userTracerMap.put(tracer.getUserId(), userTracers);
            }
        }

        return userTracerMap;
    }
}