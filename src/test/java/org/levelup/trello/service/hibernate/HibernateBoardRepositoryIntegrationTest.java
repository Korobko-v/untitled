package org.levelup.trello.service.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.levelup.trello.hibernate.HibernateUtils;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HibernateBoardRepositoryIntegrationTest {
    private final SessionFactory factory = H2HibernateUtils.getFactory();
    private HibernateBoardRepository boardRepository = new HibernateBoardRepository(factory);


    @Test
    public void testCreateBoard_whenDataIsValid_thenCreateBoard() {
        String name = "name3";
        Boolean favourite = true;
        User user = new HibernateUserRepository(factory).createUser("login", "log@mail.ru", "vasya", null);
        Integer userId = user.getId();

        Board board = boardRepository.createBoard(name, favourite, userId);
        Assertions.assertNotNull(board.getBoardId());
        Board foundByName = boardRepository.findBoardByName(name);
        Assertions.assertEquals(foundByName.getBoardId(), board.getBoardId());
    }

    @Test
    public void testUpdateBoard_whenDataIsValid() {
        String name = "name3";
        Boolean favourite = true;
        User user = new HibernateUserRepository(factory).createUser("login", "log@mail.ru", "vasya", null);
        Integer userId = user.getId();
        Board board = boardRepository.createBoard(name, favourite, userId);
        Board board2 = boardRepository.updateBoard(board.getBoardId(), "newName", false, userId);
        Assertions.assertNotEquals(board2, board);
        Assertions.assertNotNull(board2.getBoardId());
        Board foundByName = boardRepository.findBoardByName("newName");
        Assertions.assertEquals(foundByName.getBoardId(), board2.getBoardId());
    }

    @Test
    public void testDeleteBoard_whenBoardExists() {

        Integer id = 1;
        Board board = boardRepository.deleteBoard(id);

        Assertions.assertNotNull(board.getBoardId());
        Assertions.assertNotNull(board.getOwner());
        Assertions.assertNotNull(board.getName());
        Assertions.assertNotNull(board.getColumns());

        Assertions.assertNull(boardRepository.getBoardById(board.getBoardId()));
        Assertions.assertNull(boardRepository.findBoardByName(board.getName()));
    }
}
