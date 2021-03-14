package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "cards")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String text;
    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "column_id")
    private Integer columnId;
}
