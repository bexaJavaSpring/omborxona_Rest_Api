package com.example.omborxona_rest_api.service.adminservice;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.ClientDto;
import com.example.omborxona_rest_api.entity.Client;
import com.example.omborxona_rest_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
final ClientRepository clientRepository;
    public ApiResponse add(ClientDto dto) {
        if (clientRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("This client already exist", false);
        }
        Client client=new Client();
        client.setName(dto.getName());
        client.setActive(true);
        Client save = clientRepository.save(client);
        return new ApiResponse("Added",true,save);
    }

    public ApiResponse edit(Integer id, ClientDto dto) {
        if (clientRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("Bunday nom bor", false);
        }
        Optional<Client> byId = clientRepository.findById(id);
        if(!byId.isPresent()){
            return new ApiResponse("Not found",false);
        }
        Client client = byId.get();
        client.setActive(dto.isActive());
        client.setName(dto.getName());
        Client save = clientRepository.save(client);
        return new ApiResponse("Edit",true,save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Client> byId = clientRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Not found",false);
        }
        Client client = byId.get();
        clientRepository.delete(client);
        return new ApiResponse("Deleted",true,client);
    }
}
