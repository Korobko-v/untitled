package org.levelup.trello.service.hibernate;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.BoardColumn;
import org.levelup.trello.model.User;
import org.levelup.trello.service.BoardColumnRepository;
import org.levelup.trello.service.BoardRepository;

public class HibernateBoardColumnRepositoryIntegrationTest {
    private final SessionFactory factory = H2HibernateUtils.getFactory();
    private HibernateBoardColumnRepository columnRepository = new HibernateBoardColumnRepository(factory);

    @Test
    public void testCAddColumn_whenDataIsValid() {
        String name = "testColumn";
        Integer order = 2;
        User user = new HibernateUserRepository(factory).createUser("login", "log@mail.ru", "vasya", null);
        Integer userId = user.getId();
        Board board = new HibernateBoardRepository(factory).createBoard("testBoard", false, userId);
        BoardColumn column = columnRepository.addColumn(name, order, board.getBoardId());
        Assertions.assertNotNull(column.getId());
        BoardColumn foundByName = columnRepository.findColumnByName(name);
        Assertions.assertEquals(foundByName.getId(), column.getId());
    }
    @Test
    public void testUpdateColumn_whenDataIsValid() {
        String boardName = "name3";
        Boolean favourite = true;

        String columnName = "testCol";
        Integer order = 1;

        User user = new HibernateUserRepository(factory).createUser("login", "log@mail.ru", "vasya", null);
        Integer userId = user.getId();

        Board board = new HibernateBoardRepository(factory).createBoard(boardName, favourite, userId);
        BoardColumn column = columnRepository.addColumn(columnName, order, board.getBoardId());
        String newName = "newColName";
        Integer newOrder = 2;
        BoardColumn column2= columnRepository.updateColumn(column.getId(), newName, newOrder, board.getBoardId());
        Assertions.assertNotEquals(column2, column);
        Assertions.assertNotNull(column2.getId());
        BoardColumn foundByName = columnRepository.findColumnByName(newName);
        Assertions.assertEquals(foundByName.getId(), column2.getId());
    }

    @Test
    public void testDeleteColumn_whenColumnExists() {

        Integer id = 1;
        BoardColumn column = columnRepository.deleteColumn(id);

        Assertions.assertNotNull(column.getName());
        Assertions.assertNotNull(column.getBoard());
        Assertions.assertNotNull(column.getId());
        Assertions.assertNotNull(column.getOrder());

        Assertions.assertNull(columnRepository.getColumnById(id));
        Assertions.assertNull(columnRepository.findColumnByName(column.getName()));
    }
}
