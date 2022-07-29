package models;

import entities.Aluno;
import entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CursoModel {
    public void create(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("curso criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um curso !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Curso findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        Curso cursoEncontrado = em.find(Curso.class, id);
        em.close();
        return cursoEncontrado;
    }

    @SuppressWarnings("unchecked")
    public List<Curso> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        List<Curso> alunos = new ArrayList<Curso>();

        alunos = em.createQuery("FROM Curso").getResultList();
        em.close();
        return alunos;
    }

    public void update(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("UPDATE Curso SET nome = ?1 , sigla = ?2 , professor = ?3 , materialCurso = ?4" +
                            "WHERE id = :id")
                    .setParameter(1, curso.getNome())
                    .setParameter(2, curso.getSigla())
                    .setParameter(3, curso.getProfessor())
                    .setParameter(4, curso.getMaterialCurso())
                    .setParameter("id", curso.getId()).executeUpdate();
            em.getTransaction().commit();
            System.out.println("curso atualizada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar a curso !!!" + e.getMessage() + " " + e.getCause());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("delete from Curso where id = :id")
                    .setParameter("id", curso.getId())
                    .executeUpdate();
            em.getTransaction().commit();
            System.out.println("curso removida com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao remover o curso !!!" + e.getMessage() + " " + e.getCause());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
}
