package com.teste_coontrol.empresas.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.teste_coontrol.empresas.entity.Empresa;
import com.teste_coontrol.empresas.repository.EmpresaRepository;
import com.teste_coontrol.empresas.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        super();
        this.empresaRepository = empresaRepository;
    }

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

    public String regiaoMaisFuncionarios() {

        Map<String, Integer> contFuncPorRegiao = new HashMap();

        for (Empresa empresa : getAllEmpresas()) {
            String regiao = empresa.getRegiaoBrasil();
            int qtdFunc = empresa.getNumFuncionarios();

            if (contFuncPorRegiao.containsKey(regiao)) {
                int totalFunc = contFuncPorRegiao.get(regiao) + qtdFunc;
                contFuncPorRegiao.put(regiao, totalFunc);
            } else {
                contFuncPorRegiao.put(regiao, qtdFunc);
            }
        }

        String regiaoMaisEmpresas = "";
        int maiorQtdFunc = 0;

        for (Map.Entry<String, Integer> entry : contFuncPorRegiao.entrySet()) {
            String regiaoEspec = entry.getKey();
            int contFuncRegiaoEspec = entry.getValue();

            if (contFuncRegiaoEspec > maiorQtdFunc) {
                regiaoMaisEmpresas = regiaoEspec;
                maiorQtdFunc = contFuncRegiaoEspec;
            }
        }

        String txt = "Região com Mais Funcionários é o " + regiaoMaisEmpresas + ", com " + maiorQtdFunc
                + " funcionários";

        return txt;
    }

    public String EmpresaMaisAntiga() {

        if (getAllEmpresas().isEmpty()) {
            return null;
        }

        Empresa empresaMaisAntiga = getAllEmpresas().get(0);

        for (Empresa empresa : getAllEmpresas()) {
            if (empresa.getDataFundacao().before(empresaMaisAntiga.getDataFundacao())) {
                empresaMaisAntiga = empresa;
            }
        }

        String txt = "A Empresa Mais Antiga é: " + empresaMaisAntiga.getNomeEmpresa();

        return txt;

    }

    public String regiaoMaisEmpresasIndustrial() {

        Map<String, Integer> regioes = new HashMap();

        for (Empresa empresa : getAllEmpresas()) {
            String regiao = empresa.getRegiaoBrasil();
            if ("Industrial".equals(empresa.getSetorAtuacao())) {
                regioes.put(regiao, regioes.getOrDefault(regiao, 0) + 1);
            }
        }

        String regiaoMaisEmpresas = "";
        int qtdEmpresas = 0;

        for (Map.Entry<String, Integer> entry : regioes.entrySet()) {
            String regiaoEspec = entry.getKey();
            int contFuncRegiaoEspec = entry.getValue();

            if (contFuncRegiaoEspec > qtdEmpresas) {
                regiaoMaisEmpresas = regiaoEspec;
                qtdEmpresas = contFuncRegiaoEspec;
            }
        }

        String txt = "Região com Mais Empresas do Setor Industrial é o " + regiaoMaisEmpresas + ", com " + qtdEmpresas
                + " funcionários";

        return txt;
    }

    public String numEmpresasSetorDecrescente() {
        String txt = "";

        Map<String, Integer> setores = new HashMap();

        for (Empresa empresa : getAllEmpresas()) {
            String setor = empresa.getSetorAtuacao();
            setores.put(setor, setores.getOrDefault(setor, 0) + 1);
        }

        // Converter o mapa em uma lista de entradas (chave-valor)
        List<Map.Entry<String, Integer>> listaMap = new ArrayList<>(setores.entrySet());

        // Ordenar a lista de forma decresente conforme o comparador
        listaMap.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Criar um novo mapa ordenado com base na lista ordenada
        LinkedHashMap<String, Integer> mapaOrdenado = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entrada : listaMap) {
            mapaOrdenado.put(entrada.getKey(), entrada.getValue());
        }
        // Exibir o mapa ordenado
        for (Map.Entry<String, Integer> entry : mapaOrdenado.entrySet()) {
            txt += "Quantidade Empresas Setor " + entry.getKey() + ": " + entry.getValue()+ ". ";
        }

        return txt;
    }

    public String totalFuncionarios() {

        int cont = 0;

        for (Empresa empresa : getAllEmpresas()) {
            cont += empresa.getNumFuncionarios();
        }

        String txt = "O Número Total de Funcionários é: " + cont + " funcionários.";

        return txt;
    }

}
