package fr.spc.leosoliveres.chaldeas.model

import fr.spc.leosoliveres.chaldeas.model.Site

class Station(_nom:String, _latitude:Double, _longitude:Double, _energie:String="Non renseigné", _acces:String="Non renseigné",
              _proprietaire:String="Non renseigné", _type:String="Non renseigné"):
    Site(_nom,_longitude,_latitude,_energie,_acces, _proprietaire, _type)