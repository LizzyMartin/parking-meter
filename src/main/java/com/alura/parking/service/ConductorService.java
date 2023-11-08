package com.alura.parking.service;

import com.alura.parking.dto.ConductorDTO;
import com.alura.parking.entity.Conductor;
import com.alura.parking.exceptions.ConductorNotFoundException;
import com.alura.parking.mapper.ConductorMapper;
import com.alura.parking.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class ConductorService {

    private final ConductorRepository repository;
    private final ConductorMapper mapper;

    @Autowired
    public ConductorService(ConductorRepository repository, ConductorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Conductor> getAllConductor() {
        return this.repository.findAll();
    }

    public Conductor getById(Long id) throws ConductorNotFoundException {
        return this.repository.findById(id).orElseThrow(ConductorNotFoundException::new);
    }

    public Conductor getByCpf(String cpf) throws ConductorNotFoundException {
        return this.repository.findByCpf(cpf).orElseThrow(ConductorNotFoundException::new);
    }

    public void removeConductor(String cpf) throws ConductorNotFoundException {
        var conductor = this.repository.findByCpf(cpf).orElseThrow(ConductorNotFoundException::new);
        this.repository.delete(conductor);
    }

    public void updateConductor(ConductorDTO conductorDTO) throws ConductorNotFoundException {
        var conductor = this.repository.findByCpf(conductorDTO.getCpf()).orElseThrow(ConductorNotFoundException::new);
        updateIfNotNullOrBlank(conductorDTO.getName(), conductor::setName);
        updateIfNotNullOrBlank(conductorDTO.getEmail(), conductor::setEmail);
        updateIfNotNullOrBlank(conductorDTO.getAddress(), conductor::setAddress);
        updateIfNotNullOrBlank(conductorDTO.getCpf(), conductor::setCpf);
        if (conductorDTO.getFavoritePaymentType() != null)
            conductor.setFavoritePaymentType(conductorDTO.getFavoritePaymentType());
        this.repository.save(conductor);
    }

    public String addConductor(ConductorDTO conductorDTO) {
        var conductor = this.repository.save(this.mapper.conductorDTOToConductor(conductorDTO));
        return conductor.getId().toString();
    }

    private void updateIfNotNullOrBlank(String value, Consumer<String> setter) {
        if (value != null && !value.isBlank()) {
            setter.accept(value);
        }
    }
}
