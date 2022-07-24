package com.mrclsc.springcloudconfigclient.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix="property")
@ConstructorBinding
public record ConfigClientValue(String name, String description) {
}