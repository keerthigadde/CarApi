package com.key.car.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
@ApiModel(value="Car entity", description="car data can be entered")
public class Car  implements Serializable {
    @Id
    @Column(name = "car_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ApiModelProperty(value="provide car name",required = false, name="name")
    private String name;
    @ApiModelProperty(value="provide car model",required = true, name="model")
    @NotNull
    private String model;
    @ApiModelProperty(value="provide car price",required = true, name="price")
    @NotNull
    private Double price;
    @ApiModelProperty(value="provide car fuelType",required = true, name="fuelType")
    @NotNull
    private String fuelType;
}
