package com.teste_coontrol.empresas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_empresa", nullable = false)
    private String nomeEmpresa;

    @Column(name = "data_fundacao", nullable = false)
    private String dataFundacao;

    @Column(name = "num_funcionarios", nullable = false)
    private int numFuncionarios;

    @Column(name = "regiao_brasil", nullable = false)
    private String regiaoBrasil;

    @Column(name = "setor_atuacao", nullable = false)
    private String setorAtuacao;

    public Empresa() {

    }

    public Empresa(String nomeEmpresa, String dataFundacao, int numFuncionarios, String regiaoBrasil,
            String setorAtuacao) {
        super();
        this.nomeEmpresa = nomeEmpresa;
        this.dataFundacao = dataFundacao;
        this.numFuncionarios = numFuncionarios;
        this.regiaoBrasil = regiaoBrasil;
        this.setorAtuacao = setorAtuacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getNumFuncionarios() {
        return numFuncionarios;
    }

    public void setNumFuncionarios(int numFuncionarios) {
        this.numFuncionarios = numFuncionarios;
    }

    public String getRegiaoBrasil() {
        return regiaoBrasil;
    }

    public void setRegiaoBrasil(String regiaoBrasil) {
        this.regiaoBrasil = regiaoBrasil;
    }

    public String getSetorAtuacao() {
        return setorAtuacao;
    }

    public void setSetorAtuacao(String setorAtuacao) {
        this.setorAtuacao = setorAtuacao;
    }

}
