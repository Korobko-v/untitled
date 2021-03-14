package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "columns")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Columns {
    @Id
    private Integer id;
    private String name;
    @Column(name = "column_order")
    private Integer columnOrder;
    @Column(name = "board_id")
    private Integer boardId;
}
