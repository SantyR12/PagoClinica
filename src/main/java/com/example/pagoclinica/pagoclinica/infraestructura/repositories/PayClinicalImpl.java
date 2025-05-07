package com.example.pagoclinica.pagoclinica.infraestructura.repositories;




import com.example.pagoclinica.pagoclinica.domain.dto.PayClinicalDTO;
import com.example.pagoclinica.pagoclinica.domain.repository.IPayClinical;
import com.example.pagoclinica.pagoclinica.infraestructura.crud.PagoRepository;
import com.example.pagoclinica.pagoclinica.infraestructura.entity.PagoClinica;
import com.example.pagoclinica.pagoclinica.infraestructura.mapper.PagoClinicaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayClinicalImpl implements IPayClinical {

    private final PagoRepository pagoRepository;
    private final PagoClinicaMapper pagoClinicaMapper;

    @Autowired
    public PayClinicalImpl(PagoRepository pagoRepository, PagoClinicaMapper pagoClinicaMapper) {
        this.pagoRepository = pagoRepository;
        this.pagoClinicaMapper = pagoClinicaMapper;
    }

    @Override
    public PayClinicalDTO registrarPago(PayClinicalDTO payClinicalDTO) {
        PagoClinica pagoClinica = pagoClinicaMapper.toPagoClinica(payClinicalDTO);
        PagoClinica pagoGuardado = pagoRepository.save(pagoClinica);
        return pagoClinicaMapper.toPayClinicalDTO(pagoGuardado);
    }

    @Override
    public PayClinicalDTO obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id)
                .map(pagoClinicaMapper::toPayClinicalDTO)
                .orElse(null);
    }

    @Override
    public List<PayClinicalDTO> obtenerTodosLosPagos() {
        return pagoRepository.findAll().stream()
                .map(pagoClinicaMapper::toPayClinicalDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PayClinicalDTO> obtenerPagosPorCitaId(Long citaId) {
        return pagoRepository.findByCitaId(citaId).stream()
                .map(pagoClinicaMapper::toPayClinicalDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PayClinicalDTO> obtenerPagosPendientes() {
        return pagoRepository.findByEstadoPago("Pendiente").stream()
                .map(pagoClinicaMapper::toPayClinicalDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal obtenerTotalIngresosPorFecha(LocalDate fecha) {
        return pagoRepository.sumMontoByFechaPago(fecha);
    }

    @Override
    public Map<String, Long> obtenerMetodosPagoMasUsados() {
        return pagoRepository.findMetodosPagoMasUsados().stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Long) result[1]
                ));
    }

    @Override
    public String generarReporteIngresos() {
        StringBuilder reporte = new StringBuilder("--- Reporte de Ingresos ---\n\n");

        // Total de ingresos por fecha (ejemplo para la fecha actual)
        LocalDate hoy = LocalDate.now();
        BigDecimal totalHoy = obtenerTotalIngresosPorFecha(hoy);
        reporte.append("Total de ingresos el ").append(hoy).append(": $").append(totalHoy).append("\n");

        // Métodos de pago más usados
        Map<String, Long> metodosMasUsados = obtenerMetodosPagoMasUsados();
        reporte.append("\nMétodos de pago más usados:\n");
        metodosMasUsados.forEach((metodo, cantidad) ->
                reporte.append("- ").append(metodo).append(": ").append(cantidad).append(" transacciones\n")
        );

        return reporte.toString();
    }
}