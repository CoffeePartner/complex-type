package coffeepartner.complextype.example.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import coffeepartner.complextype.androidx.ViewBinder;
import coffeepartner.complextype.example.R;

/**
 * Created by dieyi on 2019/1/20.
 */
public class PersonViewBinder extends ViewBinder<Person, PersonViewHolder> {
  @Override
  public PersonViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
    return new PersonViewHolder(inflater.inflate(R.layout.person_vh, parent, false));
  }

  @Override
  public void onBindViewHolder(PersonViewHolder holder, Person item, int position) {
    holder.tvName.setText("Name: " + item.getName());
    holder.tvAge.setText("Age:" + item.getAge());
  }
}
