package cz.swa.carmart.api.mapper;

import cz.swa.carmart.api.model.CheckRequestTO;
import cz.swa.carmart.core.entity.CarInfo;

public class OfferMapper {

    public static CarInfo mapToDomain(CheckRequestTO request) {
        CarInfo carInfo = new CarInfo();

        carInfo.setCarId(request.getCarId());
        carInfo.setPrice(request.getPrice());
        carInfo.setModel(request.getModel());

        return carInfo;
    }
}