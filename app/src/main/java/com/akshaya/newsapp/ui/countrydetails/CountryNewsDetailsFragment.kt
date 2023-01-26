package com.akshaya.newsapp.ui.countrydetails

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshaya.newsapp.R
import com.akshaya.newsapp.di.component.FragmentComponent
import com.akshaya.newsapp.ui.base.BaseFragment
import javax.inject.Inject

class CountryNewsDetailsFragment : BaseFragment() {

    companion object {

        const val TAG = "HomeFragment"
//
//        fun newInstance(): CountryNewsDetailsFragment {
//            val args = Bundle()
//            val fragment = CountryNewsDetailsFragment()
//            fragment.arguments = args
//            return fragment
//        }
    }

    @Inject
    lateinit var mainSharedViewModel: FragmentViewModel

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun provideLayoutId(): Int = R.layout.fragment_layout

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

    }
}