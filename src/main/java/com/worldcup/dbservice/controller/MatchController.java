package com.worldcup.dbservice.controller;

import com.worldcup.dbservice.entity.Match;
import com.worldcup.dbservice.service.MatchService;
import com.worldcup.dbservice.service.dto.CreateMatchDto;
import com.worldcup.dbservice.service.dto.MatchDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/match")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @PostMapping("/create")
    public ResponseEntity<MatchDto> createMatch(@RequestBody CreateMatchDto dto) {
        MatchDto matchDto = matchService.createMatch(dto);
        return ResponseEntity.ok(matchDto);
    }

    @GetMapping("/getAllMatches")
    public ResponseEntity<List<MatchDto>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<MatchDto> update(@PathVariable UUID id, @RequestBody CreateMatchDto dto) {
        return ResponseEntity.ok(matchService.update(id, dto));
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<MatchDto> getMatchById(@PathVariable UUID id) {
        MatchDto matchDto = matchService.getMatchById(id);
        return ResponseEntity.ok(matchDto);
    }
}
