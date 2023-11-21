package com.example.newsapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.ui.screens.business.BusinessFragment
import com.example.newsapp.ui.screens.entertainment.EntertainmentFragment
import com.example.newsapp.ui.screens.general.GeneralFragment
import com.example.newsapp.ui.screens.health.HealthFragment
import com.example.newsapp.ui.screens.nation.NationFragment
import com.example.newsapp.ui.screens.science.ScienceFragment
import com.example.newsapp.ui.screens.sports.SportsFragment
import com.example.newsapp.ui.screens.technology.TechnologyFragment
import com.example.newsapp.ui.screens.world.WorldFragment

class TabFragmentsAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragmentsList = listOf<Pair<Fragment, String>>(
        Pair(GeneralFragment(), "Главная"),
        Pair(WorldFragment(), "Мир"),
        Pair(NationFragment(), "Россия"),
        Pair(SportsFragment(), "Спорт"),
        Pair(TechnologyFragment(), "Технологии"),
        Pair(HealthFragment(), "Здоровье"),
        Pair(ScienceFragment(), "Наука"),
        Pair(BusinessFragment(), "Бизнес"),
        Pair(EntertainmentFragment(), "Шоу бизнес")
    )

    fun  getTabTitle(tabPosition: Int) : String{
        return fragmentsList[tabPosition].second
    }

    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position].first
    }
}