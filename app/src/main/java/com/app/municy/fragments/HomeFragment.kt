package com.app.municy.fragments

import ApiClient
import RetrofitHelper
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.app.filesender.interfaces.ApiInterface
import com.app.filesender.interfaces.ItemClickListener
import com.app.municy.HomeActivity.Companion.countIncrement
import com.app.municy.R
import com.app.municy.adapter.NotificationListAdapter
import com.app.municy.databinding.FragmentHomeBinding
import com.app.municy.interfaces.updateCount
import com.app.municy.model.Notification
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONException
import org.json.JSONObject


class HomeFragment : Fragment(), ItemClickListener {

    private var mFragmentHomeBinding: FragmentHomeBinding? = null
    private val TAG = "HomeFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_home, container, false)
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val fragmentName = arguments?.getString("fragmentName")
        // rootView.fragment_name.text = fragmentName

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        mFragmentHomeBinding = binding
        /* binding.emails.setOnClickListener {
             Navigation.findNavController(requireView()).navigate(R.id.actionToEmail)
         }*/
        val list: MutableList<Notification> = ArrayList()
        list.add(Notification("Test"))
        list.add(Notification("Test1"))
        list.add(Notification("Test2"))
        list.add(Notification("Test3"))
        list.add(Notification("Test4"))
        list.add(Notification("Test5"))
        list.add(Notification("Test6"))
        list.add(Notification("Test7"))
        list.add(Notification("Test8"))
        list.add(Notification("Test9"))
        list.add(Notification("Test10"))
        val adapter = NotificationListAdapter(requireContext(), list, this)
        mFragmentHomeBinding!!.rvHome.adapter = adapter

        mFragmentHomeBinding!!.ivUp.setOnClickListener {
            mFragmentHomeBinding!!.ivUp.visibility = View.GONE
            val dialogView: View = layoutInflater.inflate(R.layout.bottom_sheet, null)
            val dialog = BottomSheetDialog(requireContext())
            dialog.setContentView(dialogView)

            (dialogView.parent as View).setBackgroundColor(Color.TRANSPARENT)

            (dialog.findViewById<View>(R.id.ivDown) as ImageView?)?.setOnClickListener {
                mFragmentHomeBinding!!.ivUp.visibility = View.VISIBLE
                dialog.dismiss()
            }

            dialog.show()
        }

    }


    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        mFragmentHomeBinding = null
        super.onDestroyView()
    }

    private fun getData(id: Int) {
        //binding.progressbar.visibility == View.VISIBLE
        val apiService = ApiClient.client.create(ApiInterface::class.java)
        val call = apiService.getAdvertisement(id)
        //    Log.d(TAG, "uploadShopData  mObject : $id")

        RetrofitHelper.requestRetrofit(
                requireContext(),
                call,
                object : RetrofitHelper.CallBackListener {
                    override fun onFailed(message: String) {
                        // binding.progressbar.visibility == View.GONE
                        Log.d(TAG, "uploadShopData  message : $message")
                    }

                    override fun onSuccess(json: JSONObject) {
                        //  binding.progressbar.visibility == View.GONE
                        Log.d(TAG, "uploadShopData  mObject : $json")
                        try {
                            val status = json.getString("status")
                            if (status == "success") {
                                val img = json.getString("fileUrl")
                                val appUrl = json.getString("appUrl")
                            }
                            Log.d(TAG, "ApiResponse--: " + "fileUrl " + json.getString("fileUrl"))
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                })
    }

    private fun getData1(id: Int) {
        RetrofitHelper.request(
            requireContext(), object : RetrofitHelper.CallBackListener {
                override fun onSuccess(json: JSONObject) {
                    //  binding.progressbar.visibility == View.GONE

                }
                override fun onFailed(message: String) {
                    // binding.progressbar.visibility == View.GONE
                    Log.d(TAG, "uploadShopData  message : $message")
                }
            })
    }



    override fun onClick(data: Any) {
        /* val notification: Notification = data as Notification;
         val body = notification.description*/
        countIncrement += 1
        (context as updateCount).updateCartCount()

        //test
        /* startActivity(
             Intent(requireContext(), NotificationActivity::class.java)
             .putExtra("id", id)
             .putExtra("banner", banner)
             .putExtra("body", body)
             .putExtra("title", title))*/

        /*  val dialogView: View = layoutInflater.inflate(R.layout.bottom_sheet, null)
          val dialog = BottomSheetDialog(requireContext())
          dialog.setContentView(dialogView)
          dialog.show()*/
    }

  /*  override fun dispatchEvent(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (mBottomSheetBehavior.getState() === BottomSheetBehavior.STATE_EXPANDED) {
                val outRect = Rect()
                bottomSheet.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }

        }
    }*/


}