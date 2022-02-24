/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.kafka.service;

import ec.edu.espe.arquitectura.kafka.model.Persona;
import org.springframework.stereotype.Service;
import ec.edu.espe.arquitectura.kafka.dao.PersonaRepository;

/**
 *
 * @author Windows Boo
 */
@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public void guardarPersona(Persona p) {
        this.personaRepository.save(p);
    }
}
