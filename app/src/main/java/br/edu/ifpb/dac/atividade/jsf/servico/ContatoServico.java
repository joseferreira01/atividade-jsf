/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.jsf.servico;

import br.edu.ifpb.dac.atividade.jsf.entity.Contato;
import br.edu.ifpb.dac.atividade.jsf.infra.Repositorio;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author jose
 */
@Named
@RequestScoped
public class ContatoServico {

    @Inject
    private Repositorio repositorio;

    public void salvar(Contato contato) {
        System.err.println("sando "+contato);
        repositorio.salvar(contato);
        
    }

    public void atualizar(Contato contato) {
        System.err.println("atu" +contato);
        repositorio.atualizar(contato);
    }

    public void remover(Contato contato) {
        this.repositorio.remover(contato);
    }

    public Contato buscar(long id) {
        return repositorio.buscar(id);
    }

    public List<Contato> buscarTodos() {
        return repositorio.buscarTodos();
    }

    public List<Contato> getAllFirstLettersAsc() {
        return repositorio.getAllFirstLettersAsc();

    }

    public List<Contato> getContatoPorNome(String nome) {
        return repositorio.getContatoPorNome(nome);
    }
}
