package coffeepartner.complextype.example.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import coffeepartner.complextype.androidx.ViewBinder;
import coffeepartner.complextype.example.R;

/**
 * Created by dieyi on 2019/1/20.
 */
public class ParentViewBinder extends ViewBinder<Parent, ParentViewHolder> {

  @Override
  public ParentViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
    return new ParentViewHolder(inflater.inflate(R.layout.parent_vh, parent, false));
  }

  @Override
  public void onBindViewHolder(ParentViewHolder holder, Parent item, int position) {
    holder.tvName.setText("Name: " + item.getName());
    holder.tvAge.setText("Age:" + item.getAge());
    holder.tvCount.setText("Children number: " + item.getChildren().length);
  }
}
