package com.worldcup.dbservice.service;

import com.worldcup.dbservice.entity.Match;
import com.worldcup.dbservice.repository.MatchRepository;
import com.worldcup.dbservice.service.dto.CreateMatchDto;
import com.worldcup.dbservice.service.dto.MatchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    public MatchDto createMatch(CreateMatchDto dto) {
        Match match = new Match();
        mapToEntity(dto, match);
        Match savedMatch = matchRepository.save(match);

        return mapToDto(savedMatch);
    }

    private void mapToEntity(CreateMatchDto dto, Match match) {
        match.setStadium(dto.getStadium());
        match.setHomeTeam(dto.getHomeTeam());
        match.setAwayTeam(dto.getAwayTeam());
        match.setReferee(dto.getReferee());
        match.setMatch_date(dto.getDateTime());
        match.setNrSeats(dto.getNrSeats());
        match.setSeatPrice(dto.getSeatPrice());
    }

    private MatchDto mapToDto(Match match) {
        return MatchDto.builder()
                .id(match.getId())
                .stadium(match.getStadium())
                .homeTeam(match.getHomeTeam())
                .awayTeam(match.getAwayTeam())
                .referee(match.getReferee())
                .dateTime(match.getMatch_date())
                .nrSeats(match.getNrSeats())
                .seatPrice(match.getSeatPrice())
                .build();
    }


    public MatchDto update(UUID id, CreateMatchDto dto) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with!"));

        mapToEntity(dto, match);

        Match updated = matchRepository.save(match);

        return new MatchDto(
                updated.getId(),
                updated.getStadium(),
                updated.getHomeTeam(),
                updated.getAwayTeam(),
                updated.getReferee(),
                updated.getMatch_date(),
                updated.getNrSeats(),
                updated.getSeatPrice()
        );
    }

    public MatchDto getMatchById(UUID id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found!"));

        return new MatchDto(
                match.getId(),
                match.getStadium(),
                match.getHomeTeam(),
                match.getAwayTeam(),
                match.getReferee(),
                match.getMatch_date(),
                match.getNrSeats(),
                match.getSeatPrice()
        );
    }


    public List<MatchDto> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        List<MatchDto> response = new ArrayList<>();

        for (Match match : matches) {
            response.add(new MatchDto(
                    match.getId(),
                    match.getStadium(),
                    match.getHomeTeam(),
                    match.getAwayTeam(),
                    match.getReferee(),
                    match.getMatch_date(),
                    match.getNrSeats(),
                    match.getSeatPrice()
            ));
        }
        return response;
    }
}
