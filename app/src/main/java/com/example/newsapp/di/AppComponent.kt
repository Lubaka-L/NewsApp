package com.example.newsapp.di

import com.example.newsapp.ui.screens.business.BusinessViewModel
import com.example.newsapp.ui.screens.entertainment.EntertainmentViewModel
import com.example.newsapp.ui.screens.general.GeneralViewModel
import com.example.newsapp.ui.screens.health.HealthViewModel
import com.example.newsapp.ui.screens.nation.NationViewModel
import com.example.newsapp.ui.screens.science.ScienceViewModel
import com.example.newsapp.ui.screens.sports.SportsViewModel
import com.example.newsapp.ui.screens.technology.TechnologyViewModel
import com.example.newsapp.ui.screens.world.WorldViewModel
import dagger.Component

@Component(modules = [AppModule::class, NetWorkModule::class])
interface AppComponent {

    fun inject(businessViewModel: BusinessViewModel)
    fun inject(entertainmentViewModel: EntertainmentViewModel)
    fun inject(generalViewModel: GeneralViewModel)
    fun inject(healthViewModel: HealthViewModel)
    fun inject(scienceViewModel: ScienceViewModel)
    fun inject(sportsViewModel: SportsViewModel)
    fun inject(nationViewModel: NationViewModel)
    fun inject(worldViewModel: WorldViewModel)
    fun inject(technologyViewModel: TechnologyViewModel)

}