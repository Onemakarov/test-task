package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AssessedValueDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.CollateralModel;

public interface CollateralService {
    boolean approve(Collateral collateral);
    @SuppressWarnings("ConstantConditions")
    Collateral save(Collateral collateral);
    Collateral getInfo(Collateral collateral);
    Collateral getCollateralById(long collateralId);
    Collateral addValue(long collateralId, AssessedValueDto valueDto);
    CollateralModel fromDto(Collateral dto);
    Collateral toDto(CollateralModel model);
}
