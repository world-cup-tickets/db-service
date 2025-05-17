package com.worldcup.dbservice.service;

import com.worldcup.dbservice.entity.Match;
import com.worldcup.dbservice.entity.Ticket;
import com.worldcup.dbservice.repository.MatchRepository;
import com.worldcup.dbservice.repository.TicketRepository;
import com.worldcup.dbservice.service.dto.CreateTicketDto;
import com.worldcup.dbservice.service.dto.TicketDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void TicketService_CreateTicket_SavesAndReturnsTicketDto() {
        UUID matchId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Match match = Match.builder().id(matchId).build();

        CreateTicketDto dto = new CreateTicketDto(LocalDateTime.now(), userId, matchId);

        Ticket savedTicket = Ticket.builder()
                .id(UUID.randomUUID())
                .reservationDate(dto.getReservationDate())
                .userId(userId)
                .match(match)
                .build();

        when(matchRepository.findById(matchId)).thenReturn(Optional.of(match));
        when(ticketRepository.save(any())).thenReturn(savedTicket);

        TicketDto result = ticketService.createTicket(dto);

        Assertions.assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(matchId, result.getMatchId());
    }

    @Test
    void TicketService_GetTicketsByUser_ReturnsTicketDtos() {
        UUID userId = UUID.randomUUID();
        UUID matchId = UUID.randomUUID();
        Match match = Match.builder().id(matchId).build();

        List<Ticket> tickets = List.of(
                Ticket.builder()
                        .id(UUID.randomUUID())
                        .reservationDate(LocalDateTime.now())
                        .userId(userId)
                        .match(match)
                        .build()
        );

        when(ticketRepository.findByUserId(userId)).thenReturn(tickets);

        List<TicketDto> result = ticketService.getTicketsByUser(userId);

        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getUserId());
        assertEquals(matchId, result.get(0).getMatchId());
    }

    @Test
    void TicketService_DeleteTicket_WhenTicketExists_DeletesSuccessfully() {
        UUID ticketId = UUID.randomUUID();
        when(ticketRepository.existsById(ticketId)).thenReturn(true);

        ticketService.deleteTicket(ticketId);

        verify(ticketRepository).deleteById(ticketId);
    }

    @Test
    void TicketService_DeleteTicket_WhenTicketNotFound_ThrowsException() {
        UUID ticketId = UUID.randomUUID();
        when(ticketRepository.existsById(ticketId)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> ticketService.deleteTicket(ticketId));
    }

    @Test
    void TicketService_GetTicketById_ReturnsTicketDto() {
        UUID ticketId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID matchId = UUID.randomUUID();
        Match match = Match.builder().id(matchId).build();

        Ticket ticket = Ticket.builder()
                .id(ticketId)
                .reservationDate(LocalDateTime.now())
                .userId(userId)
                .match(match)
                .build();

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        TicketDto result = ticketService.getTicketById(ticketId);

        assertEquals(ticketId, result.getId());
        assertEquals(userId, result.getUserId());
        assertEquals(matchId, result.getMatchId());
    }

    @Test
    void TicketService_GetAllTickets_ReturnsAllTicketDtos() {
        UUID userId = UUID.randomUUID();
        UUID matchId = UUID.randomUUID();
        Match match = Match.builder().id(matchId).build();

        List<Ticket> tickets = List.of(
                Ticket.builder()
                        .id(UUID.randomUUID())
                        .reservationDate(LocalDateTime.now())
                        .userId(userId)
                        .match(match)
                        .build()
        );

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<TicketDto> result = ticketService.getAllTickets();

        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getUserId());
    }

}

