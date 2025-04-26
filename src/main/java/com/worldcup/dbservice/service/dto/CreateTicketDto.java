package com.worldcup.dbservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketDto {
    private LocalDateTime reservationDate;
    private UUID userId;
    private UUID matchId;
}
