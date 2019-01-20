package coffeepartner.complextype.example.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import coffeepartner.complextype.androidx.ViewBinder;
import coffeepartner.complextype.example.R;

/**
 * Created by dieyi on 2019/1/20.
 */
public class TeacherViewBinder extends ViewBinder<Teacher, TeacherViewHolder> {

  @Override
  public TeacherViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
    return new TeacherViewHolder(inflater.inflate(R.layout.teacher_vh, parent, false));
  }

  @Override
  public void onBindViewHolder(TeacherViewHolder holder, Teacher item, int position) {
    holder.tvName.setText("Name: " + item.getName());
    holder.tvAge.setText("Age:" + item.getAge());
    holder.tvSubject.setText("Subject: " + item.getSubject());
  }
}
