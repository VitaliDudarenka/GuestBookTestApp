package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.answers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gmail.dudarenka.vitali.guestbooktestapp.R
import com.gmail.dudarenka.vitali.guestbooktestapp.domain.entity.Feedback
import java.util.*


class AnswerListAdapter : RecyclerView.Adapter<AnswerListAdapter.Holder>() {
    private var answerList: ArrayList<Feedback>? = ArrayList()
    var listData: MutableList<Feedback>? = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
            answerList!!.addAll(this.listData!!)
        }
    //internal var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.feedback_item, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = listData!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val feedback = listData!![position]
        holder.titleTextView.text = feedback.title
        holder.messageTextView.text = feedback.message
        holder.nameTextView.text = feedback.user!!.name
        holder.dateTextView.text = feedback.created_at
    }

    inner class Holder : RecyclerView.ViewHolder {
        var titleTextView: TextView
        var messageTextView: TextView
        var nameTextView: TextView
        var dateTextView: TextView

        constructor(view: View) : super(view) {
            titleTextView = view.findViewById(R.id.titleTextView)
            messageTextView = view.findViewById(R.id.messageTextView)
            nameTextView = view.findViewById(R.id.nameTextView)
            dateTextView = view.findViewById(R.id.dateTextView)
            /*itemView.setOnClickListener {
                val user = listData!![layoutPosition]
                onItemClickListener!!.onItemClick(user)
            }*/
        }

    }

    /*interface OnItemClickListener {
        fun onItemClick(car: Car)
    }*/

}