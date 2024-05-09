package edu.finplatjavacourse.distributeddbprototype.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class Hotel {
    private Long id;
    private String name;
    private Long price;
}
