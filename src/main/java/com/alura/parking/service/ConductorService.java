package com.alura.parking.service;

import com.alura.parking.dto.ConductorDTO;
import com.alura.parking.mapper.ConductorMapper;
import com.alura.parking.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConductorService {

    private final ConductorRepository repository;
    private final ConductorMapper mapper;

    @Autowired
    public ConductorService(ConductorRepository repository, ConductorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void addConductor(ConductorDTO conductorDTO) {
        this.repository.save(this.mapper.conductorDTOToConductor(conductorDTO));
    }
}
