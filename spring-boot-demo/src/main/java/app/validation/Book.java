package app.validation;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

@Data
public class Book {

    @NotEmpty
    private String name;

    @NotNull
    private Integer price;
}
