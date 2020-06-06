package cz.swa.carmart.core.repository;

import cz.swa.carmart.core.entity.Tracer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TracerRepository extends JpaRepository<Tracer, Long> {

    Tracer getTracerByUserIdAndTracerId(Long userId, Long tracerId);

    @Query(value = "select t from Tracer t where t.userId = :userId and t.valid = true ")
    List<Tracer> getAllByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("update Tracer t set t.valid = false where t.tracerId = :tracerId")
    void deleteByTracerId(@Param("tracerId") Long tracerId);

    @Query(value = "select t from Tracer t where t.priceFrom <= :price and t.priceTo >= :price")
    List<Tracer> getAllByPrice(@Param("price") Integer price);
}