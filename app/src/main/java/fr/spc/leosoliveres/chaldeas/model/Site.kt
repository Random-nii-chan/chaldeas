package fr.spc.leosoliveres.chaldeas.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Site(
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val powerSource: String = "Alimentation non renseignée",
    val access: String = "Type d'accès non renseigné",
    val owner: String = "Propriétaire non renseigné",
    val type: String = "Type d'installation inconnu"
) : Parcelable