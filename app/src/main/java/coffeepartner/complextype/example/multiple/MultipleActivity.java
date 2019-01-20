package coffeepartner.complextype.example.multiple;

import java.lang.reflect.Type;
import java.util.Arrays;

import androidx.recyclerview.widget.RecyclerView;
import coffeepartner.complextype.androidx.ComplexAdapter;
import coffeepartner.complextype.androidx.ComplexProvider;
import coffeepartner.complextype.androidx.ViewBinder;
import coffeepartner.complextype.androidx.ViewBinderFactory;
import coffeepartner.complextype.example.common.AbstractRvActivity;
import coffeepartner.complextype.example.common.Parent;
import coffeepartner.complextype.example.common.Person;
import coffeepartner.complextype.example.common.Teacher;
import coffeepartner.complextype.example.common.TeacherViewBinder;

/**
 * Created by dieyi on 2019/1/20.
 */
public class MultipleActivity extends AbstractRvActivity {
  @Override
  public ComplexAdapter createAdapter() {
    ComplexProvider provider = new ComplexProvider.Builder().registerBinderFactory(new ViewBinderFactory() {
      @Override
      public ViewBinder<?, ? extends RecyclerView.ViewHolder> create(ComplexProvider context, Class<?> clazz) {
        if(clazz == Teacher.class) {
          return new TeacherViewBinder();
        }
        return null;
      }
    }).build();
    ComplexAdapter adapter = new ComplexAdapter(provider);
    adapter.setItems(Arrays.asList(
      new Person("Bob", 25),
      new Teacher("Nancy", 30, "English"),
      new Parent("Lucy", 28, new Person[]{
        new Person("Zz", 3)
      })
    ));
    return adapter;
  }
}
