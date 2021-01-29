package com.baiana.imc_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        //codigo para o calculo dinamico do IMC
        alturaEDT?.doAfterTextChanged { text ->
            calcularIMC(pesoEDT?.text.toString(), text.toString())
        }

        pesoEDT?.doOnTextChanged { peso, _, _, _ ->
            calcularIMC(peso.toString(), alturaEDT?.text.toString())
        }
        calcularBTN?.setOnClickListener {
            exibirFaixaDePeso()
        }
    }


    private fun calcularIMC(peso: String, altura: String): Float? {
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()

        return if (peso != null && altura != null) {
            val imc = peso / (altura * altura)
            titleTXT.text = getString(R.string.imc_resultado).format(imc)
            imc
        } else {
            null
        }
    }

    private fun abrirNovaTelaComResultados(imc: String, faixa: String) {
        val result = Intent(this, ResultActivity::class.java)
        result.putExtra(ResultActivity.IMC_ID, imc)
        result.putExtra(ResultActivity.FAIXA_DE_PESO, faixa)
        startActivity(result)
    }

    private fun exibirFaixaDePeso() {
        val peso = pesoEDT?.text.toString()
        val altura = alturaEDT?.text.toString()
        val imc = calcularIMC(peso, altura)

        imc?.let {
            val faixa: String = when (it) {
                in 18.5..24.9 -> {
                    getString(R.string.peso_normal)
                }
                in 25f..29f -> {
                    getString(R.string.sobrepeso)
                }
                in 30f..34.9f -> {
                    getString(R.string.obesidade_1)
                }
                in 35f..39f -> {
                    getString(R.string.obesidade_2)
                }
                else -> {
                    if (it < 18.5) {
                        getString(R.string.abaixo)
                    } else if (it > 40) {
                        getString(R.string.obesidade_3)
                    }
                    getString(R.string.indefinido)
                }

            }

            abrirNovaTelaComResultados("%.2f".format(it), faixa)
        }

    }
}