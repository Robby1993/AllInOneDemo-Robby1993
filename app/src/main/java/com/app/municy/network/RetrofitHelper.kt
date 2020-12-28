
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.app.filesender.interfaces.ApiInterface
import okhttp3.MultipartBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


object RetrofitHelper {
    private const val TAG = "RetrofitHelper"
    private lateinit var call: Call<Any>

    @JvmStatic
    fun requestRetrofit(context: Context, call: Call<Any>, callback: CallBackListener) {
        val apiService: ApiInterface = ApiClient.client.create(ApiInterface::class.java)
      //  val file = File("path")
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

    interface CallBackListener {
        fun onSuccess(json: JSONObject)
        fun onFailed(message: String)
    }
}