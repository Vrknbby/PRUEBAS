package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.controller.UsuarioController;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.UsuarioServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

        String emailPrueba = "rodrigotocto@gmail.com";
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        usuarioMock.setNombre("Rodrigo Tocto");
        usuarioMock.setEmail(emailPrueba);
        usuarioMock.setPassword("123456789");


        when(usuarioServicio.obtenerPorEmail(emailPrueba)).thenReturn(Optional.of(usuarioMock));


        Optional<Usuario> respuesta = usuarioController.obtenerUsuarioPorEmail(emailPrueba);

        assertTrue(respuesta.isPresent());
        assertEquals(emailPrueba, respuesta.get().getEmail());
        assertEquals("Rodrigo Tocto", respuesta.get().getNombre());
    }
}
