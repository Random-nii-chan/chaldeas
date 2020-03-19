package fr.spc.leosoliveres.chaldeas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.spc.leosoliveres.chaldeas.adapter.SitesAdapter
import fr.spc.leosoliveres.chaldeas.model.Site
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val sites : ArrayList<Site> = ArrayList()

		sites.add(Site("Nom de relais qui est bien trop long pour rentrer sur une ligne",-5.45457856f,3.89164832f))

		for (i in 0..10){
			sites.add(
				Site(
					"Station $i",
					((-90..89).random() + Math.random()).toFloat(),
					((-180..179).random() + Math.random()).toFloat()
				)
			)
		}

		siteRecyclerView.layoutManager = LinearLayoutManager(this)
		siteRecyclerView.adapter = SitesAdapter(sites)
	}
}
