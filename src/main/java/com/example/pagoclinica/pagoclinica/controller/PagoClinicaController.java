package com.example.pagoclinica.pagoclinica.controller;



 // Importamos el servicio
import com.example.pagoclinica.pagoclinica.domain.dto.PayClinicalDTO;
import com.example.pagoclinica.pagoclinica.domain.service.PayClinicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoClinicaController {

    @Autowired
    private PayClinicalService payClinicalService; // Inyectamos el servicio

    @GetMapping
    public ResponseEntity<List<PayClinicalDTO>> obtenerTodosLosPagos() {
        return ResponseEntity.ok(payClinicalService.obtenerTodosLosPagos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayClinicalDTO> obtenerPagoPorId(@PathVariable Long id) {
        PayClinicalDTO pago = payClinicalService.obtenerPagoPorId(id);
        if (pago != null) {
            return ResponseEntity.ok(pago);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PayClinicalDTO> registrarPago(@RequestBody PayClinicalDTO payClinicalDTO) {
        PayClinicalDTO nuevoPago = payClinicalService.registrarPago(payClinicalDTO);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<List<PayClinicalDTO>> obtenerPagosPorCitaId(@PathVariable Long citaId) {
        return ResponseEntity.ok(payClinicalService.obtenerPagosPorCitaId(citaId));
    }

    @GetMapping("/reporte/ingresos")
    public ResponseEntity<String> generarReporteIngresos() {
        return ResponseEntity.ok(payClinicalService.generarReporteIngresos());
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<PayClinicalDTO>> obtenerPagosPendientes() {
        return ResponseEntity.ok(payClinicalService.obtenerPagosPendientes());
    }
}