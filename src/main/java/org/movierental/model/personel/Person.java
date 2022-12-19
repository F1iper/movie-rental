package org.movierental.model.personel;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Person {

    private UUID personId;

    private String firstname;
    private String lastname;
    private Position position;
    private BigDecimal salary;

}
