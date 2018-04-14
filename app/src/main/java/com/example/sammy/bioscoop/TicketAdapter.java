package com.example.sammy.bioscoop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sammy on 4/5/18.
 */


public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>{
    private static final String TAG = "TicketAdapter";

        private ArrayList<Ticket> tickets;
        private Context mContext;

        public TicketAdapter(ArrayList<Ticket> tickets, Context mContext) {
            this.tickets = tickets;
            this.mContext = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View mView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ticket_item, parent, false);

            return new ViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(TicketAdapter.ViewHolder holder, final int position) {

            final Ticket ticket = tickets.get(position);
//        Picasso.with(holder.itemView.getContext()).load(listItem.getImageURL()).into(holder.imageView);

            holder.textTicketTitle.setText(String.valueOf(ticket.getFilmTitle()));
            holder.dateTicketText.setText(String.valueOf(ticket.getDate()));
            holder.ticketID.setText(String.valueOf(ticket.getTicketID()));

            holder.ticketList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "OnClick clicked on");
                    Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();

                    Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), TicketDetailActivity.class);

                    itemDetailIntent.putExtra("MyFilmAdapter", (Serializable) ticket);


//                itemDetailIntent.putExtra("imageURL", listItem.getImageURL());
//                itemDetailIntent.putExtra("cameraName", listItem.getCameraName());

                    view.getContext().startActivity(itemDetailIntent);
                }
            });

        }

        @Override
        public int getItemCount() {

            return tickets.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private static final String TAG = "ViewHolder";

            private RelativeLayout ticketList;
            private TextView textTicketTitle;
            private TextView dateTicketText;
            private TextView ticketID;


            public ViewHolder(View itemView) {

                super(itemView);

                ticketList = itemView.findViewById(R.id.ticketItem);

                textTicketTitle = itemView.findViewById(R.id.titleTicketText);
                dateTicketText = itemView.findViewById(R.id.dateTicketText);
                ticketID =itemView.findViewById(R.id.ticketID);
            }
        }
    }


