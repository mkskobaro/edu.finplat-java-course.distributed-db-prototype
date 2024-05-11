package edu.finplatjavacourse.distributeddbprototype.processing.searchservice.idsearch;

import lombok.Getter;
import lombok.Setter;

public class Node {
    @Setter
    @Getter
    private Long id,strNumber;
    @Setter
    @Getter
    int  height;

    public Node left, right;

    public  Node(Long Id,Long StrNumber) {
        id = Id;
        strNumber = StrNumber;
        height = 1;
    }
}
