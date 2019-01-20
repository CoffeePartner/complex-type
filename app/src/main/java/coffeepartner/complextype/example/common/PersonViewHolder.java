package coffeepartner.complextype.example.common;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import coffeepartner.complextype.example.R;

/**
 * Created by dieyi on 2019/1/20.
 */
public class PersonViewHolder extends RecyclerView.ViewHolder {


  public TextView tvName;
  public TextView tvAge;


  public PersonViewHolder(@NonNull View itemView) {
    super(itemView);
    tvName = itemView.findViewById(R.id.tv_name);
    tvAge = itemView.findViewById(R.id.tv_age);
  }
}
