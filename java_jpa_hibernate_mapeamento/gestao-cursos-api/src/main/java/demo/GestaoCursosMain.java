package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.util.Date;
import java.util.HashSet;

public class GestaoCursosMain {
    public static void main(String[] args) {

        Aluno aluno = new Aluno("Joao Victor Bohana", "111111",
                new Date(), "joaovictorbohana@aaa.com");

        Endereco endereco = new Endereco("Rua Tal de Tal", "Rua Tal de Tal, 385, Paulista",
                "385", "Paulista", "SP", "Sao Paulo", 1231231);
        endereco.setAluno(aluno);

        HashSet<Endereco> enderecos = new HashSet<>();
        enderecos.add(endereco);
        aluno.setEnderecos(enderecos);

        Telefone telefone = new Telefone("11", "444444444");
        telefone.setAluno(aluno);

        HashSet<Telefone> telefones = new HashSet<>();
        telefones.add(telefone);
        aluno.setTelefones(telefones);

        //create aluno
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.create(aluno);
        System.out.println("Numero de alunos cadastrados: " + alunoModel.findAll().size());

        //update aluno
        aluno.setMatricula("22222222");
        alunoModel.update(aluno);

        Curso curso = new Curso("Sistemas de Informacao", "SI");
        Professor professor = new Professor("Joelson Silva", "123123123", "joelson@aaa.com");
        MaterialCurso materialCurso = new MaterialCurso("https://www.kabum.com.br/computadores");

        HashSet<Aluno> alunos = new HashSet<>();
        alunos.add(aluno);

        curso.setProfessor(professor);
        curso.setMaterialCurso(materialCurso);
        curso.setAlunos(alunos);

        //create curso
        CursoModel cursoModel = new CursoModel();
        cursoModel.create(curso);
        System.out.println("Numero de cursos cadastrados: " + cursoModel.findAll().size());

        //update curso
        curso.setSigla("SINF");
        cursoModel.update(curso);

        //delete curso
        cursoModel.delete(curso);
        System.out.println("Numero de cursos cadastrados: " + cursoModel.findAll().size());


    }
}
