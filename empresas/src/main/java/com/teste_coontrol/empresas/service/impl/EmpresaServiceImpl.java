package com.teste_coontrol.empresas.service.impl;

import java.util.List;

import com.teste_coontrol.empresas.entity.Empresa;
import com.teste_coontrol.empresas.repository.EmpresaRepository;
import com.teste_coontrol.empresas.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService{

    private EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> getAllEmpresas() {

        return empresaRepository.findAll();
    }

    @Override
    public Empresa saveEmpresa(Empresa empresa) {

        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa getEmpresaById(Long id) {
    
        return empresaRepository.findById(id).get();
    }

    @Override
    public Empresa updateEmpresa(Empresa empresa) {
    
        return empresaRepository.save(empresa);
    }

    @Override
    public void deleteEmpresaById(Long id) {
    
        empresaRepository.deleteById(id);

    }
    
}
