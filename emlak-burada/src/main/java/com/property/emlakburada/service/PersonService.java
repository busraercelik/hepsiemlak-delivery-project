package com.property.emlakburada.service;

import com.bsr.emlak.commons.dto.request.PersonRequestDTO;
import com.bsr.emlak.commons.dto.response.PersonResponseDTO;
import com.bsr.emlak.commons.entity.Person;
import com.property.emlakburada.repository.PersonRepository;
import com.property.emlakburada.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonResponseDTO> getAllUsers() {
        return personRepository.findAll()
                .stream()
                .map(Response::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public PersonResponseDTO getPersonById(long id) {
        Person person = personRepository.
                findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("no person with given id %s found", id)));
        return Response.convertToResponseDTO(person);
    }

    public void savePerson(PersonRequestDTO personRequestDTO) {
        personRepository.save(Response.convertToPerson(personRequestDTO));
    }

}
