package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entidades.Cliente;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, String>{
	    @Query("SELECT COUNT(cl) FROM Cliente cl")
	    public Integer contarClientes();
	
}
