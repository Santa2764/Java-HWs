package org.example.log_rest.service;

import org.example.log_rest.model.LogData;
import java.util.List;

public interface LogDataService {
    LogData save(LogData logData);
    int[] saveLogsDataList(List<LogData> logsData);
    void update(LogData logData);
    void delete(LogData logData);
    List<LogData> findAll();
    void deleteAll();

    List<LogData> findAllLogs(Integer limit);
}
