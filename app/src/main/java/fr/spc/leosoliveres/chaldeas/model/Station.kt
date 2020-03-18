package fr.spc.leosoliveres.chaldeas.model

import fr.spc.leosoliveres.chaldeas.model.Site

class Station(_nom:String, _latitude:Double, _longitude:Double, _energie:String, _acces:String,
              _proprietaire:String, _type:String):
    Site(_nom,_longitude,_latitude,_energie,_acces, _proprietaire, _type)