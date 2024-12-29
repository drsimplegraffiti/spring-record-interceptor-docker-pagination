package com.abcode.springrefresh.service;


import com.abcode.springrefresh.model.Bee;
import com.abcode.springrefresh.dto.BeeDto;
import com.abcode.springrefresh.repository.BeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeeServiceImpl implements BeeService {
    private final BeeRepository beeRepository;

    public BeeServiceImpl(BeeRepository beeRepository) {
        this.beeRepository = beeRepository;
    }

    @Override
    public BeeDto createBee(BeeDto beeDto) {
        Bee bee = new Bee(null, beeDto.name(), beeDto.species());
        Bee savedBee = beeRepository.save(bee);
        return new BeeDto(savedBee.getId(), bee.getName(), bee.getSpecies());
    }

    @Override
    public BeeDto getBeeById(Long id) {
        Bee bee = beeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bee not found with id: " + id));
        return new BeeDto(bee.getId(), bee.getName(), bee.getSpecies());
    }

    @Override
    public List<BeeDto> getAllBees() {
        return beeRepository.findAll().stream()
                .map(bee -> new BeeDto(bee.getId(), bee.getName(), bee.getSpecies()))
                .collect(Collectors.toList());
    }

    @Override
    public BeeDto updateBee(Long id, BeeDto beeDto) {
        Bee existingBee = beeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bee not found with id: " + id));
        Bee updatedBee = new Bee(existingBee.getId(), beeDto.name(), beeDto.species());
        Bee savedBee = beeRepository.save(updatedBee);
        return new BeeDto(savedBee.getId(), savedBee.getName(), savedBee.getSpecies());
    }

    @Override
    public void deleteBee(Long id) {
        Bee bee = beeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bee not found with id: " + id));
        beeRepository.delete(bee);
    }
}