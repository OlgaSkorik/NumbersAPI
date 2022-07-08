package com.example.numbersapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String typeOfRole;
    private Date created;
    private Date updated;

    @ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY)
    private List<User> userList;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
