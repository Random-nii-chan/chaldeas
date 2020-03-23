package fr.spc.leosoliveres.chaldeas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.spc.leosoliveres.chaldeas.R
import fr.spc.leosoliveres.chaldeas.adapter.SitesAdapter
import fr.spc.leosoliveres.chaldeas.model.Site
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
}
