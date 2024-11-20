package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.service.UsuarioServicio;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private UsuarioServicio usuarioServicio;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(4L);
        usuario.setNombre("Cristian Medina");
        usuario.setEmail("mak_21_05@hotmail.com");
        usuario.setPassword("123456");
        usuario.setFechaNacimiento(LocalDate.parse("2000-08-22"));
        usuario.setFondos(new BigDecimal("740.00"));

    }

    @Test
    void testEliminarUsuarioPorId() {
        usuarioServicio.eliminarUsuario(usuario.getId());
        String respuesta = usuarioController.eliminarUsuarioPorId(usuario.getId());
        assertEquals("El usuario se elimino correctamente", respuesta);
    }

    @Test
    void testActualizarUsuario() {
        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setId(2L);
        usuarioActualizado.setNombre("Juan Pérez Actualizado");
        usuarioActualizado.setEmail("juan.perez.actualizado@example.com");
        usuarioActualizado.setPassword("newpassword123");

        ResponseEntity<Usuario> response = usuarioController.actualizarUsuario(usuarioActualizado.getId(), usuarioActualizado);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Juan Pérez Actualizado", response.getBody().getNombre());
        assertEquals("juan.perez.actualizado@example.com", response.getBody().getEmail());
        assertEquals("newpassword123", response.getBody().getPassword());
    }

    @Test
    void testObtenerUsuarios() {
        ArrayList<Usuario> resultado = usuarioController.obtenerUsuarios();
        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(18, resultado.size())
        );
    }

    @Test
    void obtenerUsuarioPorId() {

        Optional<Usuario> usuarioEncontrado = usuarioController.obtenerUsuarioPorId(2L);

        assertEquals(2L, usuarioEncontrado.get().getId());
        assertEquals("Juan Pérez Actualizado", usuarioEncontrado.get().getNombre());
        assertEquals("juan.perez.actualizado@example.com", usuarioEncontrado.get().getEmail());
    }
}
