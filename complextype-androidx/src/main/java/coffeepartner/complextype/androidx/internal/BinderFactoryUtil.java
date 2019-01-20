package coffeepartner.complextype.androidx.internal;


import coffeepartner.complextype.androidx.ComplexProvider;
import coffeepartner.complextype.androidx.ViewBinder;
import coffeepartner.complextype.androidx.ViewBinderFactory;

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

    @Override
    public ViewBinder<?, ?> create(ComplexProvider context, Class<?> clazz) {
      if (hierarchy ? origin.isAssignableFrom(clazz) : clazz.equals(origin)) {
        return binder;
      }
      return null;
    }
  }
}
