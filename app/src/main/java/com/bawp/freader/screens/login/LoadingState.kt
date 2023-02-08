package com.bawp.freader.screens.login

data class LoadingState(val state: Status, val message: String? = null) {

    companion object {
        val IDLE = LoadingState(Status.IDLE)
        val SUCESS = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.LOADING)
        val FAILED = LoadingState(Status.FAILED )
    }

    enum class Status {
        SUCCESS,
        FAILED,
        LOADING,
        IDLE
    }
}
