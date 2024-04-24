package br.com.luishbarros.gestao_vagas.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luishbarros.gestao_vagas.modules.company.entities.JobsEntity;

public interface JobRepository extends JpaRepository<JobsEntity, UUID> {
}
