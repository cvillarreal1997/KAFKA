package ec.edu.espe.arquitectura.kafka;

import com.github.javafaker.Faker;
import ec.edu.espe.arquitectura.kafka.model.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootApplication
@Slf4j
public class KafkaApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
    
    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate) {
        return args -> {
            Faker faker = new Faker();
            Persona persona = new Persona();
            for (int i = 0; i < 10000; i++) {
                persona.setNombre(faker.name().firstName());
                persona.setApellido(faker.name().lastName());
                persona.setTelefono(faker.phoneNumber().phoneNumber());
                persona.setCorreo(faker.internet().emailAddress());
                persona.setDireccion(faker.address().fullAddress());
                persona.setFechaNacimiento(faker.date().birthday(18, 80));
                log.info("Persona: {}", persona);
                ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send("personas", persona);
                send.addCallback(new KafkaSendCallback<String, Object>() {
                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        log.info("Mensaje enviado: {}",result.getRecordMetadata());
                    }
                    
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Error al enviar el mensaje {}",ex);
                    }
                    
                    @Override
                    public void onFailure(KafkaProducerException ex) {
                         log.error("Error al enviar el mensaje {}",ex); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                });
            }
        };
    }
}
