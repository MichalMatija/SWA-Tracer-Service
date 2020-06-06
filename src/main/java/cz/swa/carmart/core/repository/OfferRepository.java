package cz.swa.carmart.core.repository;

import cz.swa.carmart.core.entity.Offer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    void deleteByCarId(Long carId);
}