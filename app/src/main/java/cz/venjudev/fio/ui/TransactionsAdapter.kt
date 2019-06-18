package cz.venjudev.fio.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cz.venjudev.fio.R
import cz.venjudev.fio.entity.LocalFioTransaction
import cz.venjudev.fio.extensions.formatAmountValue
import cz.venjudev.fio.extensions.formatCurrency
import cz.venjudev.fio.extensions.formatDate
import cz.venjudev.fio.extensions.tintedDrawable


class TransactionsAdapter(
    var transactions: List<LocalFioTransaction>,
    val clickCallback: ClickCalback
) :
    RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    interface ClickCalback {
        fun onClicked(transaction: LocalFioTransaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
        holder.itemView.setOnClickListener {
            clickCallback.onClicked(transactions[position])
        }
    }

    fun update(updated: List<LocalFioTransaction>) {
        transactions = updated
        notifyDataSetChanged()
    }

    class TransactionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private var titleTextView: TextView = view.findViewById(R.id.title_tv)
        private var amountTextView: TextView = view.findViewById(R.id.amount_tv)
        private var currencyTextView: TextView = view.findViewById(R.id.currency_tv)
        private var dateTextView: TextView = view.findViewById(R.id.date_tv)
        private var typeTextView: TextView = view.findViewById(R.id.type_tv)
        private var arrowImageView: ImageView = view.findViewById(R.id.arrow_iv)

        fun bind(transaction: LocalFioTransaction) {
            titleTextView.text = transaction.getTitle()
            amountTextView.text = transaction.amount.formatAmountValue()
            currencyTextView.text = transaction.currency!!.formatCurrency()
            dateTextView.text = transaction.date.formatDate()
            typeTextView.text = transaction.type

            if (transaction.amount.signum() > 0) {
                arrowImageView.background = view.tintedDrawable(R.drawable.ic_arrow_forward, R.color.positiveAmount)
            } else {
                arrowImageView.background = view.tintedDrawable(R.drawable.ic_arrow_back, R.color.negativeAmount)
            }

        }
    }
}