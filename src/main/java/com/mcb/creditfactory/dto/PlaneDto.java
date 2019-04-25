package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("plane")
public class PlaneDto implements Collateral {
    private Long id;
    private List<AssessedValueDto> values;
    private String brand;
    private String model;
    private String manufacturer;
    private Integer fuelCapacity;
    private Integer seats;
    private Short year;

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }
}
