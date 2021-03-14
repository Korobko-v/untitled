package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "students")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer rating;
}
