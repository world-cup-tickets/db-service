package com.worldcup.dbservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {
    @Id
    @GeneratedValue()
    @Column(name = "id")
    private UUID id;

    @Column(name = "match_date")
    private LocalDateTime match_date;

    @Column(name = "stadium")
    private String stadium;

    @Column(name = "home_team")
    private String homeTeam;

    @Column(name = "away_team")
    private String awayTeam;

    private String referee;

    @OneToMany(mappedBy = "match")
    private List<Ticket> tickets;

    @Column(name = "nr_seats", nullable = false)
    private Integer nrSeats;

    @Column(name = "seat_price", nullable = false)
    private Double seatPrice;
}
