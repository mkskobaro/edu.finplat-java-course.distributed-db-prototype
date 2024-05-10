package edu.finplatjavacourse.distributeddbprototype.handler.response;


import edu.finplatjavacourse.distributeddbprototype.entity.Hotel;
import lombok.*;

import java.util.Collection;


@Builder
@EqualsAndHashCode
@ToString
@Getter
public class ReadResponse implements Response {
    @Singular
    private final Collection<Hotel> hotels;
}