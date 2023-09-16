package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.formulario_botao_salvar)
        val produtoDao = ProdutoDao()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            produtoDao.adiciona(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = findViewById<EditText>(R.id.formulario_produto_nome)
        val campoDescricao = findViewById<EditText>(R.id.formulario_produto_descricao)
        val campoValor = findViewById<EditText>(R.id.formulario_produto_valor)
        val nome = campoNome.text.toString()
        val descricao = campoDescricao.text.toString()
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }
        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor
        )
    }
}