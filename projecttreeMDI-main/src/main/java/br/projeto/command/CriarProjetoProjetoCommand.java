package br.projeto.command;

import br.projeto.model.Projeto;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.service.CriarProjetoMock;
import br.projeto.view.EscolhaPlataformaView;

import javax.swing.*;
import java.util.Optional;

public class CriarProjetoProjetoCommand implements Command {
    private final ProjetoRepositoryMock repository;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private final JDesktopPane desktop;
    private final UsuarioModel usuarioModel;
    //private final CriarProjetoMock criarProjetoMock;

    public CriarProjetoProjetoCommand(ProjetoRepositoryMock repository,ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository,ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, JDesktopPane desktop, UsuarioModel usuarioModel) {
        this.repository = repository;
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository =perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.desktop = desktop;
        this.usuarioModel = usuarioModel;
        //this.criarProjetoMock = new CriarProjetoMock(repository);//LEMBRAR DE EXCLUIR CLASSE
    }

    
    @Override
    public void execute() {
        //IMPLEMENTAR NOVA LOGICA DE CRIACAO
        new ProjetoDeEstimativaPresenter(new EscolhaPlataformaView(), projetoDeEstimativaRepository, perfilProjetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository, perfilFuncionalidadesPersonalizadasRepository,usuarioModel);
    }
/*    @Override
    public void execute() {
        Optional<Projeto> projetoCriado = criarProjetoMock.criarProjetoAleatorio();

        projetoCriado.ifPresentOrElse(
                projeto -> {
                    repository.adicionarProjeto(
                            projeto.getNome(),
                            projeto.getCriador(),
                            projeto.getDataCriacao(),
                            projeto.getStatus(),
                            projeto.isCompartilhado(),
                            projeto.getCompartilhadoPor(),
                            projeto.getPerfis(),
                            projeto.getFuncionalidadesEscolhidas()
                    );
                    new MostrarMensagemProjetoCommand("Projeto \"" + projeto.getNome() + "\" criado com sucesso!").execute();
                },
                () -> new MostrarMensagemProjetoCommand("Falha ao criar o projeto.").execute());
    }*/
    
    /*public void executeTeste(){
        Optional<ProjetoDeEstimativaModel> = projetoDeEstimativaRepository.findAll();
    }*/


}
