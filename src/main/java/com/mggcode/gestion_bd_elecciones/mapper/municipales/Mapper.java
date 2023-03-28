package com.mggcode.gestion_bd_elecciones.mapper.municipales;

import com.mggcode.gestion_bd_elecciones.DTO.municipales.CarmenDTO;
import com.mggcode.gestion_bd_elecciones.DTO.municipales.CpDTO;
import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.municipales.Partido;


import java.util.ArrayList;
import java.util.List;


public class Mapper {

    public CarmenDTO toDTO(Circunscripcion c, List<CircunscripcionPartido> cp, List<Partido> p) {
        List<CpDTO> cpDTO = new ArrayList<>();
        cp.forEach(x -> {
            Partido pTemp = p.stream().filter(y -> y.getCodigo().equals(x.getKey().getPartido())).toList().get(0);
            CpDTO cpTemp = CpDTO.builder()
                    .escanos_desde(x.getEscanos_desde())
                    .escanos_hasta(x.getEscanos_hasta())
                    .escanos_hasta_hist(x.getEscanos_hasta_hist())
                    .numVotantes(x.getNumVotantes())
                    .porcentajeVoto(x.getPorcentajeVoto())
                    .porcentajeVotoHistorico(x.getVotantesHistorico())
                    .siglas(pTemp.getSiglas())
                    .literalPartido(pTemp.getNombreCompleto())
                    .codigoPartido(pTemp.getCodigo())
                    .codigoPadre(pTemp.getCodigoPadre())
                    .build();
            cpDTO.add(cpTemp);
        });
        return CarmenDTO.builder()
                .circunscripcion(c)
                .numPartidos(cp.size())
                .cpDTO(cpDTO)
                .build();
    }
}
