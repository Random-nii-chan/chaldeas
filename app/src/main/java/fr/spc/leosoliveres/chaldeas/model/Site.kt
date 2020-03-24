package fr.spc.leosoliveres.chaldeas.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Site(
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val powerSource: String = "Non renseigné",
    val access: String = "Non renseigné",
    val owner: String = "Non renseigné",
    val type: String = "Type d'installation inconnu"
) : Parcelable