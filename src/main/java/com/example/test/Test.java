package com.example.test;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="aa")
public class Test{
    String javahome;

    String test;

    @Value("${java.version}")
    String javaVersion;

    String test1;

    String name;

    @Value("${aa.app.name}")
    String appName;

}
