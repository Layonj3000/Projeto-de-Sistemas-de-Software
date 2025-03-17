package br.projeto.command;

import br.projeto.presenter.ExportarPresenter;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;

public class AbrirTelaExportacaoCommand implements Command {
    private Integer idProjeto;
    private String nomeProjeto;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    
    public AbrirTelaExportacaoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository,PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository) {
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;  
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }
    
    @Override
    public void execute() {
        ExportarPresenter exportarPresenter = new ExportarPresenter(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository,idProjeto, nomeProjeto);
    }    
}