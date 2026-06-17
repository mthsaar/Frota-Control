package com.saar.frotacontrol

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AlertDialog
import com.saar.frotacontrol.databinding.ActivityMainBinding
import com.saar.frotacontrol.databinding.DialogNovoCadastroBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btListar.setOnClickListener {

        }

        binding.btNovoCadastro.setOnClickListener {
            showNovoCadastroDialog()
        }

        binding.btPesquisar.setOnClickListener {

        }

    }

    private fun showNovoCadastroDialog() {
        val dialogBinding = DialogNovoCadastroBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogBinding.root)

        val dialog = builder.create()

        dialogBinding.btSalvar.setOnClickListener {
            // Lógica para salvar os dados
            val veiculo = dialogBinding.etVeiculo.text.toString()
            val motorista = dialogBinding.etMotorista.text.toString()
            val passageiro = dialogBinding.etPassageiro1.text.toString()

            // Por enquanto, apenas fecha o diálogo
            dialog.dismiss()
        }

        dialogBinding.btCancelar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}