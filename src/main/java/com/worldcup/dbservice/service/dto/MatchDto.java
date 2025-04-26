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
public class MatchDto {
    private UUID id;
    private String stadium;
    private String homeTeam;
    private String awayTeam;
    private String referee;
    private LocalDateTime dateTime;
    private Integer nrSeats;
    private Double seatPrice;
}