package fr.spc.leosoliveres.chaldeas.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.spc.leosoliveres.chaldeas.R

class StationDetailFragment : Fragment() {
    companion object{
        fun newInstance(): StationDetailFragment{
            return StationDetailFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_station_detail, container, false)
    }
}