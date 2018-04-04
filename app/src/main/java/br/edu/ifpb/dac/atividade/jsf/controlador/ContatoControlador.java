/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.jsf.controlador;

import br.edu.ifpb.dac.atividade.jsf.entity.Contato;
import br.edu.ifpb.dac.atividade.jsf.servico.ContatoServico;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class ContatoControlador {
    @Inject
    private ContatoServico cs;
    private Contato contato;
    @PostConstruct
    public void init(){
        this.contato = new Contato();
    }
    public String salvar(){
        System.err.println("antes "+getLista());
        cs.salvar(contato);
         System.err.println("depois "+getLista());
        return null;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
 
    public List<Contato> getLista(){
        int l1,l2,l3;
        List<Contato> buscarTodos = cs.buscarTodos();
        List<Contato> allFirstLettersAsc = cs.getAllFirstLettersAsc();
        List<Contato> contatoPorNome = cs.getContatoPorNome("j");
        return allFirstLettersAsc;
        
        
    }
}
