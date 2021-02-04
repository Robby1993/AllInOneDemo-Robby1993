import android.content.Context
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.app.filesender.interfaces.ApiInterface
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object RetrofitHelper {
    //https://camposha.info/kotlin-android-capture-from-camera-or-pick-image/
    private const val TAG = "RetrofitHelper"
    private lateinit var call: Call<Any>

    @JvmStatic
    fun requestRetrofit(context: Context, call: Call<Any>, callback: CallBackListener) {
        val apiService: ApiInterface = ApiClient.client.create(ApiInterface::class.java)
        //   val file = File("path")
        // val body = MultipartBody.Part.createFormData("file", file.name, file.asRequestBody("*/*".toMediaTypeOrNull()))
        //  call = apiService.uploadFile(body)


        // Log.d(TAG, "ApiRequest-- : $callApi $hashMap"
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.code() == 200) {
                    try {
                        val resObject = JSONObject(Gson().toJson(response.body()))
                        Log.d(TAG, "ApiResponse--: ---$resObject")
                        callback.onSuccess(resObject)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                } else {
                    Log.d(TAG, "ApiResponse--MEssage : " + "---" + response.message())
                    val message: String = when (response.code()) {
                        401 -> "unAuthorized"
                        404 -> "not found"
                        500 -> "not logged in or server broken"
                        else -> "unknown error"
                    }
                    callback.onFailed(message)
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.d(TAG, "ApiResponse json error  " + " " + t.message)
                callback.onFailed(t.message.toString())
                //Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
    }

    fun request(context: Context, callback: CallBackListener) {
        AndroidNetworking.post("https://fierce-cove-29863.herokuapp.com/createAnUser")
                .addBodyParameter("firstname", "Amit")
                .addBodyParameter("lastname", "Shekhar")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        // do anything with response
                        if (response != null) {
                            callback.onSuccess(response)
                        }
                    }

                    override fun onError(error: ANError?) {
                        if (error != null) {
                            callback.onFailed(error.errorDetail)
                        }
                    }
                })
    }


    interface CallBackListener {
        fun onSuccess(json: JSONObject)
        fun onFailed(message: String)
    }
}