package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operator extends BaseModel {

    private String name;

    public Operator(String name) {
        super(-1);
        this.name = name;
    }
}
