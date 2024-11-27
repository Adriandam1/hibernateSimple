package services;

import config.HibernateConfig;
import model.Gatos;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GatosServices {

    public void crearGato(String nome, String raza, Integer idade, String cor, BigDecimal peso, Date dataAdopcion, Boolean vacinado, String nomeDono) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Gatos novoGato = new Gatos();
            novoGato.setNome(nome);
            novoGato.setRaza(raza);
            novoGato.setIdade(idade);
            novoGato.setCor(cor);
            novoGato.setPeso(peso);
            novoGato.setDataAdopcion(dataAdopcion);
            novoGato.setVacinado(vacinado);
            novoGato.setNomeDono(nomeDono);
            session.save(novoGato);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao crea-lo gato: " + e.getMessage());
        }
    }

    public Gatos lerGato(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Gatos.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao ler o gato: " + e.getMessage());
            return null;
        }
    }

    public void actualizarGato(Long id, String nome, String raza, Integer idade, String cor, BigDecimal peso, Date dataAdopcion, Boolean vacinado, String nomeDono) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Gatos gato = session.get(Gatos.class, id);
            if (gato != null) {
                gato.setNome(nome);
                gato.setRaza(raza);
                gato.setIdade(idade);
                gato.setCor(cor);
                gato.setPeso(peso);
                gato.setDataAdopcion(dataAdopcion);
                gato.setVacinado(vacinado);
                gato.setNomeDono(nomeDono);
                session.update(gato);
            } else {
                System.out.println("Gato non encontrado para actualizar.");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualiza-lo gato: " + e.getMessage());
        }
    }
    public void actualizarGato(Gatos gato) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
                session.update(gato);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualiza-lo gato: " + e.getMessage());
        }
    }

    public void eliminarGato(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Gatos gato = session.get(Gatos.class, id);
            if (gato != null) {
                session.delete(gato);
            } else {
                System.out.println("non se atopou o gato");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Non quero eliminar ningun gatiño Y.Y: " + e.getMessage());
        }
    }


    public Gatos lerGatoPorNomeEDono(String nome, String nomeDono) {

        Gatos gato = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();

            List<Gatos> gatosList = session.createQuery("from Gatos where nome = :nome and nomeDono = :nomeDono", Gatos.class)
                    .setParameter("nome", nome)
                    .setParameter("nomeDono", nomeDono)
                    .getResultList();

            if (!gatosList.isEmpty()) {
                gato = gatosList.get(0);  // Suponemos que el nombre y el dueño son únicos
            } else {
                System.out.println("Non se atopou o gato co nome " + nome + " e o propietario " + nomeDono);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao ler o gato: " + e.getMessage());
        }
        return gato;
    }


    public void eliminarGatoPorNomeEDono(String nome, String nomeDono) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Gatos> gatosList = session.createQuery("from Gatos where nome = :nome and nomeDono = :nomeDono", Gatos.class)
                    .setParameter("nome", nome)
                    .setParameter("nomeDono", nomeDono)
                    .getResultList();

            if (!gatosList.isEmpty()) {
                Gatos gato = gatosList.get(0);
                session.delete(gato);
                System.out.println("Gato " + nome + " do propietario " + nomeDono + " eliminado correctamente.");
            } else {
                System.out.println("Non se atopou o gato co nome " + nome + " e o propietario " + nomeDono + ".");
            }

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Non quero eliminar ningun gatiño Y.Y: " + e.getMessage());
        }
    }

    public List<Gatos> listarGatos() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Gatos", Gatos.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao lista-los gatos: " + e.getMessage());
            return null;
        }
    }
}
