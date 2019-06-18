package cz.venjudev.fio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.venjudev.fio.entity.LocalFioTransaction
import cz.venjudev.fio.entity.TransactionDetailItem
import cz.venjudev.fio.extensions.formatAmountValue
import cz.venjudev.fio.extensions.formatCurrency
import cz.venjudev.fio.extensions.formatDate
import cz.venjudev.fio.ui.TransactionDetailAdapter
import cz.venjudev.fio.ui.transactions.TransactionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class TransactionDetailActivity : AppCompatActivity() {

    private val model: TransactionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        val adapter = TransactionDetailAdapter(ArrayList())

        val id = intent.getStringExtra(ID_KEY)

        val listRV = findViewById<RecyclerView>(R.id.transactions_rv)
        listRV.layoutManager = LinearLayoutManager(this)
        listRV.adapter = adapter
        adapter.notifyDataSetChanged()


        model.getTransaction(id).observe(this, Observer { transaction ->
            adapter.update(createTxItems(transaction))

        })

        model.progress.observe(this, Observer {
            findViewById<ProgressBar>(R.id.progress_bar).visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun createTxItems(transaction: LocalFioTransaction): MutableList<TransactionDetailItem> {
        val transactionData: MutableList<TransactionDetailItem> = ArrayList()

        transactionData.add(TransactionDetailItem(getString(R.string.tx_date), transaction.date.formatDate()))

        findViewById<TextView>(R.id.amount_tv).text = transaction.amount.formatAmountValue()
        findViewById<TextView>(R.id.currency_tv).text = transaction.currency!!.formatCurrency()

        transaction.type.let {
            findViewById<TextView>(R.id.type_tv).text = it
        }

        transaction.getAccountInfo()?.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_account), it))
        }

        transaction.bankName?.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_bank_name), it))
        }

        transaction.author?.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_author), it))
        }

        transaction.userId?.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_user_id), it))
        }

        transaction.ks?.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_ks), it))
        }

        transaction.vs?.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_vs), it))
        }

        transaction.ss?.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_ss), it))
        }

        transaction.messageForRecipient?.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_message_for_recipient), it))
        }

        transaction.comment?.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_comment), it))
        }

        transaction.idP?.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_idp), it))
        }

        transaction.id.let {
            transactionData.add(TransactionDetailItem(getString(R.string.tx_id), it))
        }

        return transactionData
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    companion object {

        const val ID_KEY = "ID_KEY"

        fun newIntent(context: Context, id: String): Intent {
            val intent = Intent(context, TransactionDetailActivity::class.java)
            intent.putExtra(ID_KEY, id)
            return intent
        }

    }
}
