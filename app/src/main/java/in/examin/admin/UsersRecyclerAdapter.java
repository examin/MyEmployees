package in.examin.admin;

/**
 * Created by examin on 7/10/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;
    Context ctx;
    public UsersRecyclerAdapter(List<User> listUsers,Context ctx)
    {
        this.listUsers = listUsers;
        this.ctx=ctx;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);
        return new UserViewHolder(itemView);
    }
//    public  UsersRecyclerAdapter(Context ctx)
//    {
//       this.ctx=ctx;
//    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {

        holder.textViewName.setText(listUsers.get(position).getName());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewAddress.setText(listUsers.get(position).getAddress());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id=  (listUsers.get(position).getId());
                String name=  (listUsers.get(position).getName());
                Intent i =  new Intent(ctx,UpdateHandlers.class);
                Bundle extras = new Bundle();

                extras.putInt("selectedIt",id);
                extras.putString("name",name);
                i.putExtras(extras);
                ctx.startActivity(i);


            }
        });
            }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewAddress;
        CardView cardView;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewAddress = (AppCompatTextView) view.findViewById(R.id.textViewAddress);
            cardView = (CardView) itemView.findViewById(R.id.updateuser);



        }
    }


}