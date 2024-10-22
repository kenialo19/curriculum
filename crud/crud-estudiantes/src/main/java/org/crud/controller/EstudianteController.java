package org.crud.controller;

import org.crud.dto.EstudianteDto;
import org.crud.entity.Estudiante;
import org.crud.service.EstudianteService;
import org.crud.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDto>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDto> obtenerEstudiantePorId(@PathVariable Long id) {
        Estudiante estudiante = estudianteService.findEstudianteById(id);

        if (estudiante != null) {
            EstudianteDto estudianteDto = estudianteService.mapToDto(estudiante);
            return new ResponseEntity<>(estudianteDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EstudianteDto> crearEstudiante(@RequestBody EstudianteDto estudianteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.crearEstudiante(estudianteDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDto> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDto estudianteDto) {
        return ResponseEntity.ok(estudianteService.actualizarEstudiante(id, estudianteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.ok(Constant.ESTUDIANTE_ELIMINADO_MENSAJE);
    }
}
