package com.example.libraryapp.presentation.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.libraryapp.R
import com.example.libraryapp.databinding.FragmentBookDetailBinding
import com.example.libraryapp.presentation.adapter.BookAdapter
import com.example.libraryapp.presentation.viewmodel.BookDetailViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [BookDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class BookDetailFragment : Fragment(R.layout.fragment_book_detail) {
    private var _binding: FragmentBookDetailBinding ?= null
    private val binding get() = _binding!!

    private lateinit var bookAdapter: BookAdapter

    private val viewModel: BookDetailViewModel by viewModels()

    private var bookId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookId = it.getInt("bookId")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBookDetailBinding.bind(view)
        viewModel.loadBook(bookId!!).observe(viewLifecycleOwner){
            book ->
            book?.let {
                binding.apply {
                    titleText.text = it.title
                    authorText.text = it.author
                    yearText.text = it.year.toString()
                    descriptionText.text = it.description
                    availabilityChip.apply {
                        text = if (book.isAvailable) "Disponible" else "Agotado"
                        chipBackgroundColor = ColorStateList.valueOf(
                            context.getColor(
                                if (book.isAvailable) R.color.available
                                else R.color.checked_out
                            )
                        )
                    }
                }
            }
        }

        Log.d("BookDetailFragment", "onViewCreated: $bookId")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookingDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            BookDetailFragment()
    }
}