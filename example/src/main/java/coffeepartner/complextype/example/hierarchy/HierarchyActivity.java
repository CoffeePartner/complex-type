package coffeepartner.complextype.example.hierarchy;

import java.util.Arrays;

import coffeepartner.complextype.androidx.ComplexAdapter;
import coffeepartner.complextype.androidx.ComplexProvider;
import coffeepartner.complextype.example.common.AbstractRvActivity;
import coffeepartner.complextype.example.common.Parent;
import coffeepartner.complextype.example.common.Person;
import coffeepartner.complextype.example.common.PersonViewBinder;
import coffeepartner.complextype.example.common.Teacher;

/**
 * Created by dieyi on 2019/1/20.
 */
public class HierarchyActivity extends AbstractRvActivity {
  @Override
  public ComplexAdapter createAdapter() {
    ComplexProvider provider = new ComplexProvider
      .Builder()
      .registerHierarchyBinder(Person.class, new PersonViewBinder())
      .build();
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
