package fr.spc.leosoliveres.chaldeas.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Site(
    val nom: String,
    val latitude: Float,
    val longitude: Float,
    val energie: String = "Non renseigné",
    val acces: String = "Non renseigné",
    val proprietaire: String = "Non renseigné",
    val type: String = "Type d'installation inconnu"
) : Parcelable