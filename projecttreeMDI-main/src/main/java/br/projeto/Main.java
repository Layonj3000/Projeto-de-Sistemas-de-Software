package br.projeto;

import br.projeto.db.DB;
import br.projeto.enums.SimNao;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.PrincipalPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository_factory.RepositoryFactory;
import java.sql.Date;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        
        
        RepositoryFactory factory = RepositoryFactory.escolherClasseFabricada(ProjetoDeEstimativaRepository.class);
        ProjetoDeEstimativaRepository projetoDeEstimativaRepository = factory.createRepository();
//
        RepositoryFactory factory1 = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
        UsuarioRepository usuarioRepository = factory1.createRepository();
        UsuarioModel usuarioModel = new UsuarioModel(1, "ana", "1234", "ana@gmail.com");
        UsuarioModel usuarioModel2 = new UsuarioModel(2, "david", "1234", "david@gmail.com");
//        usuarioRepository.insert(usuarioModel);
//        usuarioRepository.insert(usuarioModel2);
        System.out.println(usuarioRepository.findAll());

        ProjetoDeEstimativaModel projetoDeEstimativaModel = new ProjetoDeEstimativaModel(1, usuarioModel, 0, null, new Date(System.currentTimeMillis()), "Projeto Teste UM", 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0, 0.0, 0.0, 0.0);
        ProjetoDeEstimativaModel projetoDeEstimativaModel2 = new ProjetoDeEstimativaModel(2, usuarioModel2, 0, null, new Date(System.currentTimeMillis()), "Projeto Teste DOIS", 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0, 0.0, 0.0, 0.0);
//        projetoDeEstimativaRepository.insert(projetoDeEstimativaModel);
//        projetoDeEstimativaRepository.insert(projetoDeEstimativaModel2);
//        projetoDeEstimativaRepository.deleteById(3);
        //System.out.println(projetoDeEstimativaRepository.findAll());
//
//
        RepositoryFactory factory2 = RepositoryFactory.escolherClasseFabricada(PerfilProjetoDeEstimativaRepository.class);
        PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository = factory2.createRepository();
//
        PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = new PerfilProjetoDeEstimativaModel(
            1, usuarioModel, "Perfil Padrão",
            10, 20, 30, 5, 15, 25, // Pequeno, Médio, Grande, MVP, Básico, Profissional
            10, 40, 15, 20, 50, 20, 10, 10, 10, 10,  // Cadastro por redes sociais
            10, 40, 15, 20, 50, 20, 10, 10, 10, 10,  // Painel, Feed, Uploads, Perfis de Usuário...
            10, 40, 15, 20, 50, 20, 10, 10, 10, 10,  // Pesquisas, Mapa, Reservas, Mensagens...
            10, 40, 15, 20, 50, 20, 10, 10, 10, 10,  // Integrações, Notificações, Pagamentos...
            10, 40, 15, 20, 50, 20, 10, 10, 10, 10,  // Moderação, Monitoramento, Segurança...
            10, 40, 15, 20, 50, 20, 10, 10, 10, 10,        // Funcionalidades Avançadas
            500.00, 800.00, 1200.00, new Date(System.currentTimeMillis())        // Taxas diárias (Design, Gestão, Desenvolvimento)
        );
//        perfilProjetoDeEstimativaModel.setId(2);
//        perfilProjetoDeEstimativaModel.setUsuarioModel(usuarioModel);
//        perfilProjetoDeEstimativaModel.setNomePerfil("IOS");
//        perfilProjetoDeEstimativaModel.setPequeno(70);
//        perfilProjetoDeEstimativaRepository.insert(perfilProjetoDeEstimativaModel);
        //System.out.println(perfilProjetoDeEstimativaRepository.findAll());
//        System.out.println(perfilProjetoDeEstimativaRepository.findByProjetoEstimativa(projetoDeEstimativaModel2));

        RepositoryFactory factory3 = RepositoryFactory.escolherClasseFabricada(PerfilProjetoIntermediariaRepository.class);
        PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository = factory3.createRepository();
//        perfilProjetoIntermediariaRepository.insert(projetoDeEstimativaModel, perfilProjetoDeEstimativaModel);
//        perfilProjetoIntermediariaRepository.insert(projetoDeEstimativaModel2, perfilProjetoDeEstimativaModel);
//        perfilProjetoIntermediariaRepository.deleteById(1,1);

//
      // perfilProjetoIntermediariaRepository.insert(projetoDeEstimativaModel, perfilProjetoDeEstimativaModel);
      //  perfilProjetoIntermediariaRepository.insert(projetoDeEstimativaModel2, perfilProjetoDeEstimativaModel);
//
        //System.out.println(perfilProjetoIntermediariaRepository.findAll());
        
        RepositoryFactory factory4 = RepositoryFactory.escolherClasseFabricada(ProjetoFuncionalidadesPersonalizadasRepository.class);
        ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository = factory4.createRepository();
        ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = new ProjetosFuncionalidadesPersonalizadasModel(1, "Funcionalidade EXTRA", SimNao.SIM, projetoDeEstimativaModel);
//        projetoFuncionalidadesPersonalizadasRepository.insert(projetosFuncionalidadesPersonalizadasModel);
        
        
        RepositoryFactory factory5 = RepositoryFactory.escolherClasseFabricada(PerfilFuncionalidadesPersonalizadasRepository.class);
        PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository = factory5.createRepository();
        PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel = new PerfilFuncionalidadesPersonalizadasModel(1, "Funcionalidade EXTRA", 40, perfilProjetoDeEstimativaModel);
//        perfilFuncionalidadesPersonalizadasRepository.deleteById(1);
//        perfilFuncionalidadesPersonalizadasRepository.insert(perfilFuncionalidadesPersonalizadasModel);
        //System.out.println(perfilFuncionalidadesPersonalizadasRepository.findAll());
        
        SwingUtilities.invokeLater(() -> {
            PrincipalPresenter presenter = new PrincipalPresenter(new ProjetoRepositoryMock(), projetoDeEstimativaRepository, perfilProjetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository, perfilFuncionalidadesPersonalizadasRepository, usuarioModel2);
            WindowManager.getInstance().initialize(presenter);
        });
    }
}
