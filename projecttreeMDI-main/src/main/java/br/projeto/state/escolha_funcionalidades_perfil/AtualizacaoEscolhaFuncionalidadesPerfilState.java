/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidades_perfil;

import br.projeto.command.MostrarMensagemCommand;
import br.projeto.command.preencher.PreencherTabelaFuncionalidadesPerfilParaUpdateCommand;
import br.projeto.command.salvar.SalvarPerfilProjetoDeEstimativaCommand;
import br.projeto.db.DbException;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author David Potentini
 */
public class AtualizacaoEscolhaFuncionalidadesPerfilState extends AEscolhaFuncionalidadesPerfilState{
    private Integer idPerfil;
    
    public AtualizacaoEscolhaFuncionalidadesPerfilState(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter, Integer idPerfil) {
        super(escolhaFuncionalidadesPerfilPresenter);
        
        this.idPerfil = idPerfil;
        
        configuraTela();
        
        new PreencherTabelaFuncionalidadesPerfilParaUpdateCommand(escolhaFuncionalidadesPerfilPresenter, idPerfil).execute();
    }
    
        @Override
    public void salvar(){
        try{
            new SalvarPerfilProjetoDeEstimativaCommand(escolhaFuncionalidadesPerfilPresenter, idPerfil).execute();
            new MostrarMensagemCommand("PERFIL ATUALIZADO COM SUCESSO!").execute();
            
            escolhaFuncionalidadesPerfilPresenter.getView().dispose();
        } catch (NumberFormatException e) {
            new MostrarMensagemCommand("Número inválido inserido: " + e.getMessage()).execute();
        } catch (IllegalArgumentException e) {
            new MostrarMensagemCommand("Erro de validação: " + e.getMessage()).execute();
        } catch (DbException e) {
            new MostrarMensagemCommand("Erro no banco de dados: " + e.getMessage()).execute();
        } catch (ArrayIndexOutOfBoundsException e){
            new MostrarMensagemCommand("Preencha o conteúdo da linha adicionada: " + e.getMessage()).execute();
        } catch (Exception e) { 
            new MostrarMensagemCommand("Ocorreu um erro inesperado: " + e.getMessage()).execute();
        }        
    }
    
    
    private void configuraTela() {
        escolhaFuncionalidadesPerfilPresenter.getView().setVisible(false);
        escolhaFuncionalidadesPerfilPresenter.getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               salvar();
            }
        });
        
       escolhaFuncionalidadesPerfilPresenter.configurarAdicaoFuncionalidades();
       escolhaFuncionalidadesPerfilPresenter.configurarRemocaoFuncionalidades();
        
       escolhaFuncionalidadesPerfilPresenter.getView().setVisible(true);
    }
    
}
