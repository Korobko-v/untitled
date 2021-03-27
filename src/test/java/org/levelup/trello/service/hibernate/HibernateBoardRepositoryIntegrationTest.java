package org.levelup.trello.service.hibernate;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.levelup.trello.hibernate.HibernateUtils;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.User;

public class HibernateBoardRepositoryIntegrationTest {
    private final SessionFactory factory = H2HibernateUtils.getFactory();
    private HibernateBoardRepository boardRepository = new HibernateBoardRepository(factory);


    @Test
    public void testCreateBoard_whenDataIsValid_thenCreateBoard() {
        String name = "name3";
        Boolean favourite = true;
        Board board = boardRepository.createBoard(name, favourite, 5);
        Assertions.assertNotNull(board.getBoardId());
        Board foundByName = boardRepository.findBoardByName(name);
        Assertions.assertEquals(foundByName.getBoardId(), board.getBoardId());
    }
}
