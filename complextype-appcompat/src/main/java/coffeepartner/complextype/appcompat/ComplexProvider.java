package coffeepartner.complextype.appcompat;


import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import coffeepartner.complextype.appcompat.internal.BinderFactoryUtil;

import static coffeepartner.complextype.appcompat.internal.Util.requireNonNull;


/**
 * Created by dieyi on 2019/1/20.
 */
public class ComplexProvider {


  private final List<ViewBinderFactory> factories;
  private final int raw;

  ComplexProvider(Builder builder) {
    this.raw = builder.factories.size();

    List<ViewBinderFactory> factories = new ArrayList<>(builder.factories.size() + builder.hierarchyFactories.size());
    factories.addAll(builder.factories);
    factories.addAll(builder.hierarchyFactories);

    this.factories = Collections.unmodifiableList(factories);
  }

  public Builder newBuilder() {
    return new Builder(this);
  }

  public <T, V extends RecyclerView.ViewHolder> ViewBinder<T, V> nextViewBinder(@Nullable ViewBinderFactory skipPast, Class<? extends T> clazz) {
    int start = skipPast == null ? 0 : factories.indexOf(skipPast) + 1;
    for (int i = start; i < factories.size(); i++) {
      ViewBinder<T, V> binder = factories.get(i).create(this, clazz);
      if (binder != null) {
        return binder;
      }
    }

    StringBuilder builder = new StringBuilder("Could not locate view binder for ")
      .append(clazz)
      .append(".\n");
    if (skipPast != null) {
      builder.append("  Skipped:");
      for (int i = 0; i < start; i++) {
        builder.append("\n   * ").append(factories.get(i).getClass().getName());
      }
      builder.append('\n');
    }
    builder.append("  Tried:");
    for (int i = start, count = factories.size(); i < count; i++) {
      builder.append("\n   * ").append(factories.get(i).getClass().getName());
    }
    throw new IllegalArgumentException(builder.toString());
  }

  public <T, V extends RecyclerView.ViewHolder> ViewBinder<T, V> viewBinder(Class<? extends T> clazz) {
    return nextViewBinder(null, clazz);
  }


  public static final class Builder {

    private final List<ViewBinderFactory> factories;
    private final List<ViewBinderFactory> hierarchyFactories;

    public Builder() {
      factories = new ArrayList<>();
      hierarchyFactories = new ArrayList<>();
    }

    Builder(ComplexProvider recycler) {
      factories = new ArrayList<>(recycler.factories.subList(0, recycler.raw));
      hierarchyFactories = new ArrayList<>(recycler.factories.subList(recycler.raw, recycler.factories.size()));
    }


    public <T> Builder registerViewBinder(Class<? extends T> clazz, ViewBinder<T, ?> binder) {
      factories.add(BinderFactoryUtil.newSingleFactory(requireNonNull(clazz), requireNonNull(binder)));
      return this;
    }

    public Builder registerBinderFactory(ViewBinderFactory factory) {
      factories.add(requireNonNull(factory));
      return this;
    }

    public <T> Builder registerHierarchyBinder(Class<? extends T> clazz, ViewBinder<T, ?> binder) {
      hierarchyFactories.add(BinderFactoryUtil.newHierarchyFactory(requireNonNull(clazz), requireNonNull(binder)));
      return this;
    }

    public ComplexProvider build() {
      return new ComplexProvider(this);
    }
  }

}
