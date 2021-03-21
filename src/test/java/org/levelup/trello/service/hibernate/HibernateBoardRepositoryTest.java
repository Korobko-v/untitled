package org.levelup.trello.service.hibernate;

import lombok.SneakyThrows;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.User;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HibernateBoardRepositoryTest {
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;
    private HibernateBoardRepository boardRepository;
    private HibernateUserRepository userRepository;

    @BeforeEach
    public void setupRepository() {
        factory = Mockito.mock(SessionFactory.class);
        session = Mockito.mock(Session.class);
        transaction = Mockito.mock(Transaction.class);
        boardRepository = new HibernateBoardRepository(factory);
        userRepository = new HibernateUserRepository(factory);

        Mockito.when(factory.openSession()).thenReturn(session);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);
    }

    @SneakyThrows
    @Test
    public void testCreateBoard_whenAllDataIsValid_thenPersistBoard() {
        //when
        Board board = boardRepository.createBoard("name10", true, 2);
        assertEquals("name10",board.getName());
        assertTrue(board.isFavourite());
        assertEquals(userRepository.getUserById(2), board.getOwner());

        ArgumentCaptor<Board> boardArgumentCaptor = ArgumentCaptor.forClass(Board.class);
        Mockito.verify(session).persist(boardArgumentCaptor.capture());
        Mockito.verify(transaction).commit();

        Assertions.assertSame(board, boardArgumentCaptor.getValue());
    }
}