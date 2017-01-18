package com.thq.aluno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thq.aluno.model.Aluno;
import com.thq.aluno.repository.Alunos;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
	
	@Autowired
	private Alunos alunos;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("ListaAlunos");
		modelAndView.addObject("alunos", alunos.findAll());
		modelAndView.addObject(new Aluno());
		
		return modelAndView;
	}
	
	@PostMapping
	public String salvar(Aluno aluno) {
		this.alunos.save(aluno);
		return "redirect:/alunos";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletar(@PathVariable Long id) {
		this.alunos.delete(id);
		return "redirect:/alunos";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("ListaAlunos");
		modelAndView.addObject("alunos", alunos.findAll());
		modelAndView.addObject(alunos.findOne(id));
	
		return modelAndView;
	}

}