package com.example.omborxona_rest_api.service.adminservice;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.CurrencyDto;
import com.example.omborxona_rest_api.entity.Currency;
import com.example.omborxona_rest_api.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public ApiResponse all() {
        List<Currency> all = currencyRepository.findAll();
        return new ApiResponse("mana", true, all);
    }

    public ApiResponse add(CurrencyDto currencyDto) {
        if (currencyRepository.existsByNameIgnoreCase(currencyDto.getName())) {
            return new ApiResponse("Xatolik", false);
        }
        Currency currency = new Currency();
        currency.setName(currencyDto.getName());
        currency.setActive(true);
        Currency save = currencyRepository.save(currency);
        return new ApiResponse("Added", true, save);
    }

    public ApiResponse edit(Integer id, CurrencyDto dto) {
        if (currencyRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("Bunday nom bor", false);
        }
        Optional<Currency> byId = currencyRepository.findById(id);
        Currency currency=byId.get();
        currency.setName(dto.getName());
        currency.setActive(dto.isActive());
        Currency save=currencyRepository.save(currency);
        return new ApiResponse("Edit",true,save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Currency> byId = currencyRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Not found",false);
        }
        Currency save = byId.get();
        currencyRepository.delete(save);
        return new ApiResponse("Delete",true,save);
    }
}
