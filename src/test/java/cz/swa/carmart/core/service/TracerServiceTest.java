package cz.swa.carmart.core.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import cz.swa.carmart.core.entity.Tracer;
import cz.swa.carmart.core.repository.TracerRepository;

@RunWith(SpringRunner.class)
public class TracerServiceTest {

    @InjectMocks
    TracerServiceImpl tracerService;

    @Mock
    private TracerRepository tracerRepository;

    @Before
    public void setup() {
        tracerService = new TracerServiceImpl(tracerRepository);
    }

    @Test
    public void saveTracerTest() {
        Tracer tracer = createTracer();

        tracerService.saveTracer(tracer);

        Mockito.verify(tracerRepository, Mockito.times(1)).save(Mockito.any(Tracer.class));
    }

    Tracer createTracer() {
        Tracer tracer = new Tracer();

        tracer.setModel("Audi");
        tracer.setPriceFrom(200);
        tracer.setPriceTo(500);
        tracer.setUserId(2L);

        return tracer;
    }


}