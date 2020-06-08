package cz.swa.carmart.api.facade;

import cz.swa.carmart.core.entity.CarInfo;
import cz.swa.carmart.core.entity.Tracer;

import java.util.List;

public interface TracerFacade {

    void saveTracer(Tracer tracer);

    Tracer getTracer(Long userId, Long tracerId);

    List<Tracer> getAllTracersForUser(Long userId);

    void removeTracer(Long tracerId);

    void check(Long userId, CarInfo carInfo);

    void checkCars(Tracer tracer);

    List<CarInfo> getAllCarsByTracerId(long tracerId);

    List<CarInfo> getAllCarsByUserId(long userId);

    void removeCar(Long carId);
}