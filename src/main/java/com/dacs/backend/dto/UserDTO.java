package com.dacs.backend.dto;

import com.dacs.backend.model.entity.Gender;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    
    private String username;
    
    private Gender gender;                 
    
    private String bornDate;            
    
    private String registerDate;        
    
    private Boolean active;
    
    private String description;
}
