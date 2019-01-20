package coffeepartner.complextype.appcompat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static coffeepartner.complextype.appcompat.internal.Util.requireNonNull;


/**
 * Created by dieyi on 2019/1/20.
 */
public class ComplexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private final BinderContainer container;
  private List<?> items = Collections.emptyList();

  public ComplexAdapter(ComplexProvider provider) {
    container = new BinderContainer(provider);
  }


  public ComplexAdapter setItems(List<?> items) {
    this.items = requireNonNull(items);
    return this;
  }

  private Object getItem(int pos) {
    return getItems().get(pos);
  }

  public List<?> getItems() {
    return items;
  }

  @Override
  public int getItemViewType(int position) {
    return container.itemToType(position, getItem(position));
  }

  @Override
  public int getItemCount() {
    return getItems().size();
  }

  @Override
  public long getItemId(int position) {
    Object item = getItem(position);
    return container.getBinderByClass(item.getClass())
      .getItemId(item);
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    int pos = viewType / ViewBinder.MAX_TYPE;
    int type = viewType % ViewBinder.MAX_TYPE;
    return container.getBinderByTypeIndex(pos)
      .onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent, type);
  }


  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    onBindViewHolder(holder, position, Collections.emptyList());
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
    Object item = getItem(position);
    container.getBinderByClass(item.getClass())
      .onBindViewHolder(holder, item, position, payloads);
  }

  @Override
  public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
    container.getBinderByTypeIndex(holder.getItemViewType() / ViewBinder.MAX_TYPE)
      .onViewRecycled(holder);
  }

  @Override
  public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
    return container.getBinderByTypeIndex(holder.getItemViewType() / ViewBinder.MAX_TYPE)
      .onFailedToRecycleView(holder);
  }

  @Override
  public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
    container.getBinderByTypeIndex(holder.getItemViewType() / ViewBinder.MAX_TYPE)
      .onViewAttachedToWindow(holder);
  }

  @Override
  public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
    container.getBinderByTypeIndex(holder.getItemViewType() / ViewBinder.MAX_TYPE)
      .onViewDetachedFromWindow(holder);
  }

  @SuppressWarnings("unchecked")
  class BinderContainer {

    private final ComplexProvider provider;
    private List<ViewBinder<Object, RecyclerView.ViewHolder>> binders = new ArrayList<>();
    private List<Class<?>> classes = new ArrayList<>();

    BinderContainer(ComplexProvider provider) {
      this.provider = requireNonNull(provider);
    }

    int itemToType(int position, Object data) {
      Class<?> clazz = data.getClass();
      int index = classes.indexOf(clazz);
      if (index < 0) {
        ViewBinder<Object, RecyclerView.ViewHolder> binder = (ViewBinder<Object, RecyclerView.ViewHolder>) provider.viewBinder(clazz);
        index = classes.size();
        classes.add(clazz);
        binders.add(binder);
        binder.onAttachAdapter(ComplexAdapter.this);
      }
      return index * ViewBinder.MAX_TYPE + binders.get(index).getItemViewType(data, position);
    }

    ViewBinder<Object, RecyclerView.ViewHolder> getBinderByTypeIndex(int pos) {
      return binders.get(pos);
    }

    ViewBinder<Object, RecyclerView.ViewHolder> getBinderByClass(Class clazz) {
      return getBinderByTypeIndex(classes.indexOf(clazz));
    }
  }
}
