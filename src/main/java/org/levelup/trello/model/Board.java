package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Entity
@Table(name = "boards")
@Setter
@ToString(exclude = "owner")
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

    @ManyToOne // by default, owner_id
    @JoinColumn(name = "owner_id") // здесь: связь с таблицей происходит по колонке owner_id
    public User owner;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id", nullable = false) // здесь: колонка в таблице columns, которая является foreign key на таблицу boards
    private Collection<BoardColumn> columns;

//    @Column(name = "owner_id")
//    private int ownerId;
}
