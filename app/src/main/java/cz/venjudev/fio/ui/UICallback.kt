package cz.venjudev.fio.ui

interface UICallback<E> {

    fun onProgress()
    fun onSuccess(data: E)
    fun onError()

}