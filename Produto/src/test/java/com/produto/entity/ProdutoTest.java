package com.produto.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProdutoTest {

	private Produto produto;

	@BeforeEach
	void setUp() {
		// Arrange
		produto = new Produto(1L, "celular", "celular preto", 4.500);
	}

	@Test
	@DisplayName("Testando o getter e setter do campo ID")
	void testId() {
		// Act
		produto.setId(2L);
		// Assert
		assertEquals(2L, produto.getId());
	}

	@Test
	@DisplayName("Testando o getter e setter do campo nome")
	void testNome() {
		// Act
		produto.setNome("celular");
		// Assert
		assertEquals("celular", produto.getNome());
	}

	@Test
	@DisplayName("Testando o getter e setter do campo descricao")
	void testDescricao() {
		// Act
		produto.setDescricao("bom celular");
		// Assert
		assertEquals("bom celular", produto.getDescricao());
	}

	@Test
	@DisplayName("Testando o getter e setter do campo preco")
	void testPreco() {
		// Act
		produto.setPreco(4.500);
		// Assert
		assertEquals(4.500, produto.getPreco());
	}

	@Test
	@DisplayName("Testando o construtor com todos os argumentos")
	void testConstructorAll() {
		// Act
		Produto novoProduto = new Produto(3L,"computador" , "otima marca", 12.000);
		// Assertion
		assertAll("novoProduto", () -> assertEquals(3L, novoProduto.getId()),
				() -> assertEquals("computador", novoProduto.getNome()),
				() -> assertEquals("otima marca", novoProduto.getDescricao()),
				() -> assertEquals(12.000, novoProduto.getPreco()));

	}

}
