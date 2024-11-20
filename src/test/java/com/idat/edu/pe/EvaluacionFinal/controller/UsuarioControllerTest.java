package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.UsuarioServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioServicio usuarioServicio;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerUsuarioPorEmail() {

        String emailPrueba = "ricardo@gmail.com";
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        usuarioMock.setNombre("Ricardo");
        usuarioMock.setEmail(emailPrueba);
        usuarioMock.setPassword("123456");

        // Simular comportamiento del servicio
        when(usuarioServicio.obtenerPorEmail(emailPrueba)).thenReturn(Optional.of(usuarioMock));

        // Ejecutar el método del controlador
        Optional<Usuario> respuesta = usuarioController.obtenerUsuarioPorEmail(emailPrueba);

        // Validar resultados
        assertTrue(respuesta.isPresent());
        assertEquals(emailPrueba, respuesta.get().getEmail());
        assertEquals("Ricardo", respuesta.get().getNombre());
        assertEquals(1L, respuesta.get().getId());
    }

    @Test
    void testObtenerUsuarioPorEmailNoEncontrado() {

        String emailNoExiste = "no.existe@gmail.com";
        when(usuarioServicio.obtenerPorEmail(emailNoExiste)).thenReturn(Optional.empty());


        Optional<Usuario> respuesta = usuarioController.obtenerUsuarioPorEmail(emailNoExiste);

        assertFalse(respuesta.isPresent());
    }
}
