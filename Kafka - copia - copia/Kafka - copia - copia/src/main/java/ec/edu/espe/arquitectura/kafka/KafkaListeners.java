/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.kafka;

import ec.edu.espe.arquitectura.kafka.controller.PersonaController;
import ec.edu.espe.arquitectura.kafka.model.Persona;
//import ec.edu.espe.arquitectura.kafka.service.MensajeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Windows Boo
 */
@Slf4j
@Component
public class KafkaListeners {

    private final PersonaController mensajeController;
    private RestTemplate restTemplate = new RestTemplate();

    public KafkaListeners(PersonaController mensajeController) {
        this.mensajeController = mensajeController;
    }

    @KafkaListener(
            topics = "personas",
            groupId = "groupId"
    )
    public void Listener(Persona p) {
        log.info("Data recibida sin procesar nada: {}", p);
        this.restTemplate.postForObject("http://localhost:8080/api/v1/persona", p, Persona.class);
        //this.restTemplate.exchange("http://localhost:8090/api/v1/", HttpMethod.POST, data, String.class);
        //log.info("Esta data procesada: {}", jsonString);
//        System.out.println("Data recibida" + data);
        //this.mensajeController.enviarMensaje(data);
        //this.mensajeService.guardarMensaje(data);
//        System.out.println("Listner received: " + data);
    }
}
