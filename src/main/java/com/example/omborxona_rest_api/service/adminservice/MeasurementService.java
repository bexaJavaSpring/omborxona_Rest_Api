package com.example.omborxona_rest_api.service.adminservice;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.MeausurementDto;
import com.example.omborxona_rest_api.entity.Measurement;
import com.example.omborxona_rest_api.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;
    public ApiResponse all() {
        List<Measurement> all = measurementRepository.findAll();
        return new ApiResponse("Mana",true,all);
    }
    public ApiResponse add(MeausurementDto meausurementDto) {
        if (measurementRepository.existsByNameIgnoreCase(meausurementDto.getName())) {
            return new ApiResponse("bunday measurement bor", false);
        }
        Measurement measurement=new Measurement();
        measurement.setName(meausurementDto.getName());
        measurement.setActive(meausurementDto.isActive());
        Measurement save=measurementRepository.save(measurement);
        return new ApiResponse("Added",true,save);
    }

    public ApiResponse edit(Integer id, MeausurementDto dto) {
        if (measurementRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("Bunday nom bor", false);
        }
        Optional<Measurement> byId = measurementRepository.findById(id);
        Measurement measurement=byId.get();
        measurement.setName(dto.getName());
        measurement.setActive(dto.isActive());
        Measurement save=measurementRepository.save(measurement);
        return new ApiResponse("Edit",true,save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Not found",false);
        }
        Measurement save = byId.get();
        measurementRepository.delete(save);
        return new ApiResponse("Delete",true,save);
    }
}
