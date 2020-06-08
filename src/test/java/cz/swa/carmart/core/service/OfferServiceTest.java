package cz.swa.carmart.core.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import cz.swa.carmart.core.entity.CarInfo;
import cz.swa.carmart.core.entity.Offer;
import cz.swa.carmart.core.repository.OfferRepository;

@RunWith(SpringRunner.class)
public class OfferServiceTest {

    @InjectMocks
    OfferServiceImpl offerService;

    @Mock
    private OfferRepository offerRepository;

    @Before
    public void setup() {
        offerService = new OfferServiceImpl(offerRepository);
    }

    @Test
    public void addOfferTest() {
        Long userId = 1L;
        CarInfo carInfo = createCarInfo();

        offerService.addCar(userId, carInfo);

        Mockito.verify(offerRepository, Mockito.times(1)).save(Mockito.any(Offer.class));
    }

    @Test
    public void removeCar() {
        Long carId = 5L;

        offerService.removeCar(carId);

        Mockito.verify(offerRepository, Mockito.times(1)).deleteByCarId(Mockito.eq(carId));
    }

    @Test
    public void getAllValidOffer() {
        int priceFrom = 100;
        int priceTo = 1000;
        String model = "Favorit";

        offerService.getAllValidOffer(priceFrom, priceTo, model);

        Mockito.verify(offerRepository, Mockito.times(1)).getAllValidOffers(Mockito.eq(priceFrom), Mockito.eq(priceTo), Mockito.eq(model));
    }

    private CarInfo createCarInfo() {
        CarInfo carInfo = new CarInfo();

        carInfo.setPrice(200);
        carInfo.setModel("Audi");
        carInfo.setCarId(1);

        return carInfo;
    }
}