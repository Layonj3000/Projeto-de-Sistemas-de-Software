/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.registro_proxy;

import br.projeto.adapter.IValidadorSenha;
import br.projeto.adapter.ValidadorSenhaAdapter;
import br.projeto.presenter.LoginUsuarioPresenter;
import br.projeto.presenter.RegistroUsuarioPresenter;
import br.projeto.repository.UsuarioRepository;
import br.projeto.service.InstanciaRepositoryService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author layon
 */
public class RegistroProxy implements IRegistroProxy{
    private RegistroUsuarioPresenter registroUsuarioPresenter;
    private IValidadorSenha validador;
    private UsuarioRepository usuarioRepository = InstanciaRepositoryService.getInstancia().getUsuarioRepository();
    
    public RegistroProxy(RegistroUsuarioPresenter registroUsuarioPresenter) {
        this.registroUsuarioPresenter =  registroUsuarioPresenter;
        this.validador = new ValidadorSenhaAdapter();
    }

    @Override
    public void registrar() {
        if(validarUsuario()){
            registroUsuarioPresenter.registrar();
            new LoginUsuarioPresenter();
            registroUsuarioPresenter.getView().dispose();
        }
    }
    
    private boolean validarUsuario(){
        List<String> mensagensErro = new ArrayList<>();

        if (registroUsuarioPresenter.getNomeUsuario().isEmpty()|| registroUsuarioPresenter.getEmail().isEmpty() ||  registroUsuarioPresenter.getSenha().length() == 0) {
            mensagensErro.add("Todos os campos devem ser preenchidos!");
        }

        if (registroUsuarioPresenter.getNomeUsuario().length() > 12) {
            mensagensErro.add("O nome deve ter no máximo 12 caracteres!");
        }

        if (!registroUsuarioPresenter.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            mensagensErro.add("Email inválido! Use o formato correto (exemplo@dominio.com).");
        }
        
        if (usuarioRepository.emailExists(registroUsuarioPresenter.getEmail())) {
            mensagensErro.add("Este email já está cadastrado! Tente outro.");
        }
        List<String> errosSenha = validador.validar(registroUsuarioPresenter.getSenha());
        mensagensErro.addAll(errosSenha);

        if (!mensagensErro.isEmpty()) {
            JOptionPane.showMessageDialog(null, String.join("\n", mensagensErro), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
        
    }
    
}
