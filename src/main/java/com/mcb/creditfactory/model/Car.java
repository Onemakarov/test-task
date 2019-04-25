package com.mcb.creditfactory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAR")
@DiscriminatorValue("CAR")
public class Car extends CollateralModel{
    private String brand;
    private String model;
    private Double power;

    @Column(name = "year_of_issue")
    private Short year;

    public Car(long id, List<AssessedValue> values, String brand, String model, double power, short year) {
        super(id, values);
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.year = year;
    }
}
