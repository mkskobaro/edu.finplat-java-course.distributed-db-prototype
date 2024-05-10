package edu.finplatjavacourse.distributeddbprototype.request.response;


import edu.finplatjavacourse.distributeddbprototype.entity.Hotel;
import edu.finplatjavacourse.distributeddbprototype.request.parsing.Statement;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Builder
@EqualsAndHashCode
@ToString
@Getter
public class TextSearchResponse implements Response {
    private final Statement was;
    @Singular
    private final Set<Hotel> hotels = new HashSet<>();
}
