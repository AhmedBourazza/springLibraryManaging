package com.library.ensaf.server.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "users")

public class Student extends User {

    @Field("CNE")
    @JsonProperty("CNE")
    String CNE;

    @Field("Delay")
    @JsonProperty("Delay")
    Integer Delay;

}