package fr.spc.leosoliveres.chaldeas.model

open class Site {
    private var nom:String
    private var latitude:Double
    private var longitude:Double
    private var energie:String
    private var acces:String
    private var proprietaire:String
    private var type:String

    get() = field

    set(value){
        field = value
    }

    constructor(_nom:String, _latitude:Double, _longitude:Double, _energie:String, _acces:String, _proprietaire:String, _type:String) {
        nom = _nom
        latitude = _latitude
        longitude = _longitude
        energie = _energie
        acces = _acces
        proprietaire = _proprietaire
        type = _type
    }
}