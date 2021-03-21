package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "columns")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "column_order")
    private Integer order;
//   @Column(name = "board_id")
//    private Integer boardId;
}
