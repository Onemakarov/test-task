package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.AssessedValueDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AssessedValue;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.CollateralModel;
import com.mcb.creditfactory.repository.CarRepository;
import com.mcb.creditfactory.service.CollateralAdapter;
import com.mcb.creditfactory.service.CollateralService;
import com.mcb.creditfactory.service.ValueConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService implements CollateralService {

    private ExternalApproveService approveService;

    private CarRepository carRepository;

    private ValueConverter valueConverter;

    @Autowired
    public CarService(ExternalApproveService approveService, CarRepository carRepository, ValueConverter valueConverter) {
        this.approveService = approveService;
        this.carRepository = carRepository;
        this.valueConverter = valueConverter;
    }

    @Override
    public boolean approve(Collateral collateral) {
        return approveService.approve(new CollateralAdapter(collateral)) == 0;
    }

    @Override
    public Collateral save(Collateral collateral) {
        Car car = (Car) fromDto(collateral);
        if (!approve(collateral)) {
            return null;
        }
        return toDto(carRepository.save(car));
    }

    @Override
    public Collateral getInfo(Collateral collateral) {
        return toDto(carRepository.findById(collateral.getId()).get());
    }

    @Override
    public Collateral getCollateralById(long collateralId) {
        return toDto(carRepository.findById(collateralId).get());
    }

    @Override
    public Collateral addValue(long carId, AssessedValueDto valueDto) {
        Car car = carRepository.findById(carId).get();
        AssessedValue value = valueConverter.fromDto(valueDto);
        car.getValues().add(value);
        return toDto(carRepository.save(car));
    }

    @Override
    public CollateralModel fromDto(Collateral dto) {
        CarDto carDto = (CarDto) dto;
        return new Car(
                carDto.getId(),
                valueConverter.fromDto(carDto.getValues()),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getPower(),
                carDto.getYear()
        );
    }

    @Override
    public Collateral toDto(CollateralModel model) {
        Car car = (Car) model;
        return new CarDto(
                car.getId(),
                valueConverter.toDto(car.getValues()),
                car.getBrand(),
                car.getModel(),
                car.getPower(),
                car.getYear()
        );
    }
}
