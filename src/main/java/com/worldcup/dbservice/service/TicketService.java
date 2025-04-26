package com.worldcup.dbservice.service;

import com.worldcup.dbservice.entity.Match;
import com.worldcup.dbservice.entity.Ticket;
import com.worldcup.dbservice.repository.MatchRepository;
import com.worldcup.dbservice.repository.TicketRepository;
import com.worldcup.dbservice.service.dto.CreateTicketDto;
import com.worldcup.dbservice.service.dto.TicketDto;
import jakarta.transaction.Transactional;
import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private MatchRepository matchRepository;

    public TicketDto createTicket(CreateTicketDto dto) {
        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(() -> new RuntimeException("Match not found"));

        Ticket ticket = new Ticket();
        ticket.setReservationDate(dto.getReservationDate());
        ticket.setUserId(dto.getUserId());
        ticket.setMatch(match);

        Ticket saved = ticketRepository.save(ticket);

        return new TicketDto(
                saved.getId(),
                saved.getReservationDate(),
                saved.getUserId(),
                saved.getMatch().getId()
        );
    }

    public List<TicketDto> getTicketsByUser(UUID userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        List<TicketDto> response = new ArrayList<>();

        for (Ticket ticket : tickets) {
            response.add(new TicketDto(
                    ticket.getId(),
                    ticket.getReservationDate(),
                    ticket.getUserId(),
                    ticket.getMatch().getId()
            ));
        }
        return response;
    }

    public void deleteTicket(UUID ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new RuntimeException("Ticket not found");
        }
        ticketRepository.deleteById(ticketId);
    }

    public TicketDto getTicketById(UUID ticketId) {
        Ticket ticket = (Ticket) ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        return new TicketDto(
                ticket.getId(),
                ticket.getReservationDate(),
                ticket.getUserId(),
                ticket.getMatch().getId()
        );
    }

    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDto> response = new ArrayList<>();

        for (Ticket ticket : tickets) {
            response.add(new TicketDto(
                    ticket.getId(),
                    ticket.getReservationDate(),
                    ticket.getUserId(),
                    ticket.getMatch().getId()
            ));
        }
        return response;
    }

}
