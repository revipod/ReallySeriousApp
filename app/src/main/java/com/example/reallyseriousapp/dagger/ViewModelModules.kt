package com.example.reallyseriousapp.dagger

import com.example.reallyseriousapp.UserInfoViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModules {

    @Provides
    fun providesUserViewModel() : UserInfoViewModel {
        return UserInfoViewModel("USA")
    }

}