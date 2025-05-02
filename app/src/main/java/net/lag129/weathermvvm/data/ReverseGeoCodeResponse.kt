package net.lag129.weathermvvm.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReverseGeoCodeResponse(
    @SerialName("Feature") val feature: List<Feature>
)

@Serializable
data class Feature(
    @SerialName("Property") val property: Property
)

@Serializable
data class Property(
    @SerialName("AddressElement") val addressElement: List<AddressElement>
)

@Serializable
data class AddressElement(
    @SerialName("Name") val name: String
)