package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.service.EquipoServicio;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EquipoControllerTest {

    @InjectMocks
    private EquipoController equipoController;

    @Mock
    private EquipoServicio equipoServicio;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); // Inicializa los mocks
    }

    @Test
    public void testObtenerEquipoPorId() {
        // Datos de prueba
        Long equipoId1 = 1L;
        Long equipoId2 = 2L;

        Equipo equipoMock1 = new Equipo();
        equipoMock1.setId(equipoId1);
        equipoMock1.setNombre("Equipo A");
        equipoMock1.setPais("España");
        equipoMock1.setFechaFundacion("1900-01-01");
        equipoMock1.setPresidente("Juan Pérez");

        Equipo equipoMock2 = new Equipo();
        equipoMock2.setId(equipoId2);
        equipoMock2.setNombre("Equipo B");
        equipoMock2.setPais("Argentina");
        equipoMock2.setFechaFundacion("1920-05-15");
        equipoMock2.setPresidente("Carlos Gómez");

        // Comportamiento simulado de los servicios
        when(equipoServicio.obtenerEquipoPorId(equipoId1)).thenReturn(Optional.of(equipoMock1));
        when(equipoServicio.obtenerEquipoPorId(equipoId2)).thenReturn(Optional.of(equipoMock2));

        // Llamada al controlador
        Optional<Equipo> respuesta1 = equipoController.obtenerEquipoPorId(equipoId1);
        Optional<Equipo> respuesta2 = equipoController.obtenerEquipoPorId(equipoId2);

        // Verificaciones
        assertTrue(respuesta1.isPresent());
        assertEquals(equipoId1, respuesta1.get().getId());
        assertEquals("Equipo A", respuesta1.get().getNombre());
        assertEquals("España", respuesta1.get().getPais());
        assertEquals("1900-01-01", respuesta1.get().getFechaFundacion());
        assertEquals("Juan Pérez", respuesta1.get().getPresidente());

        assertTrue(respuesta2.isPresent());
        assertEquals(equipoId2, respuesta2.get().getId());
        assertEquals("Equipo B", respuesta2.get().getNombre());
        assertEquals("Argentina", respuesta2.get().getPais());
        assertEquals("1920-05-15", respuesta2.get().getFechaFundacion());
        assertEquals("Carlos Gómez", respuesta2.get().getPresidente());
    }
}