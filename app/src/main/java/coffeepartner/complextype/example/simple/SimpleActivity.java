package coffeepartner.complextype.example.simple;

import java.util.Arrays;

import coffeepartner.complextype.androidx.ComplexAdapter;
import coffeepartner.complextype.androidx.ComplexProvider;
import coffeepartner.complextype.example.common.AbstractRvActivity;
import coffeepartner.complextype.example.common.Person;
import coffeepartner.complextype.example.common.PersonViewBinder;

/**
 * Created by dieyi on 2019/1/20.
 */
public class SimpleActivity extends AbstractRvActivity {

  @Override
  public ComplexAdapter createAdapter() {
    ComplexProvider provider = new ComplexProvider.Builder().registerViewBinder(Person.class, new PersonViewBinder()).build();
    ComplexAdapter adapter = new ComplexAdapter(provider);
    adapter.setItems(Arrays.asList(
      new Person("Bob", 25),
      new Person("Alice", 18)
    ));
    return adapter;
  }
}
