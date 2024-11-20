package com.idat.edu.pe.EvaluacionFinal.repository;



import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>  findByEmail(String email);


}
