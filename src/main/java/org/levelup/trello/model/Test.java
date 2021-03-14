package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "team")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer number;
}
