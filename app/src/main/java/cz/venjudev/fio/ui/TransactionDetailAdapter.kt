package cz.venjudev.fio.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cz.venjudev.fio.R
import cz.venjudev.fio.entity.TransactionDetailItem


class TransactionDetailAdapter(var transactions: List<TransactionDetailItem>) :
    RecyclerView.Adapter<TransactionDetailAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction_detail, parent, false)
        return TransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    fun update(updated: List<TransactionDetailItem>) {
        transactions = updated
        notifyDataSetChanged()
    }

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var titleTextView = view.findViewById<TextView>(R.id.title_tv)
        private var valueTextView = view.findViewById<TextView>(R.id.value_tv)

        fun bind(transaction: TransactionDetailItem) {
            titleTextView.text = transaction.title
            valueTextView.text = transaction.value
        }
    }
}