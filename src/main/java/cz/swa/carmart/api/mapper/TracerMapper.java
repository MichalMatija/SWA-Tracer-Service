package cz.swa.carmart.api.mapper;

import cz.swa.carmart.api.model.TracerResponseTO;
import cz.swa.carmart.core.entity.Tracer;

import java.util.List;
import java.util.stream.Collectors;

public class TracerMapper {

    public static TracerResponseTO mapToWS(Tracer tracer) {
        TracerResponseTO response = new TracerResponseTO();

        response.setModel(tracer.getModel());
        response.setPriceFrom(tracer.getPriceFrom());
        response.setPriceTo(tracer.getPriceTo());
        response.setTracerId(tracer.getTracerId());
        response.setUserId(tracer.getUserId());

        return response;
    }

    public static List<TracerResponseTO> mapListToWS(List<Tracer> tracers) {
        return tracers.stream()
            .map(TracerMapper::mapToWS)
            .collect(Collectors.toList());
    }
}