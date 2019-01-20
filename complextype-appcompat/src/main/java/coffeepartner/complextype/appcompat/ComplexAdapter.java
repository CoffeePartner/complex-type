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
  private final BinderContainer<Object> container;
  private List<?> items = Collections.emptyList();

  public ComplexAdapter(ComplexProvider provider) {
    container = new BinderContainer<>(provider);
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

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    int pos = viewType / ViewBinder.MAX_TYPE;
    int type = viewType % ViewBinder.MAX_TYPE;
    return container.getBinderByTypeIndex(pos)
      .onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent, type);
  }


  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
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
  class BinderContainer<T> {

    private final ComplexProvider provider;
    private List<ViewBinder<T, ?>> binders = new ArrayList<>();
    private List<Class<T>> classes = new ArrayList<>();

    BinderContainer(ComplexProvider provider) {
      this.provider = requireNonNull(provider);
    }

    int itemToType(int position, T data) {
      //noinspection unchecked
      Class<T> clazz = (Class<T>) data.getClass();
      int index = classes.indexOf(clazz);
      if (index < 0) {
        ViewBinder<T, ?> binder = provider.viewBinder(clazz);
        index = classes.size();
        classes.add(clazz);
        binders.add(binder);
        binder.onAttachAdapter(ComplexAdapter.this);
      }
      return index * ViewBinder.MAX_TYPE + binders.get(index).getItemViewType(data, position);
    }

    ViewBinder<T, RecyclerView.ViewHolder> getBinderByTypeIndex(int pos) {
      return (ViewBinder<T, RecyclerView.ViewHolder>) binders.get(pos);
    }

    ViewBinder<T, RecyclerView.ViewHolder> getBinderByClass(Class clazz) {
      return getBinderByTypeIndex(classes.indexOf(clazz));
    }
  }
}
