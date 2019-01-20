package coffeepartner.complextype.appcompat;


import android.support.v7.widget.RecyclerView;

/**
 * Created by dieyi on 2019/1/20.
 */
public interface ViewBinderFactory {

  <T, V extends RecyclerView.ViewHolder> ViewBinder<T, V> create(ComplexProvider context, Class<? extends T> clazz);
}
