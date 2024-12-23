package org.example.log_rest.controller;

import org.example.log_rest.convert.ConvertDtoToEntity;
import org.example.log_rest.convert.ConvertEntityToDto;
import org.example.log_rest.dto.LogDataDto;
import org.example.log_rest.model.LogData;
import org.example.log_rest.service.LogDataService;
import org.example.log_rest.utils.EndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = EndPoints.VERSION_1)
public class LogDataController {

    @Autowired
    private LogDataService logDataService;

    @Autowired
    private ConvertDtoToEntity convertDtoToEntity;

    @Autowired
    private ConvertEntityToDto convertEntityToDto;


    @GetMapping(EndPoints.GET_LOGS)
    public ResponseEntity<List<LogDataDto>> getLogs(@RequestParam(required = false) Integer limit) {
        List<LogData> logDataList = (limit != null)
            ? logDataService.findAllLogs(limit)
            : logDataService.findAll();

        List<LogDataDto> logDataDtoList = logDataList.stream()
                .map(convertEntityToDto::convertLogDataToDto)
                .toList();

        return ResponseEntity.ok(logDataDtoList);
    }

    @PostMapping(EndPoints.GET_LOGS)
    public ResponseEntity<Map<String, Integer>> createLog(@RequestBody LogDataDto logDataDto) {
        try {
            LogData logData = convertDtoToEntity.converDtoToLogData(logDataDto);
            LogData savedLog = logDataService.save(logData);

            Map<String, Integer> response = new HashMap<>();
            response.put("id", savedLog.getId());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("Invalid log level: " + logDataDto.getLevel(), -1));
        }
    }
}
