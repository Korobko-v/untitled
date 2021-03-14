package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "boards")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;
    private String name;
    private boolean favourite;
    @Column(name = "owner_id")
    private int ownerId;
}
