package coffeepartner.complextype.example.delegation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import coffeepartner.complextype.androidx.ViewBinder;
import coffeepartner.complextype.example.R;
import coffeepartner.complextype.example.common.Person;
import coffeepartner.complextype.example.common.PersonViewHolder;
import coffeepartner.complextype.example.common.Teacher;
import coffeepartner.complextype.example.common.TeacherViewHolder;

/**
 * Created by dieyi on 2019/1/20.
 */
public class DelegationTeacherViewBinder extends ViewBinder<Teacher, TeacherViewHolder> {


  private final ViewBinder<Person, PersonViewHolder> personViewBinder;

  public DelegationTeacherViewBinder(ViewBinder<Person, PersonViewHolder> personViewBinder) {
    this.personViewBinder = personViewBinder;
  }

  @Override
  public TeacherViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
    return new TeacherViewHolder(inflater.inflate(R.layout.teacher_vh, parent, false));
  }

  @Override
  public void onBindViewHolder(TeacherViewHolder holder, Teacher item, int position) {
    personViewBinder.onBindViewHolder(holder, item, position);
    holder.tvSubject.setText("Subject: " + item.getSubject());
  }
}
