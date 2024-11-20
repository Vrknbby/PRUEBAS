package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.EquipoServicio;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EquipoControllerTest {

    @Autowired
    private EquipoController equipoController;

    @Autowired
    private EquipoServicio equipoServicio;

    private Equipo equipo;
    @BeforeEach
    void setUp() {
        equipo = new Equipo();
        equipo.setId(1L);
        equipo.setNombre("Barcelona FC");
        equipo.setPais("España");
        equipo.setFechaFundacion("02-02-1899");
        equipo.setPresidente("Berta");
    }

    @Test
    public void testEliminarUsuarioPorId() {
        equipoServicio.eliminarEquipo(equipo.getId());
        String respuesta = equipoController.eliminarEquipoPorId(equipo.getId());
        assertEquals("El Equipo se ha elimino correctamente", respuesta);
    }

    @Test
    public void testObtenerEquipo() {

        Equipo equipo1 = new Equipo();
        equipo1.setId(2L);
        equipo1.setNombre("Atletico de Madrid");
        equipo1.setPais("España");
        equipo1.setFechaFundacion("26-04-1903");
        equipo1.setPresidente("Miguel Angel Gil Marin");

        ArrayList<Equipo> response = equipoController.obtenerEquipo();

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(19, response.size()),
                () -> assertEquals(equipo1.getId(), response.get(0).getId()),
                () -> assertEquals(equipo1.getNombre(), response.get(0).getNombre()),
                () -> assertEquals(equipo1.getPais(), response.get(0).getPais()),
                () -> assertEquals(equipo1.getFechaFundacion(), response.get(0).getFechaFundacion()),
                () -> assertEquals(equipo1.getPresidente(), response.get(0).getPresidente())
        );
    }
}