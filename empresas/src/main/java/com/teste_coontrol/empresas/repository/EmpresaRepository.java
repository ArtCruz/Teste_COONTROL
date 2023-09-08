package com.teste_coontrol.empresas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste_coontrol.empresas.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
    
}
