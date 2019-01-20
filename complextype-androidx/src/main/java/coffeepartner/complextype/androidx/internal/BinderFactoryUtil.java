package coffeepartner.complextype.androidx.internal;


import androidx.recyclerview.widget.RecyclerView;
import coffeepartner.complextype.androidx.ComplexProvider;
import coffeepartner.complextype.androidx.ViewBinder;
import coffeepartner.complextype.androidx.ViewBinderFactory;

/**
 * Created by dieyi on 2019/1/20.
 */
public class BinderFactoryUtil {

  public static <T> ViewBinderFactory newSingleFactory(final Class<? extends T> origin, final ViewBinder<T, ?> binder) {
    return new SingleBinderFactory<>(origin, binder, false);
  }

  public static <T> ViewBinderFactory newHierarchyFactory(final Class<? extends T> origin, final ViewBinder<T, ?> binder) {
    return new SingleBinderFactory<>(origin, binder, true);
  }


  static class SingleBinderFactory<T> implements ViewBinderFactory {

    private final Class<? extends T> origin;
    private final ViewBinder<T, ?> binder;
    private final boolean hierarchy;

    SingleBinderFactory(Class<? extends T> origin, ViewBinder<T, ?> binder, boolean hierarchy) {
      this.origin = origin;
      this.binder = binder;
      this.hierarchy = hierarchy;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <TT, V extends RecyclerView.ViewHolder> ViewBinder<TT, V> create(ComplexProvider context, Class<? extends TT> clazz) {
      if (hierarchy ? origin.isAssignableFrom(clazz) : clazz.equals(origin)) {
        return (ViewBinder<TT, V>) binder;
      }
      return null;
    }
  }
}
