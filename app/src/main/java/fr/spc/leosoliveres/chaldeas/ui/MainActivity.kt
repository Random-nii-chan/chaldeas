package fr.spc.leosoliveres.chaldeas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import fr.spc.leosoliveres.chaldeas.R

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(findViewById(R.id.toolbar))
		supportActionBar?.setDisplayShowHomeEnabled(true);
		supportActionBar?.setHomeButtonEnabled(true)
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.toolbar,menu)
		return super.onCreateOptionsMenu(menu)
	}
}
