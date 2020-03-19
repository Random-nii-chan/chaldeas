package fr.spc.leosoliveres.chaldeas.model

open class Site {
    var nom:String
    var latitude:Float
    var longitude:Float
    var energie:String
    var acces:String
    var proprietaire:String
    var type:String

    set(value){
        field = value
    }

    constructor(_nom:String,
                _latitude:Float,
                _longitude:Float,
                _energie:String="Non renseigné",
                _acces:String="Non renseigné",
                _proprietaire:String="Non renseigné",
                _type:String="Non renseigné") {
        nom = _nom
        latitude = _latitude
        longitude = _longitude
        energie = _energie
        acces = _acces
        proprietaire = _proprietaire
        type = _type
    }
}