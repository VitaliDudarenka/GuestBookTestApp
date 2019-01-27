package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.registration

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.gmail.dudarenka.vitali.guestbooktestapp.R
import com.gmail.dudarenka.vitali.guestbooktestapp.databinding.FragmentRegistrationBinding
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookRouter
import android.widget.Button
import android.provider.DocumentsContract
import android.R.attr.data
import android.widget.ImageView


class RegistrationFragment : BaseMvvmFragment<RegistrationViewModel, GuestBookRouter, FragmentRegistrationBinding>() {
    private val SELECT_PICTURE = 1
    private var selectedImagePath: String? = null


    companion object {
        fun getInstance(): RegistrationFragment {
            return RegistrationFragment()
        }
    }

    override fun provideViewModel(): RegistrationViewModel {
        return ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_registration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view.findViewById(R.id.imageSetButton) as? Button)!!.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,
                    "Select Picture"), SELECT_PICTURE)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                val selectedImageUri = data!!.data
                selectedImagePath = getPath(selectedImageUri)
                viewModel.imagePath.set(selectedImagePath)
                val imageView= view!!.findViewById<ImageView>(R.id.imageView)
                imageView.setImageURI(selectedImageUri)
            }
        }
    }

    private fun getPath(uri: Uri?): String {
        val wholeID = DocumentsContract.getDocumentId(uri)
        val id = wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        val column = arrayOf(MediaStore.Images.Media.DATA)
        val sel = MediaStore.Images.Media._ID + "=?"
        val cursor = activity!!.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, arrayOf(id), null)
        var filePath = ""
        val columnIndex = cursor.getColumnIndex(column[0])
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex)
        }
        cursor.close()
        return filePath
    }

}