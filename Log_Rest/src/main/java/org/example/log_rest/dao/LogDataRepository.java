package org.example.log_rest.dao;

import org.example.log_rest.model.LogData;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LogDataRepository extends PagingAndSortingRepository<LogData, Integer> {

}
