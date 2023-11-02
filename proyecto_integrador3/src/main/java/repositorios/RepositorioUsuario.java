package repositorios;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entidades.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, String>{
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	public Usuario buscarPorEmail(@Param("email") String email); // este busca un usuario por mail
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Optional<Usuario> buscarPorEmailOptional(@Param("email") String email); // este busca un usuario por mail pero la opcion de que el resultado puede estar vacio o null

    @Query("SELECT u FROM Usuario u WHERE u.dni = :dni")
    public Usuario buscarPorDni(@Param("dni") String dni);
    
    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    public Usuario buscarPorId(@Param("id") String id);
    
    @Query("SELECT u FROM Usuario u WHERE u.dni = :dni")
    public Optional<Usuario> buscarPorDniOptional(@Param("dni") String dni);
    
    @Query("SELECT u FROM Usuario u")
    public List<Usuario> buscarUsuarios(); 

}
