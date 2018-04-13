/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.jsf.controlador;

import br.edu.ifpb.dac.atividade.jsf.entity.Contato;
import br.edu.ifpb.dac.atividade.jsf.servico.ContatoServico;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@Named
@RequestScoped
public class ContatoControlador implements Serializable{

    @Inject
    private ContatoServico cs;
    private Contato contato;
    private boolean editando = false;
    private boolean notEditando = true;
    private String encontrecontato;
    @Inject
    private Mensagem mensagem;
    private List<Contato> allFirstLettersAsc;

    @PostConstruct
    public void init() { 
        allFirstLettersAsc = cs.buscarTodos();
        this.contato = new Contato();
    }

    public String salvar() {

        try {
            cs.salvar(contato);
            mensagem.addMessage("Contato salvo com sucesso");
            limparContato();
        } catch (Exception e) {
              mensagem.addMessage("Erro ao salvar verifique os dados e tente novamente");
        }
        return null;
    }

    public void editar(Contato contato) {
        this.contato = contato;
        this.editando = true;
        this.notEditando = false;
    }

    public String cancelar() {
        this.editando = false;
        this.notEditando = true;
        limparContato();
        return null;
    }
    
    public void filtro(AjaxBehaviorEvent event){
        this.allFirstLettersAsc = allFirstLettersAsc.stream()
                .filter(c -> c.getNome()
                .toLowerCase()
                .startsWith(encontrecontato.toLowerCase()))
                .collect(Collectors.toList());
       
    }

    public void atualizar() {
        try {
             cs.atualizar(contato);
        limparContato();
              mensagem.addMessage("Contato atualizado");
        } catch (Exception e) {
              mensagem.addMessage("Erro ao atualizar tente novamente");
        }
       
    }

    public void remover(Contato contato) {
        try {
            
             this.cs.remover(contato);
               mensagem.addMessage("contato removido");
        } catch (Exception e) {
              mensagem.addMessage("Erro ao remover contato");
        }
       
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

    public List<Contato> getAllFirstLettersAsc() {
        allFirstLettersAsc = cs.getAllFirstLettersAsc();
        return allFirstLettersAsc;

    }

    public String getEncontrecontato() {
        return encontrecontato;
    }

    public void setEncontrecontato(String encontrecontato) {
        this.encontrecontato = encontrecontato;
    }

    public boolean isNotEditando() {
        return notEditando;
    }

    private void limparContato() {
        this.contato = new Contato();
    }

}
