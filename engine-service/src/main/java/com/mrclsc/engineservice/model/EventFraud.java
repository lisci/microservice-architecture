package com.mrclsc.engineservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize
@JsonSerialize
public record EventFraud(String name, String type, LocalDate creationDate) implements Serializable {
}
