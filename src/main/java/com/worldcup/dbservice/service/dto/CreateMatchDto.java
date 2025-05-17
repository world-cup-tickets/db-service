package com.worldcup.dbservice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchDto {
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
