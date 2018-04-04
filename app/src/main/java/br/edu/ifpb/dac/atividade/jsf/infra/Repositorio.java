/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.jsf.infra;

import br.edu.ifpb.dac.atividade.jsf.entity.Contato;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jose
 */
@Stateless
public class Repositorio {

    @PersistenceContext
    private EntityManager em;

    public void salvar(Contato contato) {
        em.persist(contato);
    }

    public void atualizar(Contato contato) {
        em.merge(contato);
    }

    public void remover(Contato contato) {
        System.err.println("remove "+contato);
        em.remove(contato);
    }

    public Contato buscar(long id) {
        return em.find(Contato.class, id);
    }

    public List<Contato> buscarTodos() {
        return em.createQuery("select c from Contato c ", Contato.class).getResultList();
    }

    public List<Contato> getAllFirstLettersAsc() {
        return em.createQuery("select c from Contato c order by c.nome", Contato.class).getResultList();

    }

    public List<Contato> getContatoPorNome(String nome) {
        TypedQuery<Contato> query = em.createQuery("select c from Contato c where c.nome=:nome ", Contato.class);
        return query.setParameter("nome", nome).getResultList();
    }

}
