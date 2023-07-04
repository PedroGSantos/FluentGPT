package com.example.fluentgpt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

//val dbHelper = MeuSQLiteOpenHelper(context)
//
//// Inserir o usuário (caso ainda não exista)
//val nomeUsuario = "João"
//val db = dbHelper.writableDatabase
//val usuarioValues = ContentValues()
//usuarioValues.put("nome", nomeUsuario)
//val usuarioId = db.insert("usuario", null, usuarioValues)
//
//// Inserir uma conversa relacionada ao usuário
//val conversaValues = ContentValues()
//conversaValues.put("usuario_id", usuarioId)
//conversaValues.put("mensagem", "Olá, como vai?")
//db.insert("conversas", null, conversaValues)
//
//// Consultar todas as conversas do usuário
//val colunas = arrayOf("mensagem")
//val selection = "usuario_id = ?"
//val selectionArgs = arrayOf(usuarioId.toString())
//val cursor = db.query("conversas", colunas, selection, selectionArgs, null, null, null)
//if (cursor.moveToFirst()) {
//    do {
//        val mensagem = cursor.getString(cursor.getColumnIndex("mensagem"))
//        // Faça algo com a mensagem da conversa
//    } while (cursor.moveToNext())
//}
//cursor.close()


/**
 * A simple [Fragment] subclass.
 * Use the [Routine.newInstance] factory method to
 * create an instance of this fragment.
 */
class Routine : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_routine, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Routine.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Routine().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}