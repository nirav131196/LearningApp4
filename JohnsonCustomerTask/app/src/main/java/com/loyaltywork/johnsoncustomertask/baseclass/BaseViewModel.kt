package com.loyaltywork.johnsoncustomertask.baseclass

import androidx.lifecycle.ViewModel
import com.loyaltywork.johnsoncustomertask.utils.fetchData.apiCall.ApiService
import com.loyaltywork.johnsoncustomertask.utils.fetchData.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel:ViewModel() {

    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    val scope = CoroutineScope(coroutineContext)
    //initialize repo
    val apiRepository : ApiRepository = ApiRepository(ApiService.apiInterface)
}