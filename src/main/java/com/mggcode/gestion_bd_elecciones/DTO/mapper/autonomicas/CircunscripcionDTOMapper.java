package com.mggcode.gestion_bd_elecciones.DTO.mapper.autonomicas;


import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CircunscripcionDTO;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;


public class CircunscripcionDTOMapper {

    public CircunscripcionDTO toDTO(Circunscripcion c, Circunscripcion espania, String anioUltimas) {
        double participacion = 0.0;
        double participacionHistorica = 0.0;
        double participacionMedia = 0.0;
        String literal;

        if (c.getParticipacion() != 0) {
            participacion = c.getParticipacion();
            participacionHistorica = c.getParticipacionHist();
            participacionMedia = espania.getParticipacion();
            literal = "4";
        } else if (c.getAvance3() != 0) {
            participacion = c.getAvance3();
            participacionHistorica = c.getAvance3Hist();
            participacionMedia = espania.getAvance3();
            literal = "3";
        } else if (c.getAvance2() != 0) {
            participacion = c.getAvance2();
            participacionHistorica = c.getAvance2Hist();
            participacionMedia = espania.getAvance2();
            literal = "2";
        } else {
            participacion = c.getAvance1();
            participacionHistorica = c.getAvance1Hist();
            participacionMedia = espania.getAvance1();
            literal = "1";
        }
        return CircunscripcionDTO.builder()
                .codigo(c.getCodigo())
                .codigoComunidad(c.getCodigoComunidad())
                .codigoProvincia(c.getCodigoProvincia())
                .codigoMunicipio(c.getCodigoMunicipio())
                .nombreCircunscripcion(c.getNombreCircunscripcion())
                .escrutado(c.getEscrutado())
                .escanios(c.getEscanios())
                .participacion(participacion)
                .participacionHistorico(participacionHistorica)
                .avanceActual(literal)
                .participacionMedia(participacionMedia)
                .anioUltimosDatos(anioUltimas)
                .votantes(c.getVotantes())
                .escaniosHistoricos(c.getEscaniosHistoricos())
                .mayoria(getMayoria(c.getEscanios()))
                .autonomiaOMunicipio(0)
                .participacionHist(0.0)
                .build();
    }

    private int getMayoria(int escanios) {
        return (escanios / 2) + 1;
    }


}
