package cz.venjudev.fio

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.venjudev.fio.entity.LocalFioTransaction
import cz.venjudev.fio.extensions.formatAmountValue
import cz.venjudev.fio.extensions.formatCurrency
import cz.venjudev.fio.ui.TransactionsAdapter
import cz.venjudev.fio.ui.transactions.TransactionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val model: TransactionsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transactionsRV = findViewById<RecyclerView>(R.id.transactions_rv)

        val adapter = TransactionsAdapter(ArrayList(), object : TransactionsAdapter.ClickCalback {
            override fun onClicked(transaction: LocalFioTransaction) {
                startDetail(transaction)
            }

        })

        transactionsRV.layoutManager = LinearLayoutManager(this)
        transactionsRV.adapter = adapter
        adapter.notifyDataSetChanged()

        model.getTransactions().observe(this, Observer {
            adapter.update(it.transactions)

            val account = "${it.info.accountId}/${it.info.bankId}"
            findViewById<TextView>(R.id.account_tv).text = account
            findViewById<TextView>(R.id.balance_tv).text = it.info.closingBalance!!.formatAmountValue()
            findViewById<TextView>(R.id.currency_tv).text = it.info.currency!!.formatCurrency()
        })

        model.progress.observe(this, Observer {
            findViewById<ProgressBar>(R.id.progress_bar).visibility = if (it) View.VISIBLE else View.GONE
        })

    }

    fun startDetail(transaction: LocalFioTransaction) {
        startActivity(TransactionDetailActivity.newIntent(this, transaction.id))
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }
}
