package models;

import entities.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

//gestao-cursos-jpa

public class AlunoModel {
    public void create(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        Aluno alunoEncontrado = em.find(Aluno.class, id);
        em.close();
        return alunoEncontrado;
    }

    @SuppressWarnings("unchecked")
    public List<Aluno> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        List<Aluno> alunos = new ArrayList<Aluno>();

        alunos = em.createQuery("FROM Aluno").getResultList();
        em.close();
        return alunos;
    }

    public void update(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("UPDATE Aluno SET nomeCompleto = ?1 , matricula = ?2 , nascimento = ?3 , email = ?4" +
                            "WHERE id = :id")
                    .setParameter(1, aluno.getNomeCompleto())
                    .setParameter(2, aluno.getMatricula())
                    .setParameter(3, aluno.getNascimento())
                    .setParameter(4, aluno.getEmail())
                    .setParameter("id", aluno.getId()).executeUpdate();
            em.getTransaction().commit();
            System.out.println("Aluno atualizada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar a Aluno !!!" + e.getMessage() + " " + e.getCause());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("delete from Aluno where id = :id")
                    .setParameter("id", aluno.getId())
                    .executeUpdate();
            em.getTransaction().commit();
            System.out.println("Aluno removida com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao remover o aluno !!!" + e.getMessage() + " " + e.getCause());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
}
