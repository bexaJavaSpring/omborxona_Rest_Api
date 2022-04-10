package com.example.omborxona_rest_api.service.adminservice;


import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.SuplierDto;
import com.example.omborxona_rest_api.entity.Suplier;
import com.example.omborxona_rest_api.repository.SuplierRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuplierService {
final SuplierRepository suplierRepository;

    public SuplierService(SuplierRepository suplierRepository) {
        this.suplierRepository = suplierRepository;
    }

    public ApiResponse add(SuplierDto dto) {
        if (suplierRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("Xatolik", false);
        }
        Suplier suplier=new Suplier();
        suplier.setName(dto.getName());
        suplier.setActive(true);
        Suplier save = suplierRepository.save(suplier);
        return new ApiResponse("Added",true,save);
    }

    public ApiResponse edit(Integer id, SuplierDto dto) {
        if (suplierRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("Bunday nom bor", false);
        }
        Optional<Suplier> byId = suplierRepository.findById(id);
        Suplier suplier = byId.get();
        suplier.setName(dto.getName());
        suplier.setActive(dto.isActive());
        Suplier save = suplierRepository.save(suplier);
        return new ApiResponse("Edit",true,save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Suplier> byId = suplierRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Not found",false,null);
        }
        Suplier suplier = byId.get();
        suplierRepository.delete(suplier);
        return new ApiResponse("Deleted",true,suplier);
    }
}
