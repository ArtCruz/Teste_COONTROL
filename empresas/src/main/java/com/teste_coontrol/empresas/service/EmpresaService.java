package com.teste_coontrol.empresas.service;


import java.util.List;

import com.teste_coontrol.empresas.entity.Empresa;

public interface EmpresaService {
    
    List<Empresa> getAllEmpresas();

    Empresa saveEmpresa(Empresa empresa);

    Empresa getEmpresaById(Long id);

    Empresa updateEmpresa(Empresa empresa);

    void deleteEmpresaById(Long id);

    String regiaoMaisFuncionarios();

    String EmpresaMaisAntiga();

    String regiaoMaisEmpresasIndustrial();

    String numEmpresasSetorDecrescente();

    String totalFuncionarios();

}
