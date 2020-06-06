package cz.swa.carmart.core.service;

import cz.swa.carmart.core.entity.Tracer;
import cz.swa.carmart.core.repository.TracerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TracerServiceImpl implements TracerService {

    private final TracerRepository tracerRepository;

    @Autowired
    public TracerServiceImpl(TracerRepository tracerRepository) {
        this.tracerRepository = tracerRepository;
    }


    public void saveTracer(Tracer tracer) {
        tracer.setValid(true);
        tracerRepository.save(tracer);
    }

    public Tracer getTracer(Long userId, Long tracerId) {
        return tracerRepository.getTracerByUserIdAndTracerId(userId, tracerId);
    }

    public List<Tracer> getAllTracersForUser(Long userId) {
        return tracerRepository.getAllByUserId(userId);
    }

    public void removeTracer(Long tracerId) {
        tracerRepository.deleteByTracerId(tracerId);
    }

    @Override
    public List<Tracer> getAllTracersByPrice(Integer price) {
        return tracerRepository.getAllByPrice(price);
    }
}