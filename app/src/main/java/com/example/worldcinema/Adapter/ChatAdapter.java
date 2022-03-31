package com.example.worldcinema.Adapter;

import static com.example.worldcinema.Network.Models.ChatMessageResponse.LayoutOne;
import static com.example.worldcinema.Network.Models.ChatMessageResponse.LayoutTwo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.Network.Models.ChatMessageResponse;
import com.example.worldcinema.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {

    private List<ChatMessageResponse> chatItems;
    private String txtMessageMy, txtMessageOpp;
    private String txtNameMy, txtNameOpp;
    private String txtTimeMy, txtTimeOpp;

    public ChatAdapter(List<ChatMessageResponse> chatItems) {
        this.chatItems = chatItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LayoutOne:
                View viewOne = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_chat, parent, false);
                return new LayoutOneViewHolder(viewOne);
            case LayoutTwo:
                View viewTwo = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_opponent_chat, parent, false);
                return new LayoutTwoViewHolder(viewTwo);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (chatItems.get(position).getViewType()){
            case LayoutOne:
                txtMessageMy = chatItems.get(position).getText();
                txtNameMy = chatItems.get(position).getFirstName() + chatItems.get(position).getLastName();
                txtTimeMy = chatItems.get(position).getCreationDateTime();
                ((LayoutOneViewHolder)holder).setTxtMessage(txtMessageMy);
                ((LayoutOneViewHolder)holder).setTxtNamePerson(txtNameMy);
                ((LayoutOneViewHolder)holder).txtMessageTime.setText(txtTimeMy);
                ((LayoutOneViewHolder)holder).imageView.setImageResource(R.drawable.arrow_avatar);
                break;
            case LayoutTwo:
                txtMessageOpp = chatItems.get(position).getText();
                txtNameOpp = chatItems.get(position).getFirstName() + chatItems.get(position).getLastName();
                txtTimeOpp = chatItems.get(position).getCreationDateTime();
                ((LayoutTwoViewHolder)holder).setTxtMessage(txtMessageOpp);
                ((LayoutTwoViewHolder)holder).setTxtNamePerson(txtNameOpp);
                ((LayoutTwoViewHolder)holder).txtMessageTime.setText(txtTimeOpp);
                ((LayoutTwoViewHolder)holder).imageView.setImageResource(R.drawable.arrow_avatar);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatItems.get(position).getViewType()) {
            case 0:
                return LayoutOne;
            case 1:
                return LayoutTwo;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    class LayoutOneViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMessage;
        private TextView txtNamePerson;
        private TextView txtMessageTime;
        public ImageView imageView;

        public LayoutOneViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txt_my_chat);
            txtNamePerson = itemView.findViewById(R.id.txt_my_chat_name);
            txtMessageTime = itemView.findViewById(R.id.txt_my_chat_time);
            imageView = itemView.findViewById(R.id.img_my);
        }


        public TextView getTxtMessage() {
            return txtMessage;
        }

        public void setTxtMessage(String txt) {
            txtMessage.setText(txt);
        }

        public TextView getTxtNamePerson() {
            return txtNamePerson;
        }

        public void setTxtNamePerson(String txtName) {
            txtNamePerson.setText(txtName);
        }
    }
    class LayoutTwoViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMessage;
        private TextView txtNamePerson;
        private TextView txtMessageTime;
        public ImageView imageView;


        public LayoutTwoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txt_opponent_chat);
            txtNamePerson = itemView.findViewById(R.id.txt_opponent_chat_name);
            txtMessageTime = itemView.findViewById(R.id.txt_opponent_chat_time);
            imageView = itemView.findViewById(R.id.img_opponent);
        }

        public TextView getTxtMessage() {
            return txtMessage;
        }

        public void setTxtMessage(String txt) {
            txtMessage.setText(txt);
        }

        public TextView getTxtNamePerson() {
            return txtNamePerson;
        }

        public void setTxtNamePerson(String txtName) {
            txtNamePerson.setText(txtName);
        }


    }
}
