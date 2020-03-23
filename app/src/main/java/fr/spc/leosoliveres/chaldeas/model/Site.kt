package fr.spc.leosoliveres.chaldeas.model

open class Site(
    _nom: String,
    _latitude: Float,
    _longitude: Float,
    _energie: String = "Non renseigné",
    _acces: String = "Non renseigné",
    _proprietaire: String = "Non renseigné",
    _type: String = "Type d'installation inconnu"
) {
    var nom:String = _nom
    var latitude:Float = _latitude
    var longitude:Float = _longitude
    var energie:String = _energie
    var acces:String = _acces
    var proprietaire:String = _proprietaire
    var type:String = _type

}