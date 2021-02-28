package org.levelup.trello.model;

import lombok.*;

//@Getter
//@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String login;
    private String email;

}
