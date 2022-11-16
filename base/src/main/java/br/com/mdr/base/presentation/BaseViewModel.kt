package br.com.mdr.base.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mdr.base.extension.toErrorWrapper
import br.com.mdr.base.extension.toUnknownErrorWrapper
import br.com.mdr.base.model.Error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {
    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var _apiError = MutableLiveData<Error>()
    val apiError: LiveData<Error> = _apiError

    protected fun launch(
        enableLoading: Boolean = true,
        errorBlock: ((Throwable) -> Unit?)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) =
        viewModelScope.launch {
            showLoading(enableLoading, true)
            runCatching {
                block()
            }
                .onSuccess {
                    showLoading(enableLoading, false)
                }
                .onFailure { error ->
                    showLoading(enableLoading, false)
                    if (errorBlock != null) errorBlock.invoke(error)
                    else postErrorValue(error)
                }
        }

    private fun showLoading(isLoadingEnabled: Boolean, showLoading: Boolean) {
        if (isLoadingEnabled) _loading.postValue(showLoading)
    }

    private fun postErrorValue(throwable: Throwable) {
        this.postErrorValue(_apiError, throwable)
    }

    private fun postErrorValue(dispatcher: MutableLiveData<Error>, throwable: Throwable) {
        if (throwable is HttpException) {
            dispatcher.postValue(throwable.toErrorWrapper())
        } else if (throwable is UnknownHostException) {
            dispatcher.postValue(throwable.toUnknownErrorWrapper())
        }
    }
}
