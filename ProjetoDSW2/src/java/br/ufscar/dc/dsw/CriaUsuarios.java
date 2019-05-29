package br.ufscar.dc.dsw;


import br.ufscar.dc.dsw.dao.PapelDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@SessionScoped 
public class CriaUsuarios {
    private boolean criou = false;
    public void geraAdmin(){
        if(!criou){
        try {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            
            Papel papel = new Papel();
            Usuario u = new Usuario();
            u.setAtivo(true);
            u.setEmail("admin@admin");
            u.setSenha(encoder.encode("admin"));
            papel.setEmail("admin@admin");
            papel.setNome("ROLE_ADMIN");
            u.setPapel(papel);
            
            UsuarioDAO dao = new UsuarioDAO();
            dao.save(u);
            criou = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }

}