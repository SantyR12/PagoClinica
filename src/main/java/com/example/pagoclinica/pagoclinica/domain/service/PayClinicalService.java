package com.example.pagoclinica.pagoclinica.domain.service;// Importamos la implementación del repositorio
import com.example.pagoclinica.pagoclinica.domain.dto.PayClinicalDTO;
import com.example.pagoclinica.pagoclinica.infraestructura.repositories.PayClinicalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class PayClinicalService { // Ya no implementa IPayClinical

    @Autowired
    private PayClinicalImpl pagoClinicaImpl; // Inyectamos la implementación concreta

    public PayClinicalDTO registrarPago(PayClinicalDTO payClinicalDTO) {
        return pagoClinicaImpl.registrarPago(payClinicalDTO);
    }

    public PayClinicalDTO obtenerPagoPorId(Long id) {
        return pagoClinicaImpl.obtenerPagoPorId(id);
    }

    public List<PayClinicalDTO> obtenerTodosLosPagos() {
        return pagoClinicaImpl.obtenerTodosLosPagos();
    }

    public List<PayClinicalDTO> obtenerPagosPorCitaId(Long citaId) {
        return pagoClinicaImpl.obtenerPagosPorCitaId(citaId);
    }

    public List<PayClinicalDTO> obtenerPagosPendientes() {
        return pagoClinicaImpl.obtenerPagosPendientes();
    }

    public BigDecimal obtenerTotalIngresosPorFecha(LocalDate fecha) {
        return pagoClinicaImpl.obtenerTotalIngresosPorFecha(fecha);
    }

    public Map<String, Long> obtenerMetodosPagoMasUsados() {
        return pagoClinicaImpl.obtenerMetodosPagoMasUsados();
    }

    public String generarReporteIngresos() {
        return pagoClinicaImpl.generarReporteIngresos();
    }
}