package com.aim2u.week_8_udacoding_room_persistance.ui.books

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.aim2u.week_8_udacoding_room_persistance.R
import com.aim2u.week_8_udacoding_room_persistance.adapter.DataAdapter
import com.aim2u.week_8_udacoding_room_persistance.local.DatabaseBook
import com.aim2u.week_8_udacoding_room_persistance.local.LocalBook
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_add_book.view.*
import kotlinx.android.synthetic.main.fragment_books.*

class BookFragment : Fragment() {

//    private lateinit var bookViewModel: BookViewModel

    private lateinit var databaseBook: DatabaseBook
    private lateinit var bookViewModel: BookViewModel
    private var dialogView: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseBook = DatabaseBook.getInstance(requireContext())!!
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_books, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookViewModel.bookList.observe(viewLifecycleOwner) {
            showBooks(it)
        }
        bookViewModel.showBooks(databaseBook) {
            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
        }

        fab.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_add_book, null)
        dialog.setView(view)

        dialogView = dialog.create()
        dialogView?.show()

        view.buttonSaveBook.setOnClickListener {
            if (view.editTextBookName.text.isNotEmpty() &&
                view.editTextBookAuthor.text.isNotEmpty() &&
                view.editTextReleasedYear.text.isNotEmpty()
            ) {
                bookViewModel.addBook(databaseBook,
                    LocalBook(
                        null,
                        view.editTextBookName.text.toString(),
                        view.editTextBookAuthor.text.toString(),
                        view.editTextReleasedYear.text.toString()
                    )
                ){
                    Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                }
                dialogView?.dismiss()
                bookViewModel.showBooks(databaseBook){
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Semua Field Harus diisi", Toast.LENGTH_SHORT).show()
            }
        }

        view.button_back.setOnClickListener {
            dialogView?.dismiss()
        }
    }

    private fun showUpdateDialog(item: LocalBook?) {
        val dialog = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_add_book, null)
        dialog.setView(view)

        view.editTextBookName.setText(item?.book_name)
        view.editTextBookAuthor.setText(item?.author)
        view.editTextReleasedYear.setText(item?.released_year)
        view.buttonSaveBook.text = "Update"
        dialogView = dialog.create()
        dialogView?.show()

        view.buttonSaveBook.setOnClickListener {
            if (view.editTextBookName.text.isNotEmpty() &&
                view.editTextBookAuthor.text.isNotEmpty() &&
                view.editTextReleasedYear.text.isNotEmpty()
            ) {
                bookViewModel.updateBook(
                    databaseBook,
                    LocalBook(
                        item?.id,
                        view.editTextBookName.text.toString(),
                        view.editTextBookAuthor.text.toString(),
                        view.editTextReleasedYear.text.toString()
                    )
                ){
                    Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                }
                dialogView?.dismiss()
                bookViewModel.showBooks(databaseBook){
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Semua Field Harus diisi", Toast.LENGTH_SHORT).show()
            }
        }

        view.button_back.setOnClickListener {
            dialogView?.dismiss()
        }
    }

    private fun showBooks(item: List<LocalBook>?) {
        rv_main.adapter = DataAdapter(item, object : DataAdapter.OnClickListener {
            override fun update(item: LocalBook?) {
                showUpdateDialog(item)
            }

            override fun hapus(item: LocalBook?) {
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("Hapus")
                    setMessage("Yakin hapus buku?")
                    setCancelable(false)
                    setPositiveButton("Yakin") { dialogInterface, i ->
                        bookViewModel.deleteBook(databaseBook, item) {
                            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                        }
                        bookViewModel.showBooks(databaseBook){
                            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                    setNegativeButton("Batal") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }

        })
    }
}