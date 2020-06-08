package cz.swa.carmart.api.controller;

import cz.swa.carmart.api.facade.TracerFacade;
import cz.swa.carmart.api.mapper.OfferMapper;
import cz.swa.carmart.api.model.CheckRequestTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class OfferController {

    private final TracerFacade tracerFacade;

    @Autowired
    public OfferController(TracerFacade tracerFacade) {
        this.tracerFacade = tracerFacade;
    }

    @RequestMapping(value = "/offer/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void check(@RequestBody CheckRequestTO request, HttpServletResponse response) {
        tracerFacade.check(request.getUserId(), OfferMapper.mapToDomain(request));

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @RequestMapping(value = "/offer/{carId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void remove(@RequestBody Long carId, HttpServletResponse response) {
        tracerFacade.removeCar(carId);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}