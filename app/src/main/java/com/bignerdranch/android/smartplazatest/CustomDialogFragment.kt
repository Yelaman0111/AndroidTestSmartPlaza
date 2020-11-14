package com.bignerdranch.android.smartplazatest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_fragment.*

class CustomDialogFragment: DialogFragment() {
    interface Result{
        fun getResult(search: String, highCost: String = "", lowCost: Int, sortName: String)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var sortName = "costAscend"
//        radioGroup.setOnCheckedChangeListener{group, checkedId ->
//            sortName = if(R.id.radio_costDescend == checkedId) "costAscend" else "costDescend "
//            Log.i("SortName", sortName + " " + group.toString() + " " + checkedId )
//        }

        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener {
                    group, checkedId ->

                sortName = if(R.id.radio_costAscend == checkedId) "costAscend" else "costDescend"
                Log.i("SortName", sortName)

            })
        search_btn.setOnClickListener {

            var activity = activity as Result

            var lowCostNum = 0

            if(lowCost.text.isEmpty()) lowCostNum = 0 else lowCostNum = lowCost.text.toString().toInt()

            activity.getResult(search_et.text.toString(), highCost.text.toString() , lowCostNum, sortName  )
            dismiss()
            Log.i("Dialog", "search_btn.setOnClickListener")
        }
        Log.i("Dialog", "onActivityCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Dialog", "onCreateView")
        return inflater.inflate(R.layout.dialog_fragment, container, false)
    }
}