package com.example.json;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<ToDo> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ToDoAdapter(Context context, ArrayList<ToDo> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.todo, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, final int position) {
        ToDo currentItem = mExampleList.get(position);

        String userId = currentItem.getUserId();
        String id = currentItem.getId();
        String title = currentItem.getTitle();
        String completed = currentItem.getCompleted();


        holder.mTextViewuserId.setText(userId);
        holder.mTextViewid.setText(id);
        holder.mTextViewtitle.setText(title);
        holder.mTextViewcompleted.setText(completed);



    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewuserId;
        public TextView mTextViewid;
        public TextView mTextViewtitle;
        public TextView mTextViewcompleted;

        public ExampleViewHolder(View itemView) {
            super(itemView);



            mTextViewuserId = itemView.findViewById(R.id.text_view_userid);
            mTextViewid = itemView.findViewById(R.id.text_view_id);
            mTextViewtitle = itemView.findViewById(R.id.text_view_title);
            mTextViewcompleted = itemView.findViewById(R.id.text_view_completed);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }


}
