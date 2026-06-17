package com.saar.frotacontrol

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AlertDialog
import com.saar.frotacontrol.databinding.ActivityMainBinding
import com.saar.frotacontrol.databinding.DialogNovoCadastroBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var banco: SQLiteDatabase

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

        banco = SQLiteDatabase.openOrCreateDatabase(this.getDatabasePath("banco.db"), null)

        banco.execSQL("CREATE TABLE IF NOT EXISTS cadastro( _id INTEGER PRIMARY KEY AUTOINCREMENT, motorista TEXT, passageiro TEXT )")


        binding.btListar.setOnClickListener {
            listar()
        }

        binding.btNovoCadastro.setOnClickListener {
            showNovoCadastroDialog()
        }

        binding.btPesquisar.setOnClickListener {
            pesquisar()
        }

    }

    private fun listar() {
        TODO("Not yet implemented")
    }

    private fun pesquisar() {
        TODO("Not yet implemented")
    }

    private fun editar(motorista: String, passageiro: String, id: String) {
        val registro = ContentValues()
        registro.put("motorista", motorista)
        registro.put("passageiro", passageiro)
        banco.update("cadastro", registro,
            "_id = $binding.etVeiculo.text.toString()", null)

        Toast.makeText(this, "Salvo!", Toast.LENGTH_LONG).show()
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
            editar(motorista, passageiro, veiculo)
            salvar(motorista, passageiro)
            // Por enquanto, apenas fecha o diálogo
            dialog.dismiss()
        }

        dialogBinding.btCancelar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun salvar(motorista: String, passageiro: String) {
        val registro = ContentValues()
        registro.put("motorista", motorista)
        registro.put("passageiro", passageiro)
        banco.insert("cadastro", null, registro)

        Toast.makeText(this, "Salvo!", Toast.LENGTH_LONG).show()
    }
}