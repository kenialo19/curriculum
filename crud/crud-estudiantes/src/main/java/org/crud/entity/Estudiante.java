package org.crud.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "PRUEBAESTUDIANTE")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESPECIALIDAD")
    private String especialidad;
    @Column(name = "GRADO")
    private String grado;
}
