// Generated by view binder compiler. Do not edit!
package com.example.icare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.icare.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView c1;

  @NonNull
  public final CardView c2;

  @NonNull
  public final CardView c3;

  @NonNull
  public final CardView c4;

  @NonNull
  public final EditText searchdoctor;

  @NonNull
  public final TextView whatDoYouNeed;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView, @NonNull CardView c1,
      @NonNull CardView c2, @NonNull CardView c3, @NonNull CardView c4,
      @NonNull EditText searchdoctor, @NonNull TextView whatDoYouNeed) {
    this.rootView = rootView;
    this.c1 = c1;
    this.c2 = c2;
    this.c3 = c3;
    this.c4 = c4;
    this.searchdoctor = searchdoctor;
    this.whatDoYouNeed = whatDoYouNeed;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.c1;
      CardView c1 = ViewBindings.findChildViewById(rootView, id);
      if (c1 == null) {
        break missingId;
      }

      id = R.id.c2;
      CardView c2 = ViewBindings.findChildViewById(rootView, id);
      if (c2 == null) {
        break missingId;
      }

      id = R.id.c3;
      CardView c3 = ViewBindings.findChildViewById(rootView, id);
      if (c3 == null) {
        break missingId;
      }

      id = R.id.c4;
      CardView c4 = ViewBindings.findChildViewById(rootView, id);
      if (c4 == null) {
        break missingId;
      }

      id = R.id.searchdoctor;
      EditText searchdoctor = ViewBindings.findChildViewById(rootView, id);
      if (searchdoctor == null) {
        break missingId;
      }

      id = R.id.what_do_you_need;
      TextView whatDoYouNeed = ViewBindings.findChildViewById(rootView, id);
      if (whatDoYouNeed == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, c1, c2, c3, c4, searchdoctor,
          whatDoYouNeed);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
