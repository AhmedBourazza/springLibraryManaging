package com.library.ensaf.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Pattern;

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


public class User {
    @Id
     String id;
    
     @Field("Identifier")
     @JsonProperty("Identifier")
    Integer identifier;

     @Field("fullname")
     @JsonProperty("fullname")
    String fullname;


    @Pattern(regexp = "^[a-zA-Z]+\\.[a-zA-Z]+@usmba\\.ac\\.ma$", message = "Invalid email format")
    @Field("email")
    @JsonProperty("email")
    String email;

    @Field("password")
    @JsonProperty("password")
    String password;

    @Field("librarian")
    @JsonProperty("librarian")
    Boolean lib;


}
