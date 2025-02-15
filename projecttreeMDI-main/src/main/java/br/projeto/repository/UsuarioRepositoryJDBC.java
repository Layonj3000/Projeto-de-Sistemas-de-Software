package br.projeto.repository;

import br.projeto.db.DB;
import br.projeto.db.DbException;
import br.projeto.model.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryJDBC implements IUsuarioRepository {
    private Connection conn;

    public UsuarioRepositoryJDBC(Connection conn){
        this.conn = conn;
    }
    //VERIFICAR A POSSIVEL CRIACAO DE UMA CLASSE DE INSTANCIACAO OBJETOS DO TIPO UuarioModel
    @Override
    public List<UsuarioModel> findAll() {
        List<UsuarioModel> usuarioList = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM usuario");
            while(rs.next()){
                UsuarioModel usuario = instantiateUsuarioModel(rs);
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
                UsuarioModel usuarioModel = instantiateUsuarioModel(rs);
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

    @Override
    public void insert(UsuarioModel usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement("INSERT INTO usuario" +
                                           "(nome, senha, email) "+
                                           "VALUES(?,?,?)",
                                       Statement.RETURN_GENERATED_KEYS
                                      );
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getEmail());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0 ){
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    usuario.setId(rs.getInt(1));
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
                                           "SET nome=?, senha=?, email=? "+
                                           "WHERE id=?"
                                      );
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getEmail());
            ps.setInt(4, usuario.getId());

            ps.executeUpdate();
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
    //FICAR ATENTO NO COMPORTAMENTO DAS DEMAIS TABELAS COM LIGAÇÃO COM USUARIO AO REALIZAR O DELETE
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

    private UsuarioModel instantiateUsuarioModel(ResultSet rs) throws SQLException {
        UsuarioModel usuarioModel = new UsuarioModel(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"), rs.getString("email"));
        return usuarioModel;
    }

}
