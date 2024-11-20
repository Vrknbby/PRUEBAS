package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.UsuarioServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UsuarioControllerTest {

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private UsuarioServicio usuarioServicio;
    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(2L);
        usuario.setNombre("Juan Pérez");
        usuario.setEmail("juan.perez@example.com");
        usuario.setPassword("password123");

    }

    @Test
    void obtenerUsuarioPorId() {

        Optional<Usuario> usuarioEncontrado = usuarioController.obtenerUsuarioPorId(usuario.getId());

        assertEquals(usuario.getId(), usuarioEncontrado.get().getId());
        assertEquals(usuario.getNombre(), usuarioEncontrado.get().getNombre());
        assertEquals(usuario.getEmail(), usuarioEncontrado.get().getEmail());
    }

}