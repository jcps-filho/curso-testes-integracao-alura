package br.com.alura.leilao.builder;

import br.com.alura.leilao.model.Usuario;

public class UsuarioBuilder {
	
	private String nome;
	private String senha;
	private String email;
	
	public UsuarioBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public UsuarioBuilder setSenha(String senha) {
		this.senha = senha;
		return this;
	}
	
	public UsuarioBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public Usuario build() {
		return new Usuario(nome, email, senha);
	}

}
