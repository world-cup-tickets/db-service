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
public class MatchDto {
    private UUID id;

    @JsonProperty("stadium")
    private String stadium;

    @JsonProperty("home_team")
    private String homeTeam;

    @JsonProperty("away_team")
    private String awayTeam;

    @JsonProperty("referee")
    private String referee;

    @JsonProperty("date_time")
    private LocalDateTime dateTime;

    @JsonProperty("nr_seats")
    private Integer nrSeats;

    @JsonProperty("seat_price")
    private Double seatPrice;
}
