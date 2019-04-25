package com.mcb.creditfactory.service.plane;

import com.mcb.creditfactory.dto.AssessedValueDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.dto.PlaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AssessedValue;
import com.mcb.creditfactory.model.CollateralModel;
import com.mcb.creditfactory.model.Plane;
import com.mcb.creditfactory.repository.PlaneRepository;
import com.mcb.creditfactory.service.CollateralAdapter;
import com.mcb.creditfactory.service.CollateralService;
import com.mcb.creditfactory.service.ValueConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaneService implements CollateralService {

    private ExternalApproveService approveService;

    private PlaneRepository planeRepository;

    private ValueConverter valueConverter;

    @Autowired
    public PlaneService(ExternalApproveService approveService, PlaneRepository planeRepository, ValueConverter valueConverter) {
        this.approveService = approveService;
        this.planeRepository = planeRepository;
        this.valueConverter = valueConverter;
    }

    @Override
    public boolean approve(Collateral collateral) {
        return approveService.approve(new CollateralAdapter(collateral)) == 0;
    }

    @Override
    public Collateral save(Collateral collateral) {
        Plane plane = (Plane) fromDto(collateral);
        if (!approve(collateral)) {
            return null;
        }
        return toDto(planeRepository.save(plane));
    }

    @Override
    public Collateral getInfo(Collateral collateral) {
        Plane plane = planeRepository.findById(collateral.getId()).get();
        return toDto(plane);
    }

    @Override
    public Collateral getCollateralById(long collateralId) {
        return toDto(planeRepository.findById(collateralId).get());
    }

    @Override
    public Collateral addValue(long planeId, AssessedValueDto valueDto) {
        Plane plane = planeRepository.findById(planeId).get();
        AssessedValue value = valueConverter.fromDto(valueDto);
        plane.getValues().add(value);
        return toDto(planeRepository.save(plane));
    }

    @Override
    public CollateralModel fromDto(Collateral dto) {
        PlaneDto planeDto = (PlaneDto) dto;
        return new Plane(
                planeDto.getId(),
                valueConverter.fromDto(planeDto.getValues()),
                planeDto.getBrand(),
                planeDto.getModel(),
                planeDto.getManufacturer(),
                planeDto.getFuelCapacity(),
                planeDto.getSeats(),
                planeDto.getYear()
        );
    }

    @Override
    public Collateral toDto(CollateralModel model) {
        Plane plane = (Plane) model;
        return new PlaneDto(
                plane.getId(),
                valueConverter.toDto(plane.getValues()),
                plane.getBrand(),
                plane.getModel(),
                plane.getManufacturer(),
                plane.getFuelCapacity(),
                plane.getSeats(),
                plane.getYear()
        );
    }
}
