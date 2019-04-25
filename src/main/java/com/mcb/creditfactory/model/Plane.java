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
@Table(name = "AIRPLANE")
@DiscriminatorValue("PLANE")
public class Plane extends CollateralModel {
    private String brand;
    private String model;
    private String manufacturer;
    private Integer fuelCapacity;
    private Integer seats;

    @Column(name = "year_of_issue")
    private Short year;

    public Plane(long id, List<AssessedValue> values, String brand, String model, String manufacturer, int fuelCapacity, int seats, short year) {
        super(id, values);
        this.brand = brand;
        this.model = model;
        this.manufacturer = manufacturer;
        this.fuelCapacity = fuelCapacity;
        this.seats = seats;
        this.year = year;
    }

}
