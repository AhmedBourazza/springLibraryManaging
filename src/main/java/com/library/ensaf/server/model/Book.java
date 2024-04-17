package com.library.ensaf.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Books")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "N_Inv", "Title", "Module", "Author", "Editor", "Img", "Available" })
public class Book {

    @Id
    String id;

    @Field("N_Inv")
    @JsonProperty("N_Inv")
    Integer NInv;

    @Field("Title")
    @JsonProperty("Title")
    String title;

    @Field("Module")
    @JsonProperty("Module")
    String module;

    @JsonProperty("Author")
    @Field("Author")
    String author;

    @Field("Editor")
    @JsonProperty("Editor")
    String editor;

    @Field("Img")
    @JsonProperty("Img")
    String img;

    @Field("Available")
    @JsonProperty("Available")
    Boolean available;
}