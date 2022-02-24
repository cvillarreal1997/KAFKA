/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.kafka.dao;

import ec.edu.espe.arquitectura.kafka.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Windows Boo
 */
public interface PersonaRepository extends JpaRepository<Persona, Integer>{
    
}
