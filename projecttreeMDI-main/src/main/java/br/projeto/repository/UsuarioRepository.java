package br.projeto.repository;

import br.projeto.repository.abstr.IUsuarioRepository;
import br.projeto.db.DB;
import br.projeto.db.DbException;
import br.projeto.model.Subject;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.Observer;
import br.projeto.service.retornar.RetornaUsuarioModelService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements Subject, IUsuarioRepository {
    private Connection conn;
    
    private UsuarioModel usuarioModel;
    private List<Observer> observers;
    
    private RetornaUsuarioModelService serviceUsuario;

    public UsuarioRepository(Connection conn){
        this.conn = conn;
        
        this.usuarioModel = new UsuarioModel();
        this.observers = new ArrayList<>();
        
        this.serviceUsuario = RetornaUsuarioModelService.getInstancia();
    }
    
    @Override
    public List<UsuarioModel> findAll() {
        List<UsuarioModel> usuarioList = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM usuario");
            while(rs.next()){
                UsuarioModel usuario = serviceUsuario.instantiateUsuarioModel(rs);
                usuarioList.add(usuario);
            }
            return usuarioList;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    
    @Override
    public UsuarioModel findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = conn.prepareStatement("SELECT * FROM usuario WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()){
                UsuarioModel usuarioModel = serviceUsuario.instantiateUsuarioModel(rs);
                return usuarioModel;
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return null;
    }
    
 
    public UsuarioModel findByEmailandPassword(String email, String senha) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = conn.prepareStatement("SELECT * FROM usuario WHERE email = ? AND senha = ?");
            ps.setString(1, email);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            
            if(rs.next()){     
                UsuarioModel usuarioModel = serviceUsuario.instantiateUsuarioModel(rs);
                return usuarioModel;
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return null;
    }
    public UsuarioModel findByEmail(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM usuario WHERE email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                return serviceUsuario.instantiateUsuarioModel(rs);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return null;
    } 
    @Override
    public void insert(UsuarioModel usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement("INSERT INTO usuario" +
                                           "(nome, senha, email, formato_log) "+
                                           "VALUES(?,?,?,?)",
                                       Statement.RETURN_GENERATED_KEYS
                                      );
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getFormatoLOG());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0 ){
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    usuario.setId(rs.getInt(1));
                    
                    usuarioModel = usuario;
                    notifyObservers();
                }else{
                    throw new DbException("Unexpected error! No rows affected");
                }
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    
    @Override
    public void update(UsuarioModel usuario) {
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement("UPDATE usuario "+
                                           "SET nome=?, senha=?, email=?, formato_log = ? "+
                                           "WHERE id=?"
                                      );
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getFormatoLOG());
            ps.setInt(5, usuario.getId());

            ps.executeUpdate();
            
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }

    
    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("DELETE FROM usuario WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            throw new DbException(e.getMessage());
            
        }finally {
            DB.closeStatement(ps);
        }
    }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer:observers){
            observer.updateUsuarioModel(usuarioModel);
        }
    }


}
