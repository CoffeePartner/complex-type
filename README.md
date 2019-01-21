# complex-type

[ ![Download](https://api.bintray.com/packages/dieyi/maven/complextype-androidx/images/download.svg) ](https://bintray.com/dieyi/maven/complextype-androidx/_latestVersion)[![codebeat badge](https://codebeat.co/badges/31dfce19-b609-4c3e-9d13-8dfe34dd9f87)](https://codebeat.co/projects/github-com-coffeepartner-complex-type-master)

An Android library to create complex items easily in RecyclerView.

## Usage

### Install

```groovy
// androidx
implementation 'coffeepartner.complextype:complextype-androidx:1.0.0'
// appcompat
implementation 'coffeepartner.complextype:complextype-appcompat:1.0.0'
```

### Example


```java

// Like Gson TypeFactory, build the provider
ComplexProvider provider = new ComplexProvider.Builder()
  .registerHierarchyBinder(Person.class, new PersonViewBinder())
  .registerBinderFactory(new ViewBinderFactory() {
    @SuppressWarnings("unchecked")
    @Override
    public <T, V extends RecyclerView.ViewHolder> ViewBinder<T, V> create(ComplexProvider context, Class<? extends T> clazz) {
      if (clazz == Teacher.class) {
        return (ViewBinder<T, V>) new DelegationTeacherViewBinder(context.nextViewBinder(this, Person.class));
      } else if (clazz == Parent.class) {
        return (ViewBinder<T, V>) new DelegationParentViewBinder(context.nextViewBinder(this, Person.class));
      }
      return null;
    }
  })
  .build();

// The Adapter with provider
ComplexAdapter adapter = new ComplexAdapter(provider);
adapter.setItems(Arrays.asList(
  new Person("Bob", 25),
  new Teacher("Nancy", 30, "English"),
  new Parent("Lucy", 28, new Person[]{
    new Person("Zz", 3),
    new Person("Yy", 2)
  })
));
RecyclerView rv;
// set to RecyclerView
rv.setAdapter(adapter);
```

More details, see [**example**](example).

## License

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

