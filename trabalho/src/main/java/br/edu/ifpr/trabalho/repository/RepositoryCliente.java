package br.edu.ifpr.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.trabalho.entidade.Cliente;

public interface RepositoryCliente extends JpaRepository<Cliente,Integer>{

}
