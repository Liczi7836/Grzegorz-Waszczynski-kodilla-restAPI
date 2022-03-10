package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapperTestSuit {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void trelloCardMapperTest(){
        //Given
        TrelloCard trelloCard = new TrelloCard("First card", "It is first trello card", "1", "1255");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCard.getName(),trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(),trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }

    @Test
    public void trelloCardDtoMapperTest(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Second card","It is second trello card", "2", "1255");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(),trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(),trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(),trelloCard.getPos());
        assertEquals(trelloCardDto.getListId(),trelloCard.getListId());
    }

    @Test
    public void trelloListMapperTest(){
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("1", "First list", true);
        TrelloList trelloList2 = new TrelloList("2", "Second list", true);
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(trelloLists.get(0).getName(), trelloListDto.get(0).getName());
    }

    @Test
    public void trelloListDtoMapperTest(){
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "First list", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "Second list", true);
        trelloListDtoList.add(trelloListDto1);
        trelloListDtoList.add(trelloListDto2);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(trelloListDtoList.get(0).getName(), trelloLists.get(0).getName());
        assertEquals(trelloListDtoList.get(1).getName(), trelloLists.get(1).getName());
    }

    @Test
    public void trelloBoardMapperTest(){
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        List<TrelloList> trelloLists2 = new ArrayList<>();

        TrelloList trelloList1 = new TrelloList("1", "First list", true);
        TrelloList trelloList2 = new TrelloList("2", "Second list", true);
        TrelloList trelloList3 = new TrelloList("3", "Third list", true);
        TrelloList trelloList4 = new TrelloList("4", "Fourth list", true);

        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists2.add(trelloList3);
        trelloLists2.add(trelloList4);

        TrelloBoard trelloBoard1 = new TrelloBoard("1", "First board", trelloLists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "Second board", trelloLists2);
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(trelloBoards.get(0).getName(), trelloBoardDtoList.get(0).getName());
        assertEquals(trelloBoards.get(1).getName(), trelloBoardDtoList.get(1).getName());
    }

    @Test
    public void trelloBoardDtoMapperTest(){
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloLists = new ArrayList<>();
        List<TrelloListDto> trelloLists2 = new ArrayList<>();

        TrelloListDto trelloList1 = new TrelloListDto("1", "First list", true);
        TrelloListDto trelloList2 = new TrelloListDto("2", "Second list", true);
        TrelloListDto trelloList3 = new TrelloListDto("3", "Third list", true);
        TrelloListDto trelloList4 = new TrelloListDto("4", "Fourth list", true);

        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists2.add(trelloList3);
        trelloLists2.add(trelloList4);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "First board", trelloLists);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "Second board", trelloLists2);
        trelloBoardDtoList.add(trelloBoardDto1);
        trelloBoardDtoList.add(trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloBoardDtoList);

        //Then
        assertEquals(trelloBoardDtoList.get(0).getName(), trelloBoards.get(0).getName());
        assertEquals(trelloBoardDtoList.get(1).getName(), trelloBoards.get(1).getName());
    }

}
