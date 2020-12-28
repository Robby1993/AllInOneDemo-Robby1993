package com.app.municy.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.municy.HomeActivity
import com.app.municy.R
import com.app.municy.activities.EmailActivity
import com.app.municy.databinding.FragmentResourceBinding

class ResourceFragment : Fragment() {
    private var mFragmentResourceBinding: FragmentResourceBinding? = null
    private val TAG = ResourceFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_resource, container, false)
        /*  val bundle = Bundle()
          bundle.putStringArrayList("files", files)
          Navigation.findNavController(requireView()).navigate(R.id.actionToUpload, bundle)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentResourceBinding.bind(view)
        mFragmentResourceBinding = binding
        binding.emails.setOnClickListener {
            //  Toast.makeText(requireContext(), "Email", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), EmailActivity::class.java))
        }

    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        mFragmentResourceBinding = null
        super.onDestroyView()
    }
}