package coffeepartner.complextype.example.delegation;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import coffeepartner.complextype.androidx.ViewBinder;
import coffeepartner.complextype.example.R;
import coffeepartner.complextype.example.common.Parent;
import coffeepartner.complextype.example.common.ParentViewHolder;
import coffeepartner.complextype.example.common.Person;
import coffeepartner.complextype.example.common.PersonViewHolder;

/**
 * Created by dieyi on 2019/1/20.
 */
public class DelegationParentViewBinder extends ViewBinder<Parent, ParentViewHolder> {

  private final ViewBinder<Person, PersonViewHolder> personViewBinder;

  public DelegationParentViewBinder(ViewBinder<Person, PersonViewHolder> personViewBinder) {
    this.personViewBinder = personViewBinder;
  }

  @Override
  public ParentViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
    return new ParentViewHolder(inflater.inflate(R.layout.parent_vh, parent, false));
  }

  @Override
  public void onBindViewHolder(ParentViewHolder holder, Parent item, int position) {
    personViewBinder.onBindViewHolder(holder, item, position);

    LinearLayout ll = (LinearLayout) holder.itemView;
    if (holder.count > 0) {
      ll.removeViews(3, holder.count);
    }


    final int count = item.getChildren().length;
    for (int i = 0; i < count; i++) {
      Person child = item.getChildren()[i];

      PersonViewHolder personViewHolder = personViewBinder.onCreateViewHolder(LayoutInflater.from(ll.getContext()), ll);
      LinearLayout personView = (LinearLayout) personViewHolder.itemView;
      // Just a example.
      personView.setPadding(
        personView.getPaddingLeft() + 100,
        personView.getPaddingTop(),
        personView.getPaddingRight(),
        personView.getPaddingBottom()
      );
      ll.addView(personViewHolder.itemView);
      personViewBinder.onBindViewHolder(personViewHolder, child, i);
    }

    holder.count = count;
    holder.tvCount.setText("Children number: " + item.getChildren().length);
  }
}
