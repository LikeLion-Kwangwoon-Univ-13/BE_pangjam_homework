package com.example.pangjam.util;

public interface Mapper<T, R> {
    T convertToDto(R entity);
}
