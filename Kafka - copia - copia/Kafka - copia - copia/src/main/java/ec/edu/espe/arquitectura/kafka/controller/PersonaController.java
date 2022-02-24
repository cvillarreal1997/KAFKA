/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.kafka.controller;

import ec.edu.espe.arquitectura.kafka.model.Persona;
import ec.edu.espe.arquitectura.kafka.service.PersonaService;
//import ec.edu.espe.arquitectura.kafka.service.MensajeService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Windows Boo
 */
@RestController
@RequestMapping("api/v1/")
public class PersonaController {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PersonaService personaService;

    public PersonaController(KafkaTemplate<String, Object> kafkaTemplate, PersonaService personaService) {
        this.kafkaTemplate = kafkaTemplate;
        this.personaService = personaService;
    }

    @PostMapping
    public void publish(@RequestBody Persona p) {
        kafkaTemplate.send("personas", p);
    }

    @PostMapping("persona")
    public void enviarMensaje(@RequestBody Persona p) {
        this.personaService.guardarPersona(p);
    }
}
