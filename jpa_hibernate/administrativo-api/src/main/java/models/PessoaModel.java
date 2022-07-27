
import entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {
    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa cadastrada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao cadastrar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Pessoa p) {
        //TODO
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("UPDATE Pessoa SET nome = ?1 , email = ?2 , idade = ?3 , cpf = ?4 , dataDeNascimento = ?5 " +
                            "WHERE id = :id")
                    .setParameter(1, p.getNome())
                    .setParameter(2, p.getEmail())
                    .setParameter(3, p.getIdade())
                    .setParameter(4, p.getCpf())
                    .setParameter(5, p.getDataDeNascimento())
                    .setParameter("id", p.getId()).executeUpdate();
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar a pessoa !!!" + e.getMessage() + " " + e.getCause());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("delete from Pessoa where id = :id")
                    .setParameter("id", p.getId())
                    .executeUpdate();
            em.getTransaction().commit();
            System.out.println("Pessoa removida com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao remover a pessoa !!!" + e.getMessage() + " " + e.getCause());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Pessoa findById(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        Pessoa pessoaEncontrada = em.find(Pessoa.class, p.getId());
        em.close();
        return pessoaEncontrada;
    }

    @SuppressWarnings("unchecked")
    public List<Pessoa> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        pessoas = em.createQuery("FROM Pessoa").getResultList();
        em.close();
        return pessoas;
    }
}
