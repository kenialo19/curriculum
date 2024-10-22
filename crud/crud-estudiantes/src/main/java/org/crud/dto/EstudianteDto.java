package org.crud.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstudianteDto {
    private Long eid;
    private String nombre;
    private String especialidad;
    private String grado;
}
