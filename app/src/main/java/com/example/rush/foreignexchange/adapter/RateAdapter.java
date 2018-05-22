package com.example.rush.foreignexchange.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rush.foreignexchange.R;
import com.example.rush.foreignexchange.gson.ForeignExchange;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by rush on 2018/5/10.
 */

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {
    private List<ForeignExchange> mForeignExchangeList;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currency;
        private TextView buyingRate;
        private TextView cashBuyingRate;
        private TextView sellingRate;
        private TextView cashSellingRate;
        private TextView middleRate;
        private TextView publishDateTime;
        public ViewHolder(View itemView) {
            super(itemView);
            currency=(TextView) itemView.findViewById(R.id.currency);
            buyingRate=(TextView) itemView.findViewById(R.id.buying_rate);
            cashBuyingRate=(TextView) itemView.findViewById(R.id.cash_buying_rate);
            sellingRate=(TextView) itemView.findViewById(R.id.selling_rate);
            cashSellingRate=(TextView) itemView.findViewById(R.id.cash_selling_rate);
            middleRate=(TextView) itemView.findViewById(R.id.middle_rate);
            publishDateTime=(TextView) itemView.findViewById(R.id.publish_date_time);
        }
    }
    public RateAdapter(List<ForeignExchange> foreignExchangeList){
        mForeignExchangeList=foreignExchangeList;
    }
    @Override
    public RateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RateAdapter.ViewHolder holder, int position) {
        ForeignExchange foreignExchange=mForeignExchangeList.get(position);
        holder.currency.setText(foreignExchange.getCurrency());
        holder.buyingRate.setText(String.valueOf(foreignExchange.getBuyingRate()));
        holder.cashBuyingRate.setText(foreignExchange.getCashBuyingRate().toString());
        holder.sellingRate.setText(foreignExchange.getSellingRate().toString());
        holder.cashSellingRate.setText(foreignExchange.getCashSellingRate().toString());
        holder.middleRate.setText(foreignExchange.getMiddleRate().toString());
        holder.publishDateTime.setText(foreignExchange.getPublishDateTime());
    }

    @Override
    public int getItemCount() {
        return mForeignExchangeList.size();
    }
}
