package br.com.alura.leilao.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;

class UsuarioDaoTest {
	
	private UsuarioDao dao;
	private EntityManager em;
	
	@BeforeEach
	public void beforeEach() {
		this.em = JPAUtil.getEntityManager();
		this.dao = new UsuarioDao(em);
		em.getTransaction().begin();
	}
	
	@AfterEach
	public void afterEach() {
		em.getTransaction().rollback();
	}
	
	@Test
	void deveEncontrarUsuarioCadastrado() {
		Usuario usuario = criarUsuario();
		
		Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
		assertNotNull(encontrado);
	}
	
	@Test
	void naoDeveEncontrarUsuarioCadastrado() {
		criarUsuario();
		assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("beltrano"));
	}
	
	@Test
	void deveExcluirUsuarioCadastrado() {
		Usuario usuario = criarUsuario();
		this.dao.deletar(usuario);
		
		assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername(usuario.getNome()));
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
		em.persist(usuario);
		return usuario;
	}

}
