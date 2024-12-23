package org.example.log_rest.service;

import org.example.log_rest.dao.LogDataRepository;
import org.example.log_rest.model.LogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogDataServiceImpl implements LogDataService {

    @Autowired
    private LogDataRepository logDataRepository;


    @Override
    public LogData save(LogData logData) {
        return logDataRepository.save(logData);
    }

    @Override
    public int[] saveLogsDataList(List<LogData> logsData) {
        logDataRepository.saveAll(logsData);
        return new int[0];
    }

    @Override
    public void update(LogData logData) {
        logDataRepository.save(logData);
    }

    @Override
    public void delete(LogData logData) {
        logDataRepository.delete(logData);
    }

    @Override
    public List<LogData> findAll() {
        return (List<LogData>) logDataRepository.findAll();
    }

    @Override
    public void deleteAll() {
        logDataRepository.deleteAll();
    }


    @Override
    public List<LogData> findAllLogs(Integer limit) {
        Pageable pageable = PageRequest.of(1, (limit != null) ? limit : Integer.MAX_VALUE);
        return logDataRepository.findAll(pageable).getContent();
    }
}
