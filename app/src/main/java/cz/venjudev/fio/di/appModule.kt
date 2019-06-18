package cz.venjudev.fio.di

import androidx.room.Room
import cz.venjudev.fio.persistance.PreferencesManager
import cz.venjudev.fio.persistance.db.FioDatabase
import cz.venjudev.fio.repository.TransactionRepository
import cz.venjudev.fio.ui.transactions.TransactionViewModel
import cz.venjudev.fio.ui.transactions.TransactionsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single { PreferencesManager(androidContext()) }

    single { TransactionRepository(get(), get(), get()) }

    viewModel { (TransactionsViewModel(get())) }
    viewModel { (TransactionViewModel(get())) }

    single {
        Room.databaseBuilder(androidContext(), FioDatabase::class.java, "fio-db")
            .build()
    }

    single { get<FioDatabase>().infoDao() }
    single { get<FioDatabase>().localTransactionDao() }
}

