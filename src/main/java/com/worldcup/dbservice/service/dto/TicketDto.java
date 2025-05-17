package com.worldcup.dbservice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {
    private UUID id;
    @JsonProperty("reservation_date") private LocalDateTime reservationDate;
    @JsonProperty("user_id") private UUID userId;
    @JsonProperty("match_id") private UUID matchId;
}
