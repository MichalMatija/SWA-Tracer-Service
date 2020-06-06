package cz.swa.carmart.core.repository;

import cz.swa.carmart.core.entity.TracerOffer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TracerOfferRepository extends JpaRepository<TracerOffer, Long> {

    void deleteByCarId(Long carId);
}