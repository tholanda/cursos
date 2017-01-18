package com.thq.aluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thq.aluno.model.Aluno;

public interface Alunos extends JpaRepository<Aluno, Long>{
	
}
