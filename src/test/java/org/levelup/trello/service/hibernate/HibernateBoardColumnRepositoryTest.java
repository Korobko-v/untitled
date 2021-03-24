package org.levelup.trello.service.hibernate;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.BoardColumn;
import org.levelup.trello.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HibernateBoardColumnRepositoryTest {
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;
    private HibernateBoardColumnRepository columnRepository;

    @BeforeEach
    public void setupRepository() {
        factory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);
        columnRepository = new HibernateBoardColumnRepository(factory);

        when(factory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @SneakyThrows
    @Test
    public void testAddColumn_whenAllDataIsValid_thenPersistColumn() {

        String name = "newColumn2";
        Integer order = 2;
        Integer boardId = 5;
        Board board = new Board();
        when(session.load(Board.class, boardId)).thenReturn(board);
        BoardColumn column = columnRepository.addColumn(name, order, boardId);

        board.setBoardId(5);

        assertEquals(name, column.getName());
        assertEquals(order, column.getOrder());
        assertEquals(boardId, column.getBoard().getBoardId());

    }
    @SneakyThrows
    @Test
    public void testDeleteColumn_whenBoardExists() {
        BoardColumn column = new BoardColumn();
        Integer id = 3;
        when(session.get((BoardColumn.class), id)).thenReturn(column);
        when(session.load((BoardColumn.class), id)).thenReturn(column);
        column.setId(id);

        assertEquals(id, columnRepository.getColumnById(id).getId());

        columnRepository.deleteColumn(id);
        verify(session).delete(any());
    }
    @SneakyThrows
    @Test
    public void testUpdateColumn_whenColumnExists() {

        Integer id = 3;
        String name = "newTestName";
        Integer order = 2;
        Integer boardId = 5;

        BoardColumn column = columnRepository.addColumn("oldname", 3, 1);
        when(session.get((BoardColumn.class), id)).thenReturn(column);
        when(session.load((BoardColumn.class), id)).thenReturn(column);
        column.setId(id);
        Board board = new Board();
        when(session.get((Board.class), boardId)).thenReturn(board);

        board.setBoardId(boardId);

        column = columnRepository.updateColumn(id, name, order, boardId);
        verify(session).update(any());
        assertEquals(column.getId(), id);
        assertEquals(column.getName(), name);
        assertEquals(order, column.getOrder());
        assertSame(board, column.getBoard());

    }
}