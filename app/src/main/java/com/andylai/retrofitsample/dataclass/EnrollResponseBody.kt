package com.andylai.retrofitsample.dataclass

import android.os.Parcel
import android.os.Parcelable
import org.jetbrains.annotations.TestOnly

data class EnrollResponseBody(val instance_id: String?, val code: String?, val mqtt_host: String?,
							  val mqtt_timeout: Int) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readInt())

	@TestOnly
	constructor() : this(null, "32fr68", null, 0)


	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(instance_id)
		parcel.writeString(code)
		parcel.writeString(mqtt_host)
		parcel.writeInt(mqtt_timeout)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<EnrollResponseBody> {
		override fun createFromParcel(parcel: Parcel): EnrollResponseBody {
			return EnrollResponseBody(parcel)
		}

		override fun newArray(size: Int): Array<EnrollResponseBody?> {
			return arrayOfNulls(size)
		}
	}
}