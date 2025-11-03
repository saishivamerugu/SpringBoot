package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    private String email;
    private String password;
}




/*
 * @Getter
 * @Setter
 * we need not to write anything all are covered through the annotations
 * 
 * lombok provides diffenent annotations 
 * if we have final varibales we need requiredargs constructor 
 * for the final variables only it will create the constructor 
 * 
 * 
 * @Data annotation is consists of all the above things execpt the alll args construcotr and no args constructor 
 * 
 * 
 */
