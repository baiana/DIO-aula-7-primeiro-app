package com.baiana.imc_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setListeners()
        Log.w("lifecycle","CREATE - estou criando a tela")
    }

    private fun setListeners(){
        closeBTN?.setOnClickListener {
            onBackPressed()
            //retorna para a tela anterior
        }

        //exibe os valores passados pela tela anterior
        resultTXT?.text = intent.getStringExtra(IMC_ID)
        resultTextTXT?.text = intent.getStringExtra(FAIXA_DE_PESO)
    }

    override fun onStart() {
        super.onStart()
        Log.w("lifecycle","START - deixei a tela visível para você")
    }

    override fun onResume() {
        super.onResume()
        Log.w("lifecycle","RESUME - agora você pode interagir com a tela")

    }
    override fun onPause() {
        super.onPause()
        Log.w("lifecycle","PAUSE - a tela perdeu o foco, você não pode mais interagir")
    }

    override fun onStop() {
        Log.w("lifecycle","STOP - a tela não está mais visível mas ainda existe")
        super.onStop()
    }
    override fun onRestart() {
        super.onRestart()
        Log.w("lifecycle"," \nRESTART  - a tela está retomando o foco ")
    }

    override fun onDestroy() {
        Log.w("lifecycle","\nDESTROY  - oh não! a tela foi destruída")
        super.onDestroy()
    }

    companion object{
        const val FAIXA_DE_PESO = "Peso"
        const val IMC_ID = "imc"
    }
}