package com.app.municy.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressData(
    @SerializedName("cus_name")
    var cusName: String? = null,
    @SerializedName("mobile_number")
    var mobileNumber: String? = null,
    @SerializedName("add_l1")
    var addL1: String? = null,
    @SerializedName("add_l2")
    var addL2: String? = null,
    @SerializedName("address_id")
    var addressId: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("landmark")
    var landmark: String? = null,
    @SerializedName("pin")
    var pin: String? = null,
    @SerializedName("reseller_id")
    var resellerId: String? = null,
    @SerializedName("session_id")
    var sessionId: String? = null,
    @SerializedName("state")
    var state: String? = null
) : Serializable