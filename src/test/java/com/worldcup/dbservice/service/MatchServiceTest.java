package com.worldcup.dbservice.service;

import com.worldcup.dbservice.entity.Match;
import com.worldcup.dbservice.repository.MatchRepository;
import com.worldcup.dbservice.service.MatchService;
import com.worldcup.dbservice.service.dto.CreateMatchDto;
import com.worldcup.dbservice.service.dto.MatchDto;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    @Test
    void MatchService_CreateMatch_callsSaveOnRepository() {
        CreateMatchDto matchDto = new CreateMatchDto(
                "Ghencea",
                "Steaua",
                "Dinamo",
                "Ref A",
                LocalDateTime.now(),
                50,
                30.0
        );

        matchService.createMatch(matchDto);

        ArgumentCaptor<Match> matchCaptor = ArgumentCaptor.forClass(Match.class);
        verify(matchRepository).save(matchCaptor.capture());

        Match savedMatch = matchCaptor.getValue();
        assertEquals("Ghencea", savedMatch.getStadium());
        assertEquals("Steaua", savedMatch.getHomeTeam());
    }


    @Test
    void MatchService_UpdateMatch_UpdatesAndReturnsMatchDto() {
        UUID id = UUID.randomUUID();
        CreateMatchDto dto = new CreateMatchDto(
                "Arena Nationala", "Team A", "Team B", "Ref A",
                LocalDateTime.of(2025, 6, 1, 20, 0), 100, 60.0
        );

        Match existingMatch = Match.builder()
                .id(id)
                .stadium("Old")
                .homeTeam("Old A")
                .awayTeam("Old B")
                .referee("Old Ref")
                .match_date(LocalDateTime.of(2025, 5, 1, 20, 0))
                .nrSeats(50)
                .seatPrice(30.0)
                .build();

        Match updatedMatch = Match.builder()
                .id(id)
                .stadium(dto.getStadium())
                .homeTeam(dto.getHomeTeam())
                .awayTeam(dto.getAwayTeam())
                .referee(dto.getReferee())
                .match_date(dto.getDateTime())
                .nrSeats(dto.getNrSeats())
                .seatPrice(dto.getSeatPrice())
                .build();

        when(matchRepository.findById(id)).thenReturn(Optional.of(existingMatch));
        when(matchRepository.save(any(Match.class))).thenReturn(updatedMatch);

        MatchDto result = matchService.update(id, dto);

        assertEquals("Arena Nationala", result.getStadium());
        assertEquals("Team B", result.getAwayTeam());
        assertEquals(100, result.getNrSeats());
    }

    @Test
    void getAllMatches_returnsListOfMatchDtos() {
        List<Match> matches = List.of(
                Match.builder()
                        .id(UUID.randomUUID())
                        .stadium("A")
                        .homeTeam("X")
                        .awayTeam("Y")
                        .referee("R1")
                        .match_date(LocalDateTime.of(2025, 6, 10, 19, 0))
                        .nrSeats(80)
                        .seatPrice(35.0)
                        .build(),
                Match.builder()
                        .id(UUID.randomUUID())
                        .stadium("B")
                        .homeTeam("Z")
                        .awayTeam("W")
                        .referee("R2")
                        .match_date(LocalDateTime.of(2025, 6, 12, 21, 0))
                        .nrSeats(120)
                        .seatPrice(50.0)
                        .build()
        );

        when(matchRepository.findAll()).thenReturn(matches);

        List<MatchDto> result = matchService.getAllMatches();

        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getStadium());
        assertEquals("W", result.get(1).getAwayTeam());
    }

    @Test
    void getMatchById_returnsCorrectMatchDto() {
        UUID id = UUID.randomUUID();
        Match match = Match.builder()
                .id(id)
                .stadium("Ghencea")
                .homeTeam("Steaua")
                .awayTeam("Dinamo")
                .referee("Ref A")
                .match_date(LocalDateTime.of(2025, 6, 5, 18, 0))
                .nrSeats(100)
                .seatPrice(40.0)
                .build();

        when(matchRepository.findById(id)).thenReturn(Optional.of(match));

        MatchDto result = matchService.getMatchById(id);

        assertEquals("Ghencea", result.getStadium());
        assertEquals("Steaua", result.getHomeTeam());
        assertEquals(40.0, result.getSeatPrice());
    }


}
