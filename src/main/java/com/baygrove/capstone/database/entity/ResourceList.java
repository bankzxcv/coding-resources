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

    @Column(name = "list_id", insertable = false, updatable = false)
    private Integer listId;

    @Column(name = "resource_id", insertable = false, updatable = false)
    private Integer resourceId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "list_id", nullable = false)
    private UserList userList;
}
