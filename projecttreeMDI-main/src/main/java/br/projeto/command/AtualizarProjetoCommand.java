/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.view.EscolhaPlataformaView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class AtualizarProjetoCommand implements Command{
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private AbrirDetalhesProjetoProjetoCommand abrirDetalhesProjetoProjetoCommand;
    private final UsuarioModel usuarioModel;
    
    //private String nomeNo;

    //TESTE
    private Integer projetoId;
    //private List<Integer> perfisIds;
    
    public AtualizarProjetoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel, AbrirDetalhesProjetoProjetoCommand abrirDetalhesProjetoProjetoCommand/*, String nomeNo*/) {
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.usuarioModel = usuarioModel;
        
        this.abrirDetalhesProjetoProjetoCommand = abrirDetalhesProjetoProjetoCommand;
        
        this.projetoId = abrirDetalhesProjetoProjetoCommand.getProjetoId();
        //this.nomeNo = nomeNo;
        
        //this.perfisIds = new ArrayList<>();
    }

    private void getProjetoId() {
        this.projetoId = projetoId;
    }
      
   /*public void adicionarPerfilId(Integer perfilId){
        perfisIds.add(perfilId);
    }*/

    @Override
    public void execute() {
        
        ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter = new ProjetoDeEstimativaPresenter(new EscolhaPlataformaView(), projetoDeEstimativaRepository, perfilProjetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository, perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository,usuarioModel, projetoId);
        projetoDeEstimativaPresenter.setIdProjeto(projetoId);
        /*for(Integer idPerfil: perfisIds){
            projetoDeEstimativaPresenter.addIdPerfil(idPerfil);
        }*/
    }
    
            
}
