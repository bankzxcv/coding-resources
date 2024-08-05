package com.baygrove.capstone.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resources_lists")
public class ResourceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "resource_id")
    private Integer resourceId;
}
