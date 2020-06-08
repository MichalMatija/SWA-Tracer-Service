package cz.swa.carmart.core.repository;

import cz.swa.carmart.core.entity.Offer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    void deleteByCarId(Long carId);

    @Query(value = "select o from Offer o "
                   + "where (:priceFrom >= o.price and :priceTo <= o.price) or :model = o.model")
    List<Offer> getAllValidOffers(@Param("priceFrom") int priceFrom, @Param("priceTo") int priceTo, @Param("model") String model);

    List<Offer> findByCarIdIn(List<Long> carIdList);
}