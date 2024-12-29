package com.abcode.springrefresh.service;

import com.abcode.springrefresh.dto.BeeDto;

import java.util.List;

public interface BeeService {
    BeeDto createBee(BeeDto beeDto);
    BeeDto getBeeById(Long id);
    List<BeeDto> getAllBees();
    BeeDto updateBee(Long id, BeeDto beeDto);
    void deleteBee(Long id);
}