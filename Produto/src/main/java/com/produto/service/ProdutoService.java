package com.produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produto.entity.Produto;
import com.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {
	private final ProdutoRepository produtoRepository;
	
	@Autowired
	
	public ProdutoService (ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	public List<Produto> buscaTodosProduto(){
		return produtoRepository.findAll();
	}
	public Produto salvarProduto(Produto produto) {
		return produtoRepository.save(produto);		
	}
	
	public Optional<Produto> buscarProdutoPorId(long id){
		return produtoRepository.findById(id);
	}
	public Produto atualizarProduto(Produto Produto) {
		if(produtoRepository.existsById(Produto.getId())) {
			return produtoRepository.save(Produto);
		}else {
			throw new RuntimeException("Produto não encontrado");
		}
	}
	public boolean deletarProduto(Long id) {
		Optional <Produto> existeProduto = produtoRepository.findById(id);
		if (existeProduto.isPresent()) {
			produtoRepository.deleteById(id);
			return true;
		}
		return false;
	}
	public List<Produto> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}


	
}


