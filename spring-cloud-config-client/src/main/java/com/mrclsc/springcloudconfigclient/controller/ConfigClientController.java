package com.mrclsc.springcloudconfigclient.controller;

import com.mrclsc.springcloudconfigclient.models.ConfigClientValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/access")
public class ConfigClientController {


    @Autowired
    ConfigClientValue configClientValue;


    @GetMapping("/accessPropertyFile")
    public ConfigClientValue accesPropertyFile() {
        return configClientValue;
    }


}
