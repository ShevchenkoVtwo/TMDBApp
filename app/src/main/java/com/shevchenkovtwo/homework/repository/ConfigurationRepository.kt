package com.shevchenkovtwo.homework.repository

import com.shevchenkovtwo.homework.basemodels.Configuration


interface ConfigurationRepository {

    suspend fun loadConfiguration(): Configuration

}