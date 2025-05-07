package com.example.pagoclinica.pagoclinica.domain.repository;



import com.example.pagoclinica.pagoclinica.domain.dto.PayClinicalDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IPayClinical {
    PayClinicalDTO registrarPago(PayClinicalDTO payClinicalDTO);
    PayClinicalDTO obtenerPagoPorId(Long id);
    List<PayClinicalDTO> obtenerTodosLosPagos();
    List<PayClinicalDTO> obtenerPagosPorCitaId(Long citaId);
    List<PayClinicalDTO> obtenerPagosPendientes();
    BigDecimal obtenerTotalIngresosPorFecha(LocalDate fecha);
    Map<String, Long> obtenerMetodosPagoMasUsados();
    String generarReporteIngresos();
}