package com.vishal.PersonDetails.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {

    private Integer id;

    @NotNull(message = "Please provide name")
    private String name;

    @NotNull(message = "Please provide age")
    private Integer age;

    @NotNull(message = "Please provide street")
    private String street;

    @NotNull(message = "Please provide state")
    private String state;

}
