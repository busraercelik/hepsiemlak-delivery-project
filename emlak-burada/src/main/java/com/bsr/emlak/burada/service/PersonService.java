package com.bsr.emlak.burada.service;

import com.bsr.emlak.commons.dto.request.PersonRequestDTO;
import com.bsr.emlak.commons.dto.response.PersonResponseDTO;
import com.bsr.emlak.commons.entity.Person;
import com.bsr.emlak.commons.repository.PersonRepository;
import com.bsr.emlak.commons.util.CommonTransformer;
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
                .map(CommonTransformer::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public PersonResponseDTO getPersonById(long id) {
        Person person = personRepository.
                findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("no person with given id %s found", id)));
        return CommonTransformer.convertToResponseDTO(person);
    }

    public void savePerson(PersonRequestDTO personRequestDTO) {
        personRepository.save(CommonTransformer.convertToPerson(personRequestDTO));
    }

}
