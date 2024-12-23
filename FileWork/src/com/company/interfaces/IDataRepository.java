package com.company.interfaces;

import java.util.List;

public interface IDataRepository<T> {
    boolean save(List<T> obj);
    List<T> getAll();
}
