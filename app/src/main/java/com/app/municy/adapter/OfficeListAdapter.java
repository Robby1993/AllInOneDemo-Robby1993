/*
package com.app.municy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.feblix.ClickListener.ItemClickListener;
import com.feblix.Model.Office.OfficeList;
import com.feblix.Model.OrderListModel;
import com.feblix.R;
import com.feblix.databinding.AdapterOrderListBinding;
import com.feblix.databinding.ItemOfficeListBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OfficeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private List<OfficeList> mData;
    private List<OfficeList> dataListFiltered;
    private Context mContext;
    private ItemClickListener mItemClickListener;

   */
/* public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }*//*


    public OfficeListAdapter(Context context, List<OfficeList> mData, ItemClickListener mItemClickListener) {
        this.mContext = context;
        this.mData = mData;
        dataListFiltered = new ArrayList<>(mData);
        this.mItemClickListener = mItemClickListener;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemOfficeListBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_office_list, parent, false);
        return new ItemViewHolder(mBinding.getRoot(), mBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final OfficeListAdapter.ItemViewHolder mViewHolder = (OfficeListAdapter.ItemViewHolder) holder;
        OfficeList mOfficeList = mData.get(position);
        mViewHolder.mBinding.tvOfficeName.setText(mOfficeList.getOfficeName());
        mViewHolder.mBinding.tvAddress.setText(mOfficeList.getOfficeAddress());
        mViewHolder.mBinding.tvName.setText(mOfficeList.getOfficeManagePresonName());
        mViewHolder.mBinding.tvCity.setText(mOfficeList.getOfficeCity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onClick(v, position, mOfficeList);
                }
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemOfficeListBinding mBinding;

        ItemViewHolder(View itemView, ItemOfficeListBinding mBinding) {
            super(itemView);
            this.mBinding = mBinding;
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault()).trim();
        mData.clear();
        if (charText.length() == 0) {
            mData.addAll(dataListFiltered);
        } else {
            for (OfficeList QA1 : dataListFiltered) {
                if (QA1.getOfficeName().toLowerCase(Locale.getDefault()).contains(charText)
                        || QA1.getOfficeAddress().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mData.add(QA1);
                }
            }
        }
        //notifyDataSetChanged();
    }

    public Filter getFilter() {
        return exampleFilter;
    }
    private final Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<OfficeList> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataListFiltered);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (OfficeList item : dataListFiltered) {
                    if (item.getOfficeName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
*/
