package com.project.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Props {

    @Value("${spring.info.name}")
    public String name;

    @Value("${spring.info.password}")
    public String password;
}
