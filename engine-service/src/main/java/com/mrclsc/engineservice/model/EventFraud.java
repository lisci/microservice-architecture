package com.mrclsc.engineservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize
@JsonSerialize
//@NoArgsConstructor
public record EventFraud(String name, String type, LocalDate creationDate) implements Serializable {
}
