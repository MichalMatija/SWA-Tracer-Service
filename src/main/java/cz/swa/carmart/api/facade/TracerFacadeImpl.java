package cz.swa.carmart.api.facade;

import cz.swa.carmart.core.entity.CarInfo;
import cz.swa.carmart.core.entity.Offer;
import cz.swa.carmart.core.entity.Tracer;
import cz.swa.carmart.core.entity.TracerOffer;
import cz.swa.carmart.core.entity.User;
import cz.swa.carmart.core.service.EmailService;
import cz.swa.carmart.core.service.OfferService;
import cz.swa.carmart.core.service.TracerOfferService;
import cz.swa.carmart.core.service.TracerService;
import cz.swa.carmart.core.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Transactional
public class TracerFacadeImpl implements TracerFacade {

    private final TracerService tracerService;
    private final OfferService offerService;
    private final EmailService emailService;
    private final TracerOfferService tracerOfferService;
    private final UserService userService;

    @Autowired
    public TracerFacadeImpl(TracerService tracerService, OfferService offerService, EmailService emailService,
                            TracerOfferService tracerOfferService, UserService userService) {
        this.tracerService = tracerService;
        this.offerService = offerService;
        this.emailService = emailService;
        this.tracerOfferService = tracerOfferService;
        this.userService = userService;
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
        User user = userService.getUser();
        for (Tracer tracer : tracers) {
            String subject = "LFFM - Sledovač vysliedil";
            emailService.notifyUsers(user.getEmail(), subject, emailService.getEmailMessage(carInfo));
            tracerOfferService.save(createTracerOffer(tracer.getTracerId(), carInfo.getCarId()));
        }
    }

    @Override
    public void checkCars(Tracer tracer) {
        List<Offer> validOfferCars = offerService.getAllValidOffer(tracer.getPriceFrom(), tracer.getPriceTo(), tracer.getModel());

        for (Offer offer : validOfferCars) {
            String email = "michalmatija@gmail.com";
            String subject = "LFFM - Sledovač vysliedil";
            emailService.notifyUsers(email, subject, emailService.getEmailMessage(createCarInfo(offer.getCarId(), offer.getPrice(), offer.getModel())));
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
}