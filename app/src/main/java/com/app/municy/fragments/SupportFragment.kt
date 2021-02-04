package com.app.municy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.municy.R
import com.app.municy.databinding.FragmentSupportBinding


class SupportFragment : Fragment() {


    private var mFragmentSupportBinding: FragmentSupportBinding? = null
    //  var bottomSheet: LinearLayout = findViewById(R.id.bottom_sheet_behavior_id)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_support, container, false)
        /* val bottomSheet: LinearLayout = rootView.findViewById(R.id.bottom_sheet_behavior_id)
         val bottomSheetBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)

         bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
             override fun onStateChanged(view: View, i: Int) {
                 // do something when state changes
             }

             override fun onSlide(view: View, v: Float) {
                 // do something when slide happens
             }
         })


 // set the bottom sheet callback state to hidden when you just start your app
         bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN*/

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSupportBinding.bind(view)
        mFragmentSupportBinding = binding


    }


    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        mFragmentSupportBinding = null
        super.onDestroyView()
    }
}