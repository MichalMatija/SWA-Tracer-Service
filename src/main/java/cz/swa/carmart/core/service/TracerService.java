package cz.swa.carmart.core.service;

import cz.swa.carmart.core.entity.Tracer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TracerService {

    void saveTracer(Tracer tracer);

    Tracer getTracer(Long userId, Long tracerId);

    List<Tracer> getAllTracersForUser(Long userId);

    void removeTracer(Long tracerId);

    List<Tracer> getAllTracersByPrice(Integer price);
}