package com.baygrove.capstone.form;


import com.baygrove.capstone.validation.resource.ResourceNameUnique;
import com.baygrove.capstone.validation.resource.ResourceUrlUnique;
import jakarta.validation.constraints.NotEmpty;
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

    @ResourceNameUnique
    @NotEmpty
    private String name;

    @ResourceUrlUnique
    @NotEmpty
    private String url;

    @NotEmpty
    private String description;

    // TODO: Add custom validation to check image file is not null and valid extensions
    private MultipartFile imageFile;

    private String imageUrl;

    private List<Integer> topicIds;

    private String status;

    private Date createdAt;

    private Date updatedAt;
}
