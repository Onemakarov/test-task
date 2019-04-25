package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AssessedValueDto;
import com.mcb.creditfactory.model.AssessedValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValueConverter {

    public AssessedValue fromDto(AssessedValueDto dto) {
        return new AssessedValue(
                dto.getId(),
                dto.getValue(),
                dto.getDate()
        );
    }

    public AssessedValueDto toDto(AssessedValue model) {
        return new AssessedValueDto(
                model.getId(),
                model.getValue(),
                model.getDate()
        );
    }

    public List<AssessedValue> fromDto(List<AssessedValueDto> dtoList) {
        List<AssessedValue> modelList = new ArrayList<>();
        for (AssessedValueDto dto : dtoList) {
            modelList.add(fromDto(dto));
        }
        return modelList;
    }

    public List<AssessedValueDto> toDto(List<AssessedValue> modelList) {
        List<AssessedValueDto> dtoList = new ArrayList<>();
        for (AssessedValue model : modelList) {
            dtoList.add(toDto(model));
        }
        return dtoList;
    }
}
