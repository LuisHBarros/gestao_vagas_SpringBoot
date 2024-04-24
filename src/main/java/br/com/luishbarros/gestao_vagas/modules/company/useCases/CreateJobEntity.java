package br.com.luishbarros.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luishbarros.gestao_vagas.modules.company.entities.JobsEntity;
import br.com.luishbarros.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobEntity {

    @Autowired
    private JobRepository jobRepository;

    public JobsEntity execute(JobsEntity jobsEntity) {
        return this.jobRepository.save(jobsEntity);
    }
}
