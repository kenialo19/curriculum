package org.crud.service;

import org.crud.dto.EstudianteDto;
import org.crud.entity.Estudiante;
import org.crud.repository.EstudianteRepository;
import org.crud.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public EstudianteDto mapToDto(Estudiante estudiante) {
        EstudianteDto dto = new EstudianteDto();
        dto.setEid(estudiante.getEid());
        dto.setNombre(estudiante.getNombre());
        dto.setEspecialidad(estudiante.getEspecialidad());
        dto.setGrado(estudiante.getGrado());
        return dto;
    }

    private Estudiante mapToEntity(EstudianteDto dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setEid(dto.getEid());
        estudiante.setNombre(dto.getNombre());
        estudiante.setEspecialidad(dto.getEspecialidad());
        estudiante.setGrado(dto.getGrado());
        return estudiante;
    }

    public Estudiante findEstudianteById(Long id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        return estudiante.orElse(null);
    }

    public List<EstudianteDto> listarEstudiantes() {
        return estudianteRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public EstudianteDto obtenerEstudiantePorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.NOT_FOUND));
        return mapToDto(estudiante);
    }

    public EstudianteDto crearEstudiante(EstudianteDto estudianteDto) {
        Estudiante estudiante = mapToEntity(estudianteDto);
        Estudiante nuevoEstudiante = estudianteRepository.save(estudiante);
        return mapToDto(nuevoEstudiante);
    }

    public EstudianteDto actualizarEstudiante(Long id, EstudianteDto estudianteDto) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.NOT_FOUND));
        estudiante.setNombre(estudianteDto.getNombre());
        estudiante.setEspecialidad(estudianteDto.getEspecialidad());
        estudiante.setGrado(estudianteDto.getGrado());
        Estudiante estudianteActualizado = estudianteRepository.save(estudiante);
        return mapToDto(estudianteActualizado);
    }

    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }
}
