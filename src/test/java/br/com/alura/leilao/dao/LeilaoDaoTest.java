package br.com.alura.leilao.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;

class LeilaoDaoTest {

	private LeilaoDao dao;
	private EntityManager em;
	
	@BeforeEach
	public void beforeEach() {
		this.em = JPAUtil.getEntityManager();
		this.dao = new LeilaoDao(em);
		em.getTransaction().begin();
	}
	
	@AfterEach
	public void afterEach() {
		em.getTransaction().rollback();
	}
	
	@Test
	void deveSalvarLeilao() {
		Leilao leilao = criarLeilao();
		
		leilao = this.dao.salvar(leilao);
		
		Leilao salvo = this.dao.buscarPorId(leilao.getId());
		assertNotNull(salvo);
	}
	
	@Test
	void deveAtualizarLeilao() {
		Leilao leilao = criarLeilao();
		
		leilao = this.dao.salvar(leilao);
		
		leilao.setNome("Celular");
		leilao.setValorInicial(new BigDecimal("400"));
		
		leilao = this.dao.salvar(leilao);
		
		Leilao salvo = this.dao.buscarPorId(leilao.getId());
		assertEquals("Celular", salvo.getNome());
		assertEquals(new BigDecimal("400"), salvo.getValorInicial());
	}
	
	private Leilao criarLeilao() {
		Usuario usuario = criarUsuario();
		Leilao leilao = new Leilao("Computador Gamer", new BigDecimal("10.000"), LocalDate.now(), usuario);
		em.persist(leilao);
		return leilao;
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
		em.persist(usuario);
		return usuario;
	}

}
