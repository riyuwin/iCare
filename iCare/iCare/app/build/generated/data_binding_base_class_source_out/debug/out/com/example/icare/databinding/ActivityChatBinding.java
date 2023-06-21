// Generated by view binder compiler. Do not edit!
package com.example.icare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.icare.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityChatBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView chatRecyclerView;

  @NonNull
  public final View headerBackground;

  @NonNull
  public final AppCompatImageView imageBack;

  @NonNull
  public final AppCompatImageView imageInfo;

  @NonNull
  public final EditText inputMessage;

  @NonNull
  public final FrameLayout layoutSend;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final TextView textName;

  @NonNull
  public final View viewBackground;

  @NonNull
  public final View viewSupporter;

  private ActivityChatBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView chatRecyclerView, @NonNull View headerBackground,
      @NonNull AppCompatImageView imageBack, @NonNull AppCompatImageView imageInfo,
      @NonNull EditText inputMessage, @NonNull FrameLayout layoutSend,
      @NonNull ProgressBar progressBar, @NonNull TextView textName, @NonNull View viewBackground,
      @NonNull View viewSupporter) {
    this.rootView = rootView;
    this.chatRecyclerView = chatRecyclerView;
    this.headerBackground = headerBackground;
    this.imageBack = imageBack;
    this.imageInfo = imageInfo;
    this.inputMessage = inputMessage;
    this.layoutSend = layoutSend;
    this.progressBar = progressBar;
    this.textName = textName;
    this.viewBackground = viewBackground;
    this.viewSupporter = viewSupporter;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChatBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChatBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_chat, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChatBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.chatRecyclerView;
      RecyclerView chatRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (chatRecyclerView == null) {
        break missingId;
      }

      id = R.id.headerBackground;
      View headerBackground = ViewBindings.findChildViewById(rootView, id);
      if (headerBackground == null) {
        break missingId;
      }

      id = R.id.imageBack;
      AppCompatImageView imageBack = ViewBindings.findChildViewById(rootView, id);
      if (imageBack == null) {
        break missingId;
      }

      id = R.id.imageInfo;
      AppCompatImageView imageInfo = ViewBindings.findChildViewById(rootView, id);
      if (imageInfo == null) {
        break missingId;
      }

      id = R.id.inputMessage;
      EditText inputMessage = ViewBindings.findChildViewById(rootView, id);
      if (inputMessage == null) {
        break missingId;
      }

      id = R.id.layoutSend;
      FrameLayout layoutSend = ViewBindings.findChildViewById(rootView, id);
      if (layoutSend == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.textName;
      TextView textName = ViewBindings.findChildViewById(rootView, id);
      if (textName == null) {
        break missingId;
      }

      id = R.id.viewBackground;
      View viewBackground = ViewBindings.findChildViewById(rootView, id);
      if (viewBackground == null) {
        break missingId;
      }

      id = R.id.viewSupporter;
      View viewSupporter = ViewBindings.findChildViewById(rootView, id);
      if (viewSupporter == null) {
        break missingId;
      }

      return new ActivityChatBinding((ConstraintLayout) rootView, chatRecyclerView,
          headerBackground, imageBack, imageInfo, inputMessage, layoutSend, progressBar, textName,
          viewBackground, viewSupporter);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
