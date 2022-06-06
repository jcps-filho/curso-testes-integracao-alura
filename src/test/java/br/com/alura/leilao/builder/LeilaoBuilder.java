package br.com.alura.leilao.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

public class LeilaoBuilder {
	
	private String nome;
	private BigDecimal valorInicial;
	private Usuario usuario;
	private LocalDate dataAbertura;
	
	public LeilaoBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}
	public LeilaoBuilder setValorInicial(String valorInicial) {
		this.valorInicial = new BigDecimal(valorInicial);
		return this;
	}
	
	public LeilaoBuilder setUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}
	
	public LeilaoBuilder setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
		return this;
	}
	
	public Leilao build() {
		return new Leilao(nome, valorInicial, dataAbertura, usuario);
	}

}
