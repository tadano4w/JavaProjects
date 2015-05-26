package app.validation;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Book {

    @NotNull
    private String name;

    @NotNull
    private Integer price;}
