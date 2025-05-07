package com.example.pagoclinica.pagoclinica.infraestructura.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagos_clinica")
@Data
public class PagoClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long citaId;

    @Column(nullable = false)
    private Long pacienteId;

    @Column(nullable = false)
    private LocalDate fechaPago;

    @Column(nullable = false)
    private String metodoPago;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false)
    private String estadoPago;

    // Puedes agregar más campos si es necesario
}