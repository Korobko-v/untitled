package org.levelup.trello.service.hibernate;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.User;

public class HibernateBoardRepositoryIntegrationTest {
    private final SessionFactory factory = H2HibernateUtils.getFactory();
    private HibernateBoardRepository boardRepository = new HibernateBoardRepository(factory);

    @Test
    public void testCreateBoard_whenDataIsValid_thenCreateBoard() {
        String name = "name";
        Boolean favourite = true;
        Integer userId = 2;
        Board board = boardRepository.createBoard(name, favourite, userId);

        Assertions.assertNotNull(board.getBoardId());
        Board foundById = boardRepository.getBoardById(userId);
        Assertions.assertEquals(foundById, board.getBoardId());
    }
}
