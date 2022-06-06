package br.com.alura.leilao.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.builder.UsuarioBuilder;
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
		Usuario usuario = new UsuarioBuilder()
				.setNome("fulano")
				.setEmail("fulano@email.com")
				.setSenha("12345678")
				.build();
		
		em.persist(usuario);
		
		Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
		assertNotNull(encontrado);
	}
	
	@Test
	void naoDeveEncontrarUsuarioCadastrado() {
		Usuario usuario = new UsuarioBuilder()
				.setNome("fulano")
				.setEmail("fulano@email.com")
				.setSenha("12345678")
				.build();
		
		em.persist(usuario);
		
		assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("beltrano"));
	}
	
	@Test
	void deveExcluirUsuarioCadastrado() {
		Usuario usuario = new UsuarioBuilder()
				.setNome("fulano")
				.setEmail("fulano@email.com")
				.setSenha("12345678")
				.build();
		
		em.persist(usuario);
		
		this.dao.deletar(usuario);
		
		assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername(usuario.getNome()));
	}

}
