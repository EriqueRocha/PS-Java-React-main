package br.com.banco.repository;

import br.com.banco.model.conta.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<ContaEntity, Integer> {
    ContaEntity findAllByIdConta(Integer id);
}
