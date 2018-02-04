package br.edu.ifpr.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.trabalho.entidade.Cidade;

public interface RepositoryCidade extends JpaRepository<Cidade,Integer>{

}
