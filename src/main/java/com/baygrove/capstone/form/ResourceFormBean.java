package com.baygrove.capstone.form;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ResourceFormBean {
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String url;

    @NotEmpty
    private String description;
    
    private MultipartFile imageFile;

    private String imageUrl;

    private List<String> topics;

    private String status;

    private Date createdAt;

    private Date updatedAt;
}
