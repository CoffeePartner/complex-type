package coffeepartner.complextype.appcompat;

/**
 * Created by dieyi on 2019/1/20.
 */
public interface ViewBinderFactory {

  <T> ViewBinder<T, ?> create(ComplexProvider context, Class<T> clazz);
}
