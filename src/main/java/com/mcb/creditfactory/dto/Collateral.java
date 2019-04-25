package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mcb.creditfactory.external.CollateralType;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarDto.class),
        @JsonSubTypes.Type(value = PlaneDto.class)
})
public interface Collateral {
    List<AssessedValueDto> getValues();
    Short getYear();
    CollateralType getType();
    Long getId();
}
