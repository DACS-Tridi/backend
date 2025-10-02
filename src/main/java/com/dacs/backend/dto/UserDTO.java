package com.dacs.backend.dto;

import com.dacs.backend.model.entity.Gender;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    
    private String name;
    
    private String email;
    
    private Gender gender;                 
    
    private String bornDate;            
    
    private String registerDate;        
    
    private Boolean Active;
    
    private String Description;
    
    private String userName;
    
    private String password;
}
