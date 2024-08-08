package com.baygrove.capstone.dto;

import com.baygrove.capstone.database.entity.ResourceCategory;
import com.baygrove.capstone.database.entity.ResourceList;
import com.baygrove.capstone.database.entity.ResourceTopic;
import com.baygrove.capstone.database.enums.ResourceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResourceDTO {
    private boolean isAdded = false;

    private Integer id;

    private String name;

    private String url;

    private String description;

    private String imageUrl;

    private ResourceStatus status;

    private Date createdAt;

    private Date updatedAt;


    private List<ResourceCategory> resourceCategories;

    private List<ResourceTopic> resourceTopics;

    private List<ResourceList> resourceLists;
}
