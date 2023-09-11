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

        //Para cada empresa cadastrada, 
        for (Empresa empresa : getAllEmpresas()) {
            String regiao = empresa.getRegiaoBrasil();
            int qtdFunc = empresa.getNumFuncionarios();

            //Se o Map já tiver Um Key contendo o valor da região, ele somente incrementará a quantidade de funcionarios dessa empresa
            //na região ja presente
            if (contFuncPorRegiao.containsKey(regiao)) {
                int totalFunc = contFuncPorRegiao.get(regiao) + qtdFunc;
                contFuncPorRegiao.put(regiao, totalFunc);
            
            } //Se o Map não possui um Key contendo o valor da região, ele irá adicionar a nova região e adicionará a quantidade dos 
            //funcionarios da empresa
            else {
                contFuncPorRegiao.put(regiao, qtdFunc);
            }
        }

        String regiaoMaisEmpresas = "";
        int maiorQtdFunc = 0;

        //Após percorrer todas as empresas cadastradas, o sistema agora terá um Map contendo todas as regiões Cadastradas, bem como a quantidade
        //de funcionarios dessas regiões. 
        for (Map.Entry<String, Integer> entry : contFuncPorRegiao.entrySet()) {
            String regiaoEspec = entry.getKey();
            int contFuncRegiaoEspec = entry.getValue();

        //Ele agora irá encontrar qual região tem mais funcionários, a primeira região terá automaticamente a maior
        //quantidade de funcionarios e as regioes subsequentes nesse Map, caso tenham uma quantidade de funcionários superior ao maiorQtdFunc, farão com que
        //a região se torne a "regiaoMaisEmpresas", isso persiste até todo o Map ser percorrido.
            if (contFuncRegiaoEspec > maiorQtdFunc) {
                regiaoMaisEmpresas = regiaoEspec;
                maiorQtdFunc = contFuncRegiaoEspec;
            }
        }
        //Retorna a Região com Mais funcionarios e essa quantidade de funcionarios.
        String txt = "Região com Mais Funcionários é o " + regiaoMaisEmpresas + ", com " + maiorQtdFunc
                + " funcionários";

        return txt;
    }

    public String EmpresaMaisAntiga() {

        //Se não houver nenhum empresa cadastrada retorna null
        if (getAllEmpresas().isEmpty()) {
            return null;
        }

        //Pega a primeira empresa cadastrada no sistema
        Empresa empresaMaisAntiga = getAllEmpresas().get(0);

        //Caso a data de fundação de uma empresa seja mais antiga que a data data de fundação de empresaMaisAntiga,
        //empresaMaisAntiga recebe tal empresa
        for (Empresa empresa : getAllEmpresas()) {
            if (empresa.getDataFundacao().before(empresaMaisAntiga.getDataFundacao())) {
                empresaMaisAntiga = empresa;
            }
        }
        //Retorna o nome da empresa mais antiga do sistema
        String txt = "A Empresa Mais Antiga é: " + empresaMaisAntiga.getNomeEmpresa();

        return txt;

    }

    public String regiaoMaisEmpresasIndustrial() {

        Map<String, Integer> regioes = new HashMap();

        //Somente adicionara no map, regiões que tenham empresas no setor Industrial, e a cada nova empresa do setor Industrial
        //cuja região já está cadastrada, incrementará em 1 o valor de empresas nessa região
        for (Empresa empresa : getAllEmpresas()) {
            String regiao = empresa.getRegiaoBrasil();
            if ("Industrial".equals(empresa.getSetorAtuacao())) {
                regioes.put(regiao, regioes.getOrDefault(regiao, 0) + 1);
            }
        }

        String regiaoMaisEmpresas = "";
        int qtdEmpresas = 0;

        //Ele agora irá encontrar qual região tem mais empresas, a primeira região terá automaticamente a maior
        //quantidade de empresas e as regioes subsequentes nesse Map, caso tenham uma quantidade de empresas superior ao qtdEmpresas, farão com que
        //a região se torne a "regiaoMaisEmpresas", isso persiste até todo o Map ser percorrido.
        for (Map.Entry<String, Integer> entry : regioes.entrySet()) {
            String regiaoEspec = entry.getKey();
            int contEmpRegiaoEspec = entry.getValue();

            if (contEmpRegiaoEspec > qtdEmpresas) {
                regiaoMaisEmpresas = regiaoEspec;
                qtdEmpresas = contEmpRegiaoEspec;
            }
        }

        //Retorna a região com mais empresas do setor Industrial, bem como essa quantidade de empresas
        String txt = "Região com Mais Empresas do Setor Industrial é o " + regiaoMaisEmpresas + ", com " + qtdEmpresas
                + " empresas";

        return txt;
    }

    public String numEmpresasSetorDecrescente() {
        String txt = "";

        Map<String, Integer> setores = new HashMap();

        //Adiciona em um Map todos os Setores que possuem uma empresa, bem como a quantidade dessas empresas
        for (Empresa empresa : getAllEmpresas()) {
            String setor = empresa.getSetorAtuacao();
            setores.put(setor, setores.getOrDefault(setor, 0) + 1);
        }

        //Converte esse Map em uma lista de entradas
        List<Map.Entry<String, Integer>> listaMap = new ArrayList<>(setores.entrySet());

        //Ordena a lista de forma decresente conforme a quantidade de empresas de um Setor
        listaMap.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        //Cria um novo mapa ordenado com base na lista ordenada
        LinkedHashMap<String, Integer> mapaOrdenado = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entrada : listaMap) {
            mapaOrdenado.put(entrada.getKey(), entrada.getValue());
        }
        //Retorna todos os setores cadastradas, com suas respectivas quantidades de empresas de forma decrescente
        for (Map.Entry<String, Integer> entry : mapaOrdenado.entrySet()) {
            txt += "Quantidade Empresas Setor " + entry.getKey() + ": " + entry.getValue()+ ". ";
        }

        return txt;
    }

    public String totalFuncionarios() {

        int cont = 0;

        //Percorre todas as empresas do sistema, e vai somando a quantidade de funcionários a uma variavel até todas as empresas serem percoridas
        for (Empresa empresa : getAllEmpresas()) {
            cont += empresa.getNumFuncionarios();
        }

        String txt = "O Número Total de Funcionários é: " + cont + " funcionários.";

        return txt;
    }

}
