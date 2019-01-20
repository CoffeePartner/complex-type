package coffeepartner.complextype.androidx;

/**
 * Created by dieyi on 2019/1/20.
 */
public interface ViewBinderFactory {

  ViewBinder<?, ?> create(ComplexProvider context, Class<?> clazz);
}
