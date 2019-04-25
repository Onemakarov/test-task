package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AssessedValueDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class CollateralAdapter implements CollateralObject {
    private Collateral collateral;

    @Override
    public BigDecimal getValue() {
        return getLastAssessedValue().getValue();
    }

    @Override
    public Short getYear() {
        return collateral.getYear();
    }

    @Override
    public LocalDate getDate() {
        return getLastAssessedValue().getDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }

    private AssessedValueDto getLastAssessedValue() {
        List<AssessedValueDto> assessedValues = collateral.getValues();
        return Collections.max(assessedValues, Comparator.comparing(AssessedValueDto::getDate));
    }
}
