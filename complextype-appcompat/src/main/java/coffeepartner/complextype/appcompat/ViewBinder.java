package coffeepartner.complextype.appcompat;

import android.support.annotation.IntRange;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dieyi on 2019/1/20.
 */
public abstract class ViewBinder<T, VH extends ViewHolder> {

  public static final int MAX_TYPE = 128;


  /**
   * invoke when adapter attached to the view binder
   *
   * @param adapter the attached adapter
   */
  public void onAttachAdapter(ComplexAdapter adapter) {
  }

  /**
   * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
   * an item.
   * <p>
   * This new ViewHolder should be constructed with a new View that can represent the items
   * of the given type. You can either create a new View manually or inflate it from an XML
   * layout file.
   * <p>
   * The new ViewHolder will be used to display items of the adapter using
   * {@link #onBindViewHolder(ViewHolder, T, int, List)}. Since it will be re-used to display
   * different items in the data set, it is a good idea to cache references to sub views of
   * the View to avoid unnecessary {@link View#findViewById(int)} calls.
   *
   * @param inflater LayoutInflater
   * @param parent   The ViewGroup into which the new View will be added after it is bound to
   *                 an adapter position.
   * @return A new ViewHolder that holds a View
   * @see #onCreateViewHolder(LayoutInflater, ViewGroup, int)
   */
  public abstract VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

  /**
   * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
   * an item.
   * <p>
   * This new ViewHolder should be constructed with a new View that can represent the items
   * of the given type. You can either create a new View manually or inflate it from an XML
   * layout file.
   * <p>
   * The new ViewHolder will be used to display items of the adapter using
   * {@link #onBindViewHolder(ViewHolder, T, int, List)}. Since it will be re-used to display
   * different items in the data set, it is a good idea to cache references to sub views of
   * the View to avoid unnecessary {@link View#findViewById(int)} calls.
   *
   * @param inflater LayoutInflater
   * @param parent   The ViewGroup into which the new View will be added after it is bound to
   *                 an adapter position.
   * @param viewType The view type of the new View.
   * @return A new ViewHolder that holds a View of the given view type.
   * @see #getItemViewType(Object, int)
   * @see #onBindViewHolder(ViewHolder, Object, int, List)
   */
  public VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
    if (viewType == 0) {
      return onCreateViewHolder(inflater, parent);
    }
    throw new IllegalStateException("Override the method to create your view holder!");
  }

  /**
   * Return the view type of the item at <code>position</code> for the purposes
   * of view recycling.
   *
   * <p>The default implementation of this method returns 0, making the assumption of
   * a single view type for the adapter. Unlike ListView adapters, types need not
   * be contiguous. Consider using id resources to uniquely identify item view types.
   *
   * @param item     the data item
   * @param position position in adapter
   * @return integer value identifying the type of the view needed to represent the item at
   * <code>position</code>. Type codes need not be contiguous.
   */
  @IntRange(from = 0, to = MAX_TYPE - 1)
  public int getItemViewType(T item, int position) {
    return 0;
  }

  /**
   * Called by RecyclerView to display the data at the specified position. This method should
   * update the contents of the {@link ViewHolder#itemView} to reflect the given item.
   * <p>
   * If you need the position of an item later on (e.g. in a click listener), use
   * {@code ViewHolder#getAdapterPosition()} which will have the updated adapter position.
   * <p>
   * Override {@code onBindViewHolder(ViewHolder, Object, List)} instead if your ItemViewBinder
   * can handle efficient partial bind.
   *
   * @param holder   The ViewHolder which should be updated to represent the contents of the
   *                 given item in the items data set.
   * @param item     The data item.
   * @param position position in adapter
   */
  public abstract void onBindViewHolder(VH holder, T item, int position);

  /**
   * Called by RecyclerView to display the data at the specified position. This method
   * should update the contents of the {@link ViewHolder#itemView} to reflect the item at
   * the given position.
   * <p>
   * Note that unlike {@link android.widget.ListView}, RecyclerView will not call this method
   * again if the position of the item changes in the data set unless the item itself is
   * invalidated or the new position cannot be determined. For this reason, you should only
   * use the <code>position</code> parameter while acquiring the related data item inside
   * this method and should not keep a copy of it. If you need the position of an item later
   * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
   * have the updated adapter position.
   * <p>
   * Partial bind vs full bind:
   * <p>
   * The payloads parameter is a merge list from {@link Adapter#notifyItemChanged(int, Object)} or
   * {@link Adapter#notifyItemRangeChanged(int, int, Object)}.  If the payloads list is not empty,
   * the ViewHolder is currently bound to old data and Adapter may run an efficient partial
   * update using the payload info.  If the payload is empty,  Adapter must run a full bind.
   * Adapter should not assume that the payload passed in notify methods will be received by
   * onBindViewHolder().  For example when the view is not attached to the screen, the
   * payload in notifyItemChange() will be simply dropped.
   *
   * @param holder   The ViewHolder which should be updated to represent the contents of the
   *                 item at the given position in the data set.
   * @param item     The data item to query
   * @param position position in adapter
   * @param payloads A non-null list of merged payloads. Can be empty list if requires full
   *                 update.
   */
  public void onBindViewHolder(VH holder, T item, int position, List<Object> payloads) {
    onBindViewHolder(holder, item, position);
  }

  /**
   * Return the stable ID for the item at <code>position</code>. If {@link Adapter#hasStableIds()}
   * would return false this method should return {@link RecyclerView#NO_ID}. The default implementation
   * of this method returns {@link RecyclerView#NO_ID}.
   *
   * @param item the data item to query
   * @return the stable ID of the item at position
   */
  public long getItemId(T item) {
    return RecyclerView.NO_ID;
  }

  /**
   * Called when a view created by this adapter has been recycled.
   *
   * <p>A view is recycled when a {@link RecyclerView.LayoutManager} decides that it no longer
   * needs to be attached to its parent {@link RecyclerView}. This can be because it has
   * fallen out of visibility or a set of cached views represented by views still
   * attached to the parent RecyclerView. If an item view has large or expensive data
   * bound to it such as large bitmaps, this may be a good place to release those
   * resources.</p>
   * <p>
   * RecyclerView calls this method right before clearing ViewHolder's internal data and
   * sending it to RecycledViewPool. This way, if ViewHolder was holding valid information
   * before being recycled, you can call {@link ViewHolder#getAdapterPosition()} to get
   * its adapter position.
   *
   * @param holder The ViewHolder for the view being recycled
   */
  public void onViewRecycled(VH holder) {
  }

  /**
   * Called by the RecyclerView if a ViewHolder created by this Adapter cannot be recycled
   * due to its transient state. Upon receiving this callback, Adapter can clear the
   * animation(s) that effect the View's transient state and return <code>true</code> so that
   * the View can be recycled. Keep in mind that the View in question is already removed from
   * the RecyclerView.
   * <p>
   * In some cases, it is acceptable to recycle a View although it has transient state. Most
   * of the time, this is a case where the transient state will be cleared in
   * {@link Adapter#onBindViewHolder(ViewHolder, int)} call when View is rebound to a new position.
   * For this reason, RecyclerView leaves the decision to the Adapter and uses the return
   * value of this method to decide whether the View should be recycled or not.
   * <p>
   * Note that when all animations are created by {@link RecyclerView.ItemAnimator}, you
   * should never receive this callback because RecyclerView keeps those Views as children
   * until their animations are complete. This callback is useful when children of the item
   * views create animations which may not be easy to implement using an {@link RecyclerView.ItemAnimator}.
   * <p>
   * You should <em>never</em> fix this issue by calling
   * <code>holder.itemView.setHasTransientState(false);</code> unless you've previously called
   * <code>holder.itemView.setHasTransientState(true);</code>. Each
   * <code>View.setHasTransientState(true)</code> call must be matched by a
   * <code>View.setHasTransientState(false)</code> call, otherwise, the state of the View
   * may become inconsistent. You should always prefer to end or cancel animations that are
   * triggering the transient state instead of handling it manually.
   *
   * @param holder The ViewHolder containing the View that could not be recycled due to its
   *               transient state.
   * @return True if the View should be recycled, false otherwise. Note that if this method
   * returns <code>true</code>, RecyclerView <em>will ignore</em> the transient state of
   * the View and recycle it regardless. If this method returns <code>false</code>,
   * RecyclerView will check the View's transient state again before giving a final decision.
   * Default implementation returns false.
   */
  public boolean onFailedToRecycleView(VH holder) {
    return false;
  }

  /**
   * Called when a view created by this adapter has been attached to a window.
   *
   * <p>This can be used as a reasonable signal that the view is about to be seen
   * by the user. If the adapter previously freed any resources in
   * {@link #onViewDetachedFromWindow(ViewHolder) onViewDetachedFromWindow}
   * those resources should be restored here.</p>
   *
   * @param holder Holder of the view being attached
   */
  public void onViewAttachedToWindow(VH holder) {
  }

  /**
   * Called when a view created by this adapter has been detached from its window.
   *
   * <p>Becoming detached from the window is not necessarily a permanent condition;
   * the consumer of an Adapter's views may choose to cache views offscreen while they
   * are not visible, attaching and detaching them as appropriate.</p>
   *
   * @param holder Holder of the view being detached
   */
  public void onViewDetachedFromWindow(VH holder) {
  }
}
