package com.app.municy.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.municy.R
import com.app.municy.activities.EmailActivity
import com.app.municy.activities.MapActivity
import com.app.municy.databinding.FragmentResourceBinding
import com.app.municy.model.Data
import com.app.municy.utilities.Constants
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.ImagePicker.Companion.getError
import com.github.dhaval2404.imagepicker.ImagePicker.Companion.getFile
import com.github.dhaval2404.imagepicker.ImagePicker.Companion.getFilePath
import com.github.dhaval2404.imagepicker.ImagePicker.Companion.with
import com.google.gson.Gson
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.ByteArrayOutputStream

class ResourceFragment : Fragment() {
    private var mFragmentResourceBinding: FragmentResourceBinding? = null
    private val TAG = ResourceFragment::class.java.simpleName

    private lateinit var mPhoto: ByteArray
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
        binding.tvPhone.setOnClickListener {
            startActivity(Intent(requireContext(), MapActivity::class.java))
        }

        binding.tvFile.setOnClickListener {
            //   requestPermission()
            Toast.makeText(requireContext(), "data : " + Gson().toJson(Constants.map.values), Toast.LENGTH_SHORT).show()
        }

        binding.tvLink.setOnClickListener {
            Constants.setDataList(requireContext(), Data("test", 1))
        }
    }

    private fun requestPermission() {
        Dexter.withContext(requireContext())
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //.galleryOnly()	//User can only select image from Gallery
                            // .cameraOnly()	//User can only capture image using Camera
                            //  .crop()	    //Crop image and let user choose aspect ratio.
                            with(requireActivity()) //  .crop()                    //Crop image(Optional), Check Customization for more option
                                    .compress(1024) //Final image size will be less than 1 MB(Optional)
                                    .maxResultSize(
                                            1080,
                                            1080
                                    ) //Final image resolution will be less than 1080 x 1080(Optional)
                                    .start()
                        }
                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied) {
                            // permission is denied permenantly, navigate user to app settings
                            requestPermission()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                            permissions: List<PermissionRequest>,
                            token: PermissionToken
                    ) {
                        token.continuePermissionRequest()
                    }
                })
                .onSameThread()
                .check()
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        mFragmentResourceBinding = null
        super.onDestroyView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            if (data != null) {
                if (data.data != null) {
                    val fileUri = data.data
                    val file = getFile(data)
                    val filePath = getFilePath(data)
                    val selectedImage = BitmapFactory.decodeFile(filePath)
                    mFragmentResourceBinding!!.ivFile.setImageBitmap(selectedImage)
                    mPhoto = profileImage(selectedImage)!!
                    /* String encodedImage = encodeImage(selectedImage);*/Log.d(
                            "File",
                            "---------- : " + file!!.absolutePath
                    )
                    Log.d("filePath", "---------- : $filePath")
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun profileImage(b: Bitmap): ByteArray? {
        val bos = ByteArrayOutputStream()
        b.compress(Bitmap.CompressFormat.PNG, 0, bos)
        return bos.toByteArray()
    }
}