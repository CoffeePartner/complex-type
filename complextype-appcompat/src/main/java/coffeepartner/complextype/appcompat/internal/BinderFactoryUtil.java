package coffeepartner.complextype.appcompat.internal;

import android.support.annotation.NonNull;

import coffeepartner.complextype.appcompat.ComplexProvider;
import coffeepartner.complextype.appcompat.ViewBinder;
import coffeepartner.complextype.appcompat.ViewBinderFactory;


/**
 * Created by dieyi on 2019/1/20.
 */
public class BinderFactoryUtil {

  public static <T> ViewBinderFactory newSingleFactory(final Class<T> origin, final ViewBinder<T, ?> binder) {
    return new SingleBinderFactory(origin, binder, false);
  }

  public static <T> ViewBinderFactory newHierarchyFactory(final Class<T> origin, final ViewBinder<T, ?> binder) {
    return new SingleBinderFactory(origin, binder, true);
  }


  static class SingleBinderFactory implements ViewBinderFactory {

    private final Class<?> origin;
    private final ViewBinder<?, ?> binder;
    private final boolean hierarchy;

    SingleBinderFactory(Class<?> origin, ViewBinder<?, ?> binder, boolean hierarchy) {
      this.origin = origin;
      this.binder = binder;
      this.hierarchy = hierarchy;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> ViewBinder<T, ?> create(@NonNull ComplexProvider context, @NonNull Class<T> clazz) {
      if (hierarchy ? origin.isAssignableFrom(clazz) : clazz.equals(origin)) {
        return (ViewBinder<T, ?>) binder;
      }
      return null;
    }
  }
}
