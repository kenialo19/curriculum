package org.crud.service;

import org.crud.dto.EstudianteDto;
import org.crud.entity.Estudiante;
import org.crud.repository.EstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
class EstudianteServiceTest {
    @Mock
    private EstudianteRepository estudianteRepository;
    @InjectMocks
    private EstudianteService estudianteService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarEstudiantes() {
        // Arrange
        Estudiante estudiante1 = new Estudiante(1L, "Juan", "Ciencias", "Grado 1");
        Estudiante estudiante2 = new Estudiante(2L, "Ana", "Matemáticas", "Grado 2");
        when(estudianteRepository.findAll()).thenReturn(Arrays.asList(estudiante1, estudiante2));

        // Act
        List<EstudianteDto> estudiantes = estudianteService.listarEstudiantes();

        // Assert
        assertEquals(2, estudiantes.size());
        assertEquals("Juan", estudiantes.get(0).getNombre());
        assertEquals("Ana", estudiantes.get(1).getNombre());
    }

    @Test
    void testObtenerEstudiantePorId_Existente() {
        // Arrange
        Estudiante estudiante = new Estudiante(1L, "Juan", "Ciencias", "Grado 1");
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));

        // Act
        EstudianteDto estudianteDto = estudianteService.obtenerEstudiantePorId(1L);

        // Assert
        assertNotNull(estudianteDto);
        assertEquals("Juan", estudianteDto.getNombre());
    }

    @Test
    void testObtenerEstudiantePorId_NoExistente() {
        // Arrange
        when(estudianteRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> estudianteService.obtenerEstudiantePorId(1L));
        assertEquals("Estudiante no encontrado", exception.getMessage());
    }

    @Test
    void testCrearEstudiante() {
        // Arrange
        EstudianteDto estudianteDto = new EstudianteDto(1L, "Juan", "Ciencias", "Grado 1");
        Estudiante estudiante = new Estudiante(1L, "Juan", "Ciencias", "Grado 1");
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);

        // Act
        EstudianteDto nuevoEstudianteDto = estudianteService.crearEstudiante(estudianteDto);

        // Assert
        assertNotNull(nuevoEstudianteDto);
        assertEquals("Juan", nuevoEstudianteDto.getNombre());
    }

    @Test
    void testActualizarEstudiante_Existente() {
        // Arrange
        Estudiante estudiante = new Estudiante(1L, "Juan", "Ciencias", "Grado 1");
        EstudianteDto estudianteDto = new EstudianteDto(1L, "Pedro", "Física", "Grado 2");
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);

        // Act
        EstudianteDto estudianteActualizado = estudianteService.actualizarEstudiante(1L, estudianteDto);

        // Assert
        assertNotNull(estudianteActualizado);
        assertEquals("Pedro", estudianteActualizado.getNombre());
        assertEquals("Física", estudianteActualizado.getEspecialidad());
    }

    @Test
    void testActualizarEstudiante_NoExistente() {
        // Arrange
        EstudianteDto estudianteDto = new EstudianteDto(1L, "Pedro", "Física", "Grado 2");
        when(estudianteRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> estudianteService.actualizarEstudiante(1L, estudianteDto));
        assertEquals("Estudiante no encontrado", exception.getMessage());
    }

    @Test
    void testEliminarEstudiante() {
        // Arrange
        Estudiante estudiante = new Estudiante(1L, "Juan", "Ciencias", "Grado 1");
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante)).thenReturn(Optional.empty());

        // Act
        estudianteService.eliminarEstudiante(1L);

        // Assert
        Optional<Estudiante> estudianteEliminado = estudianteRepository.findById(1L);
        assertTrue(estudianteEliminado.isEmpty(), "El estudiante debería haber sido eliminado");
    }
}