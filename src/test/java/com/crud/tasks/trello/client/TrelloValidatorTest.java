package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloValidatorTest {

    private final TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    void validateCard() {
    }

    @Test
    public void trelloBoardValidatorTest(){
        //Given
        List<TrelloList> mappedTrello =
                List.of(new TrelloList("1", "list", false));
        TrelloBoard firstTrelloBoard = new TrelloBoard("1", "something", mappedTrello);
        TrelloBoard secondTrelloBoard = new TrelloBoard("2", "somethint too", mappedTrello);
        TrelloBoard testTrelloBoard = new TrelloBoard("3", "test", mappedTrello);
        List<TrelloBoard> trelloBoardss = List.of(firstTrelloBoard, secondTrelloBoard, testTrelloBoard);

        //When
        List<TrelloBoard> newTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoardss);

        //Then
        assertEquals(List.of(firstTrelloBoard, secondTrelloBoard), newTrelloBoards);
    }
}
