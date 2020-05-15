package fr.spc.leosoliveres.chaldeas.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "sites")
data class Site(
    @PrimaryKey(autoGenerate = true) val siteId:Int=0,
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val powerSource:String = "Alimentation non renseignée",
    val access: String = "Type d'accès non renseigné",
    val owner: String = "Propriétaire non renseigné",
    val type: String= "Type d'installation inconnu"
) : Parcelable {

    constructor(_name:String,_latitude: Float,_longitude: Float):this(
        0,
        _name,
        _latitude,
        _longitude,
        "Alimentation non renseignée",
        "Type d'accès non renseigné",
        "Propriétaire non renseigné",
        "Type d'installation inconnu"
    )

    fun lonString():String {
        return String.format("Lon. : $longitude")
    }

    fun latString():String {
        return String.format("Lat. : $latitude")
    }
}