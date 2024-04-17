package com.library.ensaf.server.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
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
@Document(collection = "History")
public class History {

    @Id
    private String id;

  
    @Field("User")
    @JsonProperty("User")
    private Integer user;

    @Field("Book")
    @JsonProperty("Book")
    private Integer book;

    @Field("BorrowDate")
    @JsonProperty("BorrowDate")
    private Date borrowDate;

    @Field("ReturnDate")
    @JsonProperty("ReturnDate")
    private Date returnDate;
    
    @Field("Returned")
    @JsonProperty("Returned")
    private Boolean returned;


    @Field("Delay")
    @JsonProperty("Delay")
    private Boolean delay;
}
