package com.mggcode.gestion_bd_elecciones.EscuchadorBD;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EscuchadorBD {


    @KafkaListener(topics = "Elecciones.elecciones_municipales_2019.circunscripciones", groupId = "${muni.kafka.consumer.group-id}")
    public void listenMunicipales() {
        System.out.println("Parece que hay un cambio en Municipales");
        // manejar el cambio de datos
    }

    @KafkaListener(topics = "Elecciones.elecciones_autonomicas_2019.circunscripciones", groupId = "${auton.kafka.consumer.group-id}")
    public void listenAutonomicas() {
        System.out.println("Parece que hay un cambio en Autonómicas");
        // manejar el cambio de datos
    }
}