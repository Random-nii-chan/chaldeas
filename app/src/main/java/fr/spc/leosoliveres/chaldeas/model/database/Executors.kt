package fr.spc.leosoliveres.chaldeas.model.database

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()
//exécuteur pour déléguer en thread arrière plan
fun ioThread(f:() -> Unit) {
	IO_EXECUTOR.execute(f)
}