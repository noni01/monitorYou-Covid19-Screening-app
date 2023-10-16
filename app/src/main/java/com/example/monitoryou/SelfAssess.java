package com.example.monitoryou;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SelfAssess extends AppCompatActivity {
    List<ModelClass> modelClassList;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_self);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid = user.getUid();

        private View getActivity recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference = database.getReference("question");
        FirebaseRecyclerOptions<ModelClass> options = new FirebaseRecyclerOptions.Builder<ModelClass>()
                .setQuery(databaseReference, ModelClass.class)
                .build();
        FirebaseRecyclerAdapter<ModelClass, ViewHolder_Question> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelClass, ViewHolder_Question>(options) {
                    @NonNull
                    @Override
                    public ViewHolder_Question onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.quiz_qs_layout,parent,false);
                        return new ViewHolder_Question(view);
                    }

                    @Override
                    protected void onBindViewHolder( @NonNull ViewHolder_Question holder, int position, @NonNull ModelClass model ) {
                        holder.setitem(getActivity(),model.getQuetions(),model.getO1(),model.getO2());

                    }

                    };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
                }

    private FragmentActivity getActivity() {
    }
}
