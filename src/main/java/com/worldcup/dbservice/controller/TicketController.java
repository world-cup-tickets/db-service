package com.worldcup.dbservice.controller;

import com.worldcup.dbservice.service.TicketService;
import com.worldcup.dbservice.service.dto.CreateTicketDto;
import com.worldcup.dbservice.service.dto.TicketDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/create")
    public ResponseEntity<TicketDto> createTicket(@RequestBody CreateTicketDto createTicketDto) {
        TicketDto ticketDto = ticketService.createTicket(createTicketDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketDto>> getTicketsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(ticketService.getTicketsByUser(userId));
    }

    @DeleteMapping("/{ticketId}/delete")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{ticketId}/get")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable UUID ticketId) {
        TicketDto ticketDto = ticketService.getTicketById(ticketId);
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping("/getAllTickets")
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}
