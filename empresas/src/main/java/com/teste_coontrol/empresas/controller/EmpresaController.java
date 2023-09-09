package com.teste_coontrol.empresas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.teste_coontrol.empresas.entity.Empresa;
import com.teste_coontrol.empresas.service.EmpresaService;

@Controller
public class EmpresaController {

    private EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        super();
        this.empresaService = empresaService;
    }

    @GetMapping("/empresas")
    public String listEmpresas(Model model) {
        model.addAttribute("empresas", empresaService.getAllEmpresas());
        return "empresas";
    }

    @GetMapping("/empresas/new")
    public String createEmpresaForm(Model model) {
        Empresa empresa = new Empresa();
        model.addAttribute("empresa", empresa);
        return "create_empresa";
    }

    @PostMapping("/empresas")
    public String saveEmpresa(@ModelAttribute("empresa") Empresa empresa) {
        empresaService.saveEmpresa(empresa);
        return "redirect:/empresas";
    }

    @GetMapping("/empresas/edit/{id}")
    public String editEmpresaForm(@PathVariable Long id, Model model) {
        model.addAttribute("empresa", empresaService.getEmpresaById(id));
        return "edit_empresa";
    }

    @PostMapping("/empresas/{id}")
    public String updateEmpresa(@PathVariable Long id, @ModelAttribute("empresa") Empresa empresa, Model model) {

        Empresa existingEmpresa = empresaService.getEmpresaById(id);

        existingEmpresa.setNomeEmpresa(empresa.getNomeEmpresa());
        existingEmpresa.setDataFundacao(empresa.getDataFundacao());
        existingEmpresa.setNumFuncionarios(empresa.getNumFuncionarios());
        existingEmpresa.setRegiaoBrasil(empresa.getRegiaoBrasil());
        existingEmpresa.setSetorAtuacao(empresa.getSetorAtuacao());

        empresaService.updateEmpresa(existingEmpresa);

        return "redirect:/empresas";
    }

    @GetMapping("/empresas/{id}")
    public String deleteEmpresa(@PathVariable Long id) {
        empresaService.deleteEmpresaById(id);
        return "redirect:/empresas";
    }

}
