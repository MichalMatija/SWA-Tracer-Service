package cz.swa.carmart.api.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cz.swa.carmart.config.UserDetails;
import cz.swa.carmart.core.entity.CarInfo;
import cz.swa.carmart.core.entity.Offer;
import cz.swa.carmart.core.entity.Tracer;
import cz.swa.carmart.core.entity.TracerOffer;
import cz.swa.carmart.core.service.EmailService;
import cz.swa.carmart.core.service.OfferService;
import cz.swa.carmart.core.service.TracerOfferService;
import cz.swa.carmart.core.service.TracerService;

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
        tracerOfferService.removeByTracerId(tracerId);
    }

    @Override
    public void check(Long userId, CarInfo carInfo) {
        List<Tracer> tracers = tracerService.getAllTracersByPrice(carInfo.getPrice());
        offerService.addCar(userId, carInfo);
        UserDetails userDetails = getUserDetails();
        for (Tracer tracer : tracers) {
            String subject = "LFFM - Sledovač vysliedil";
            emailService.notifyUsers(userDetails.getEmail(), subject, emailService.getEmailMessage(carInfo));
            tracerOfferService.save(createTracerOffer(tracer.getTracerId(), carInfo.getCarId()));
        }
    }

    @Override
    public void checkCars(Tracer tracer) {
        List<Offer> validOfferCars = offerService.getAllValidOffer(tracer.getPriceFrom(), tracer.getPriceTo(), tracer.getModel());
        UserDetails userDetails = getUserDetails();
        for (Offer offer : validOfferCars) {
            String subject = "LFFM - Sledovač vysliedil";
            emailService.notifyUsers(userDetails.getEmail(), subject,
                                     emailService.getEmailMessage(createCarInfo(offer.getCarId(), offer.getPrice(), offer.getModel())));
            tracerOfferService.save(createTracerOffer(tracer.getTracerId(), offer.getCarId()));
        }
    }

    @Override
    public List<CarInfo> getAllCarsByTracerId(long tracerId) {
        List<Long> carIds = tracerOfferService.getAllOffersByTracerId(tracerId);

        return getAllCars(carIds);
    }

    @Override
    public List<CarInfo> getAllCarsByUserId(long userId) {
        List<Long> tracerIds = getAllTracersForUser(userId).stream()
            .map(Tracer::getTracerId)
            .collect(Collectors.toList());
        List<Long> carIds = tracerOfferService.getAllOffersByTracerIds(tracerIds);

        return getAllCars(carIds);
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

    private CarInfo createCarInfo(long cardId, int price, String model) {
        CarInfo carInfo = new CarInfo();

        carInfo.setCarId(cardId);
        carInfo.setPrice(price);
        carInfo.setModel(model);

        return carInfo;
    }

    private List<CarInfo> getAllCars(List<Long> carIds) {
        List<Offer> offers = offerService.getAllByCarIds(carIds);

        List<CarInfo> carInfos = new ArrayList<>();

        for (Offer offer : offers) {
            CarInfo carInfo = createCarInfo(offer.getCarId(), offer.getPrice(), offer.getModel());
            carInfos.add(carInfo);
        }

        return carInfos;
    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (UserDetails) authentication.getPrincipal();
    }
}