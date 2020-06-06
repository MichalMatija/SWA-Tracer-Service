package cz.swa.carmart.api.controller;

import cz.swa.carmart.api.facade.TracerFacade;
import cz.swa.carmart.api.mapper.TracerMapper;
import cz.swa.carmart.api.model.CreateTracerRequestTO;
import cz.swa.carmart.api.model.RemoveRequestTO;
import cz.swa.carmart.api.model.TracerResponseTO;
import cz.swa.carmart.api.model.UpdateTracerRequestTO;
import cz.swa.carmart.core.entity.Tracer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TracerController {

    private final TracerFacade tracerFacade;

    @Autowired
    public TracerController(TracerFacade tracerFacade) {
        this.tracerFacade = tracerFacade;
    }

    @RequestMapping(value = "/tracer/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TracerResponseTO> getAllTracersForUser(@PathVariable long userId) {
        return TracerMapper.mapListToWS(tracerFacade.getAllTracersForUser(userId));
    }

    @RequestMapping(value = "/tracer/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody CreateTracerRequestTO request) {
        Tracer newTracer = createTracer(request.getUserId(), request.getPriceFrom(), request.getPriceTo(), request.getModel());
        tracerFacade.saveTracer(newTracer);
        return ResponseEntity.status(HttpStatus.OK).body("Tracer added.");
    }

    @RequestMapping(value = "/tracer/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> edit(@RequestBody UpdateTracerRequestTO request) {
        Tracer existingTracer = tracerFacade.getTracer(request.getUserId(), request.getTracerId());
        if (existingTracer != null) {
            existingTracer = updateTracer(existingTracer, request.getPriceFrom(), request.getPriceTo(), request.getModel());
            tracerFacade.saveTracer(existingTracer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Tracer doesn't exist. UserId: " + request.getUserId() + " TracerId" + request.getTracerId());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Tracer edited.");
    }

    @RequestMapping(value = "/tracer/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeTracer(@RequestBody RemoveRequestTO request) {
        tracerFacade.removeTracer(request.getTracerId());
    }

    // TODO getAllCarsByTracerId

    // TODO getAllCarsByUserId

    private Tracer createTracer(long userId, int priceFrom, int priceTo, String model) {
        Tracer tracer = new Tracer();
        tracer.setUserId(userId);
        tracer.setPriceFrom(priceFrom);
        tracer.setPriceTo(priceTo);
        tracer.setModel(model);

        return tracer;
    }

    private Tracer updateTracer(Tracer oldTracer, Integer priceFrom, Integer priceTo, String model) {
        if (priceFrom != null) {
            oldTracer.setPriceFrom(priceFrom);
        }
        if (priceTo != null) {
            oldTracer.setPriceTo(priceTo);
        }
        if (model != null) {
            oldTracer.setModel(model);
        }

        return oldTracer;
    }
}