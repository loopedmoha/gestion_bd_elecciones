package com.mggcode.gestion_bd_elecciones.service.autonomicas;


import com.mggcode.gestion_bd_elecciones.logic.autonomicas.CircunscripcionPartidoOficial;
import com.mggcode.gestion_bd_elecciones.logic.autonomicas.CircunscripcionPartidoSondeo;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Key;
import com.mggcode.gestion_bd_elecciones.repository.autonomicas.ACircunscripcionPartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ACircunscripcionPartidoService {
    @Autowired
    private ACircunscripcionPartidoRepository circunscripcionPartidoRepository;


    public CircunscripcionPartido create(CircunscripcionPartido circunscripcionPartido) {
        return circunscripcionPartidoRepository.save(circunscripcionPartido);
    }

    public CircunscripcionPartido update(CircunscripcionPartido circunscripcionPartido) {
        return circunscripcionPartidoRepository.save(circunscripcionPartido);
    }

    public CircunscripcionPartido findById(Key id) {
        Optional<CircunscripcionPartido> CircunscripcionPartidoOptional = circunscripcionPartidoRepository.findById(id);
        return CircunscripcionPartidoOptional.orElse(null);
    }

    public List<CircunscripcionPartido> findByIdCircunscripcionOficial(String codCircunscripcion) {
        List<CircunscripcionPartido> lista = circunscripcionPartidoRepository.findByKey_Circunscripcion(codCircunscripcion)
                .stream().filter(x -> x.getEscanos_hasta() > 0)
                .sorted(new CircunscripcionPartidoOficial().reversed()).collect(Collectors.toList());
        return lista;
    }

    public List<CircunscripcionPartido> findByIdCircunscripcionSondeo(String codCircunscripcion) {
        List<CircunscripcionPartido> lista = circunscripcionPartidoRepository.findByKey_Circunscripcion(codCircunscripcion)
                .stream().filter(x -> x.getEscanos_hasta() > 0)
                .sorted(new CircunscripcionPartidoSondeo().reversed()).collect(Collectors.toList());
        return lista;
    }

    public List<CircunscripcionPartido> findByIdPartido(String codPartido) {
        List<CircunscripcionPartido> lista = circunscripcionPartidoRepository.findByKey_Partido(codPartido);
        return lista;
    }


    public List<CircunscripcionPartido> findAll() {
        return circunscripcionPartidoRepository.findAll();
    }


    public void delete(Key id) {
        circunscripcionPartidoRepository.deleteById(id);
    }

}


