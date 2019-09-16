package sv.edu.bitlab.pupusap

import android.os.Parcel
import android.os.Parcelable

class Usuario() : Parcelable {
  constructor(parcel: Parcel) : this() {
  }

  override fun writeToParcel(p0: Parcel?, p1: Int) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun describeContents(): Int {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  companion object CREATOR : Parcelable.Creator<Usuario> {
    override fun createFromParcel(parcel: Parcel): Usuario {
      return Usuario(parcel)
    }

    override fun newArray(size: Int): Array<Usuario?> {
      return arrayOfNulls(size)
    }
  }
}