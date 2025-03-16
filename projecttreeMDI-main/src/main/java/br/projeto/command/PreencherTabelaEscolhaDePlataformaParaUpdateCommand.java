/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.PerfilProjetoIntermediariaModel;
import br.projeto.presenter.EscolhaPerfilPresenter;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Potentini
 */
public class PreencherTabelaEscolhaDePlataformaParaUpdateCommand implements Command{
    private final EscolhaPerfilPresenter escolhaPerfilPresenter;
    private final Integer projetoId;
    
    
    public PreencherTabelaEscolhaDePlataformaParaUpdateCommand(EscolhaPerfilPresenter escolhaPerfilPresenter, Integer projetoId) {
        this.escolhaPerfilPresenter = escolhaPerfilPresenter;
        this.projetoId = projetoId;
    }

    @Override
    public void execute() {
        JTable tabela = escolhaPerfilPresenter.getTable();
        
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Selecionar", "Plataforma", "Id"}, 0){
            @Override   
            public Class<?> getColumnClass(int columnIndex){
                return (columnIndex == 0) ? Boolean.class:String.class;//DEFINE A PRIMEIRA COLUNA COMO BOOLEAN, QUE NO CONTEXTO DE UM JTABLE É A MESMA COISA QUE UM CHECKBOX
            }
            
            @Override
            public boolean isCellEditable(int row, int column){
               return column == 0; //SOMENTE A PRIMEIRA COLUNA É EDITAVEL
            }
        };

            List<PerfilProjetoDeEstimativaModel> perfilUsuarioList = escolhaPerfilPresenter.getPerfilProjetoDeEstimativaRepository().findByUser(escolhaPerfilPresenter.getUsuarioModel());

            List<PerfilProjetoIntermediariaModel> perfilProjetoList = escolhaPerfilPresenter.getPerfilProjetoIntermediariaRepository().findByProjeto(projetoId);

            for (PerfilProjetoDeEstimativaModel perfil : perfilUsuarioList) {
                String nomePlataforma = perfil.getNomePerfil();
                Integer idPlataforma = perfil.getId();

                boolean encontrado = false; 
                for (PerfilProjetoIntermediariaModel perfilProjetoIntermediariaModel : perfilProjetoList) {
                    if (idPlataforma.equals(perfilProjetoIntermediariaModel.getIdPerfilProjetoDeEstimativaModel())) {
                        modelo.addRow(new Object[]{true, nomePlataforma, idPlataforma});
                        encontrado = true;
                        break; 
                    }
                }

                if (!encontrado) {
                    modelo.addRow(new Object[]{false, nomePlataforma, idPlataforma});
                }
            }

        tabela.setModel(modelo);
        
    }
    }
    

