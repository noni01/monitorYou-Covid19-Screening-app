package com.example.monitoryou;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder_Question extends RecyclerView.ViewHolder {
    TextView questions;
    Button ansA, ansB;
    public ViewHolder_Question( @NonNull View itemView ) {
        super(itemView);
    }

    public void setitem( FragmentActivity activity,String Questions,String o1,String o2){
        questions=itemView.findViewById(R.id.tvQestion);
        ansA=itemView.findViewById(R.id.tvAnswerA);
        ansB=itemView.findViewById(R.id.tvAnswerB);
    }
}
