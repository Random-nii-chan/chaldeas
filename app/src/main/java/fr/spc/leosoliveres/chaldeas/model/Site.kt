package fr.spc.leosoliveres.chaldeas.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//TODO: convertir site en entité Room
@Parcelize
@Entity(tableName = "sites")
data class Site(
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val powerSource: String = "Alimentation non renseignée",
    val access: String = "Type d'accès non renseigné",
    val owner: String = "Propriétaire non renseigné",
    val type: String = "Type d'installation inconnu",
    @PrimaryKey(autoGenerate = true) val id:Int = 0
) : Parcelable {

    fun lonString():String {
        return String.format("Lon. : $longitude")
    }

    fun latString():String {
        return String.format("Lat. : $latitude")
    }
}