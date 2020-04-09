package fr.spc.leosoliveres.chaldeas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
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

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.editReport -> {
				Navigation.findNavController(findViewById(R.id.editReport)).navigate(R.id.global_reportEditFragment)
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}
}
