package fr.spc.leosoliveres.chaldeas.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fr.spc.leosoliveres.chaldeas.R

class ReportEditFragment : Fragment() {

    companion object {
        fun newInstance() = ReportEditFragment()
    }

    private lateinit var viewModel: ReportEditViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.report_edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReportEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
