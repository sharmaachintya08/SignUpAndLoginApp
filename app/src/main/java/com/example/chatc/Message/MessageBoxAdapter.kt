package com.example.chatc.Message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatc.R
import com.example.chatc.data.ProfileData

class MessageBoxAdapter(val profileData : ArrayList<ProfileData>) : RecyclerView.Adapter<MessageBoxAdapter.ViewHolder>(){
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cardview : LinearLayout
        val linearLayout : LinearLayout
        val profileImage : ImageView
        val profileName : TextView
        val messageText : TextView
        init{
            cardview = itemView.findViewById(R.id.toplinearlayout)
            linearLayout = itemView.findViewById(R.id.linearLayout)
            profileImage = itemView.findViewById(R.id.profilePicture)
            profileName = itemView.findViewById(R.id.profileName)
            messageText = itemView.findViewById(R.id.message)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.message_card,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.profileName.setText(profileData[position].name)
        holder.messageText.setText(profileData[position].message)
    }

    override fun getItemCount(): Int {
        return profileData.size
    }
}