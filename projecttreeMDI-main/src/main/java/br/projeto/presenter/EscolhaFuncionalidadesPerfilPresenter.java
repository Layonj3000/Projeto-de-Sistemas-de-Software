package br.projeto.presenter;

import br.projeto.command.AdicionarOpcaoPerfilCommand;
import br.projeto.command.MostrarMensagemCommand;
import br.projeto.command.RemoverOpcaoPerfilCommand;
import br.projeto.model.UsuarioModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.state.escolha_funcionalidades_perfil.AEscolhaFuncionalidadesPerfilState;
import br.projeto.state.escolha_funcionalidades_perfil.AtualizacaoEscolhaFuncionalidadesPerfilState;
import br.projeto.state.escolha_funcionalidades_perfil.InclusaoEscolhaFuncionalidadesPerfilState;
import br.projeto.view.ManterPerfilProjetoDeEstimativaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author David Potentini
 */
public class EscolhaFuncionalidadesPerfilPresenter {
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository; 
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final UsuarioModel usuarioModel;
    private Integer perfilId;

    private ManterPerfilProjetoDeEstimativaView view;
    private AEscolhaFuncionalidadesPerfilState estado;
    
    public EscolhaFuncionalidadesPerfilPresenter(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, UsuarioModel usuarioModel) {
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.view = new ManterPerfilProjetoDeEstimativaView();
        this.usuarioModel = usuarioModel;

    }
    
    public void setEstadoInicial(){
        if(perfilId != null){
            this.estado = new AtualizacaoEscolhaFuncionalidadesPerfilState(this, perfilId);
        }else{
            this.estado = new InclusaoEscolhaFuncionalidadesPerfilState(this); 
        }
    }
    
    public Integer getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }

    
    
    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }
    
    public void salvar(){
        estado.salvar();
    }

    
    public void configurarAdicaoFuncionalidades(){
        view.getBtnAdicionarFuncionalidade().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    new AdicionarOpcaoPerfilCommand(EscolhaFuncionalidadesPerfilPresenter.this).execute();
                }catch(IllegalArgumentException ex){
                    new MostrarMensagemCommand("Erro: " + ex.getMessage()).execute();
                }
            }
        }); 
        
    }
    
    public void configurarRemocaoFuncionalidades(){
        view.getBtnRemoverFuncionalidade().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    new RemoverOpcaoPerfilCommand(EscolhaFuncionalidadesPerfilPresenter.this).execute();
                }catch(IllegalArgumentException ex){
                    new MostrarMensagemCommand("Erro: " + ex.getMessage()).execute();
                }
            }
        });
    }
    
    public void setState(AEscolhaFuncionalidadesPerfilState estado){
        this.estado = estado;
    }

    public ManterPerfilProjetoDeEstimativaView getView() {
        return view;
    }

    public PerfilProjetoDeEstimativaRepository getPerfilProjetoDeEstimativaRepository() {
        return perfilProjetoDeEstimativaRepository;
    }

    public PerfilFuncionalidadesPersonalizadasRepository getPerfilFuncionalidadesPersonalizadasRepository() {
        return perfilFuncionalidadesPersonalizadasRepository;
    }
    
    public String getTxtNomePerfil(){
        return view.getTxtNomePerfil().getText();
    }
    
    public String getTxtTaxaDiariaDesenvolvimento(){
        return view.getTxtTaxaDiariaDesenvolvimento().getText().trim();
    }
    
    public String getTxtTaxaDiariaDesign(){
        return view.getTxtTaxaDiariaDesign().getText().trim();
    }
    
    public String getTxtTaxaDiariaGerenciaProjeto(){
        return view.getTxtTaxaDiariaGerenciaProjeto().getText().trim();
    }
    
    public JButton getBtnConfirmar(){
        return view.getBtnConfirmar();
    }
    
    
    public JTable getTable(){
        return view.getTable();
    }
    
}
