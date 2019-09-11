package com.pd.shaadi.view


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.pd.shaadi.R


/**
 * A simple [Fragment] subclass.
 */
class CreateDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (getArguments() != null) {
            if (getArguments()!!.getBoolean("notAlertDialog")) {
                return super.onCreateDialog(savedInstanceState)
            }
        }
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.activity!!)
        builder.setTitle("No Internet Connection")
        builder.setMessage("Please Check your internet connection. Showing previously fetched data.")

        builder.setPositiveButton("Ok",
            { dialog, which -> dismiss() })


        return builder.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_dialog, container, false)
    }


}
