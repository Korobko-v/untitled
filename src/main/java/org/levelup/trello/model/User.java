package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;

@Getter
//@Setter
@Entity
@Table(name = "users")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id // primary key из таблицы users
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    private String login;
    private String email;

}
