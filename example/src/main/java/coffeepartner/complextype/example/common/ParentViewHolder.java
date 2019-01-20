package coffeepartner.complextype.example.common;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import coffeepartner.complextype.example.R;

public class ParentViewHolder extends PersonViewHolder {

  public TextView tvCount;
  public int count = 0;

  public ParentViewHolder(@NonNull View itemView) {
    super(itemView);
    tvCount = itemView.findViewById(R.id.tv_children_number);
  }
}
