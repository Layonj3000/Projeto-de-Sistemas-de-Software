/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command.abrir;

import br.projeto.command.Command;
import br.projeto.presenter.DetalhePerfilPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.view.DetalhePerfilView;
import javax.swing.JDesktopPane;

/**
 *
 * @author David Potentini
 */
public class AbrirDetalhesPerfilCommand implements Command{
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final JDesktopPane desktop;
    private Integer perfilId;
    private String perfilNome;
    
    public AbrirDetalhesPerfilCommand(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, JDesktopPane desktop){
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.desktop = desktop;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }

    public void setPerfilNome(String perfilNome) {
        this.perfilNome = perfilNome;
    }
    
    @Override
    public void execute() {
        if(perfilId ==null){
            throw new IllegalStateException("O nome do perfil não foi definido para este comando.");   
        }
        
        String tituloJanela = "Detalhes do Perfil: " + perfilProjetoDeEstimativaRepository.findById(perfilId).getNomePerfil();
        WindowManager windowManager = WindowManager.getInstance();
        
        if (windowManager.isFrameAberto(tituloJanela)) {
            windowManager.bringToFront(tituloJanela);
        } else {
            DetalhePerfilView detalheView = new DetalhePerfilView();
            detalheView.setTitle(tituloJanela);
            new DetalhePerfilPresenter(detalheView/*, repository*/, perfilProjetoDeEstimativaRepository, perfilFuncionalidadesPersonalizadasRepository, perfilNome, perfilId);
            desktop.add(detalheView);
            detalheView.setVisible(true);
            try {
                detalheView.setMaximum(true);
            } catch (Exception ignored) {
            }
        }
    }
    
}
