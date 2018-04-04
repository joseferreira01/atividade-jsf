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
    private boolean editando = false;
    private boolean notEditando = true;
    @PostConstruct
    public void init(){
        this.contato = new Contato();
    }
    
    public String salvar(){
        System.err.println("savando cot "+this.contato);
        cs.salvar(contato);
        
        limparContato();
        return null;
    }
    public void editar(Contato contato){
        this.contato = contato;
        this.editando = true;
        this.notEditando =false;
    }
    public String cancelar(){
        this.editando = false;
        this.notEditando = true;
        limparContato();
        return null;
    }
    public void atualizar(){
        cs.atualizar(contato);
        limparContato();
    }
    public void remover(Contato contato){
        this.cs.remover(contato);
    }

    public boolean isEditando() {
        return editando;
    }

   
    

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
 
    public List<Contato> getAllFirstLettersAsc(){
                List<Contato> allFirstLettersAsc = cs.getAllFirstLettersAsc();
        return allFirstLettersAsc;
        
        
    }

    public boolean isNotEditando() {
        return notEditando;
    }
    private void limparContato(){
        this.contato = new Contato();
    }
    
}
