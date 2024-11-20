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

import java.util.ArrayList;

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
        usuario.setId(1L);
        usuario.setNombre("Juan Pérez");
        usuario.setEmail("juan.perez@example.com");
        usuario.setPassword("password123");
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
        Usuario usuario1 = new Usuario();
        usuario1.setId(2L);
        usuario1.setNombre("Juan Pérez Actualizado");
        usuario1.setEmail("juan.perez.actualizado@example.com");
        usuario1.setPassword("newpassword123");

        ArrayList<Usuario> resultado = usuarioController.obtenerUsuarios();

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(19, resultado.size()),
                () -> assertEquals(usuario1.getId(), resultado.get(0).getId()),
                () -> assertEquals(usuario1.getNombre(), resultado.get(0).getNombre()),
                () -> assertEquals(usuario1.getEmail(), resultado.get(0).getEmail()),
                () -> assertEquals(usuario1.getPassword(), resultado.get(0).getPassword())
        );
    }
}
