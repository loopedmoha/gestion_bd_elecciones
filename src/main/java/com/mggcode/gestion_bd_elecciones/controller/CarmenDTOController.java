package com.mggcode.gestion_bd_elecciones.controller;


import com.mggcode.gestion_bd_elecciones.DTO.CarmenDTO;
import com.mggcode.gestion_bd_elecciones.mapper.Mapper;
import com.mggcode.gestion_bd_elecciones.model.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.Partido;
import com.mggcode.gestion_bd_elecciones.service.CircunscripcionPartidoService;
import com.mggcode.gestion_bd_elecciones.service.CsvExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carmen")
public class CarmenDTOController {

    @Autowired
    private PartidoController parCon;

    @Autowired
    private CircunscripcionController cirCon;

    @Autowired
    private CircunscripcionPartidoService cpCon;

    @Autowired
    private CsvExportService csvExportService;


    @GetMapping("/{codigo}")
    public ResponseEntity<CarmenDTO> getCarmenDTO(@PathVariable("codigo") String cod1) {

        Circunscripcion circunscripcion = cirCon.findById(cod1).getBody();

        List<CircunscripcionPartido> cp = cpCon.findByIdCircunscripcion(cod1).stream()
                //.filter(x -> x.getKey().getCircunscripcion().startsWith(cod1.substring(0, 2)))
                //.filter(x -> !x.getKey().getCircunscripcion().endsWith("00000"))
                //.filter(x -> x.getKey().getCircunscripcion().endsWith("000"))
                //.filter(x -> !x.getKey().getCircunscripcion().startsWith("99"))
                .filter(x -> x.getEscanos_hasta() > 0.0)
                //.sorted(Comparator.comparing(CircunscripcionPartido::getEscanos_hasta).reversed())
                .collect(Collectors.toList());

        List<Partido> partidos = new ArrayList<>();
        cp.forEach(x -> {
            partidos.add(parCon.findById(x.getKey().getPartido()).getBody());
        });
        Mapper mapper = new Mapper();
        CarmenDTO dto = mapper.toDTO(circunscripcion, cp, partidos);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(path = "/{codigo}/csv")
    public void findByIdCircunscripcionInCsv(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CP_DatosCircunscripcion_" + cod1 + ".csv\"");
        CarmenDTO dto = getCarmenDTO(cod1).getBody();
        csvExportService.writeCarmenDTOToCsv(dto, servletResponse.getWriter());
    }
}