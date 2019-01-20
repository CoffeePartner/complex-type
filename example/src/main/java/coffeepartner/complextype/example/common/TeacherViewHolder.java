package coffeepartner.complextype.example.common;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import coffeepartner.complextype.example.R;

/**
 * Created by dieyi on 2019/1/20.
 */
public class TeacherViewHolder extends PersonViewHolder {

  public TextView tvSubject;

  public TeacherViewHolder(@NonNull View itemView) {
    super(itemView);
    tvSubject = itemView.findViewById(R.id.tv_subject);
  }
}
