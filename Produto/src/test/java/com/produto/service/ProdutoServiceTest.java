package com.produto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.produto.entity.Produto;
import com.produto.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@SpringBootTest // Carrega o contexto completo do Spring para testes
@Transactional // Garante que as operações no banco de dados serão revertidas após cada teste
class ProdutoServiceTest {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@BeforeEach
	void setUp() {
		produtoRepository.deleteAll(); // Limpa o banco de dados antes de cada teste

	}

	@DisplayName("Testando salvar ")
	@Test
	void testSalvarProduto() {
		Produto produto = new Produto(null, "Garrafa", "cor roxa", 100.00);

		Produto resultado = produtoService.salvarProduto(produto);

		assertNotNull(resultado);
		assertEquals("Garrafa", resultado.getNome());
		assertEquals("cor roxa", resultado.getDescricao());
		assertEquals(100.00, resultado.getPreco());
		assertTrue(resultado.getId() > 0);
	}

	@DisplayName("Testando listar os Produtos")
	@Test
	void testListarTodos() {
		Produto produto1 = new Produto(null, "Garrafa", "cor roxa", 100.00);
		Produto produto2 = new Produto(null, "mochila", "cor verde", 150.00);

		produtoService.salvarProduto(produto1);
		produtoService.salvarProduto(produto2);

		List<Produto> resultado = produtoService.listarTodos();

		assertNotNull(resultado);
		assertEquals(2, resultado.size());
	}

	@DisplayName("Testando buscar Produto por ID")
	@Test
	void testBuscarPorId() {
		Produto produto = new Produto(null, "Garrafa", "cor roxa", 100.00);

		Produto salvo = produtoService.salvarProduto(produto);
		Optional<Produto> resultado = produtoService.buscarProdutoPorId(salvo.getId());

		assertTrue(resultado.isPresent());
		assertEquals("Garrafa", resultado.get().getNome());
		assertEquals("cor roxa", resultado.get().getDescricao());
		assertEquals(100.00, resultado.get().getPreco());

	}

	@DisplayName("Testando atualizar Hóspede por ID")
	@Test
	void testAtualizarProduto() {
		Produto produto = new Produto(null, "Garrafa", "cor roxa", 100.00);
		Produto salvo = produtoService.salvarProduto(produto);
		
		salvo.setNome("Garrafa");
		salvo.setDescricao("cor roxa");
		salvo.setPreco(100.00);

		Produto atualizado = produtoService.atualizarProduto(salvo);

		assertNotNull(atualizado);
		assertEquals("Garrafa", atualizado.getNome());
		assertEquals("cor roxa", atualizado.getDescricao());
		assertEquals(100.00, atualizado.getPreco());

	}

	@DisplayName("Testando deletar Produto por ID")
	@Test
	void testDeletarProduto() {
		Produto produto = new Produto(null, "Garrafa", "cor roxa", 100.00);

		Produto salvo = produtoService.salvarProduto(produto);
		produtoService.deletarProduto(salvo.getId());

		Optional<Produto> resultado = produtoService.buscarProdutoPorId(salvo.getId());

		assertTrue(resultado.isEmpty());
	}

}
