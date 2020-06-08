package cz.swa.carmart.core.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import cz.swa.carmart.core.entity.TracerOffer;
import cz.swa.carmart.core.repository.TracerOfferRepository;

@RunWith(SpringRunner.class)
public class TracerOfferServiceTest {

    @InjectMocks
    TracerOfferServiceImpl tracerOfferService;

    @Mock
    private TracerOfferRepository tracerOfferRepository;

    @Before
    public void setup() {
        tracerOfferService = new TracerOfferServiceImpl(tracerOfferRepository);
    }

    @Test
    public void saveTracerOfferTest() {
        TracerOffer tracerOffer = createTracerOffer(2L);

        tracerOfferService.save(tracerOffer);

        Mockito.verify(tracerOfferRepository, Mockito.times(1)).save(Mockito.any(TracerOffer.class));
    }

    @Test
    public void removeByCarIdTest() {
        Long carId = 1L;

        tracerOfferService.remove(carId);

        Mockito.verify(tracerOfferRepository, Mockito.times(1)).deleteByCarId(Mockito.eq(carId));
    }

    @Test
    public void removeByTracerId() {
        Long tracerId = 12L;

        tracerOfferService.removeByTracerId(tracerId);

        Mockito.verify(tracerOfferRepository, Mockito.times(1)).deleteByTracerId(Mockito.eq(tracerId));
    }

    @Test
    @Ignore
    public void getAllOffersByTracerIdTest() {
        Long tracerId = 10L;
        List<TracerOffer> tracerOffers = new ArrayList<>();
        tracerOffers.add(createTracerOffer(1L));
        tracerOffers.add(createTracerOffer(3L));
        tracerOffers.add(createTracerOffer(2L));

        Mockito.when(tracerOfferRepository.getAllByTracerId(Mockito.anyLong())).thenReturn(tracerOffers);
        List<Long> allByTracerId = tracerOfferService.getAllOffersByTracerId(tracerId);

        Assertions.assertEquals(3, allByTracerId.size());
        Assertions.assertNotEquals(allByTracerId.get(0), allByTracerId.get(1));
        Assertions.assertNotEquals(allByTracerId.get(0), allByTracerId.get(2));
        Assertions.assertNotEquals(allByTracerId.get(1), allByTracerId.get(2));
        Mockito.verify(tracerOfferRepository, Mockito.times(1)).getAllByTracerId(tracerId);
    }

    private TracerOffer createTracerOffer(Long tracerOfferId) {
        TracerOffer tracerOffer = new TracerOffer();

        tracerOffer.setCarId(1L);
        tracerOffer.setTracerId(2L);
        tracerOffer.setTracerOfferId(tracerOfferId);

        return tracerOffer;
    }
}