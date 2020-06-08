package cz.swa.carmart.core.repository;

import cz.swa.carmart.core.entity.Tracer;
import cz.swa.carmart.core.entity.TracerOffer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TracerOfferRepository extends JpaRepository<TracerOffer, Long> {

    void deleteByCarId(Long carId);

    void deleteByTracerId(Long tracerId);

    List<TracerOffer> getAllByTracerId(Long tracerId);

    List<TracerOffer> findByTracerIdIn(List<Long> tracerIdList);
}