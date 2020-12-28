package com.app.municy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.filesender.interfaces.ItemClickListener;
import com.app.municy.R;
import com.app.municy.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> implements Filterable {
    public List<Notification> mArrayList;
    public List<Notification> rowBeans;
    public Context context;
    public ItemClickListener mItemClickListener;

    public NotificationListAdapter(Context context, List<Notification> list, ItemClickListener mItemClickListener) {
        this.context = context;
        this.rowBeans = list;
        this.mArrayList = list;
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Notification notification = rowBeans.get(i);
        viewHolder.tvDes.setText(rowBeans.get(i).getDescription());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onClick(notification);
                }
            }
        });
    }

    public int getItemCount() {
        return this.rowBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDes;
        public ViewHolder(View view) {
            super(view);
            this.tvDes = (TextView) view.findViewById(R.id.tvDepartment);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            /* access modifiers changed from: protected */
            public FilterResults performFiltering(CharSequence charSequence) {
                String charSequence2 = charSequence.toString();
                if (charSequence2.isEmpty()) {
                    rowBeans = mArrayList;
                } else {
                    ArrayList<Notification> arrayList = new ArrayList();
                    for (Notification rowBean : mArrayList) {
                        if (rowBean.getDescription().toLowerCase().contains(charSequence2)) {
                            arrayList.add(rowBean);
                        }
                    }
                    rowBeans = arrayList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = rowBeans;
                return filterResults;
            }

            /* access modifiers changed from: protected */
            public void publishResults(CharSequence charSequence, FilterResults filterResults) {
                rowBeans = (List<Notification>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
