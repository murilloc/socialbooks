package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
