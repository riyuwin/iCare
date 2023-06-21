// Generated by view binder compiler. Do not edit!
package com.example.icare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.icare.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLandingPageBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNavigation;

  @NonNull
  public final FrameLayout container;

  @NonNull
  public final DrawerLayout drawerLayout;

  @NonNull
  public final FrameLayout fragmentContainer;

  @NonNull
  public final NavigationView navView;

  @NonNull
  public final Toolbar toolbar;

  private ActivityLandingPageBinding(@NonNull RelativeLayout rootView,
      @NonNull BottomNavigationView bottomNavigation, @NonNull FrameLayout container,
      @NonNull DrawerLayout drawerLayout, @NonNull FrameLayout fragmentContainer,
      @NonNull NavigationView navView, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.bottomNavigation = bottomNavigation;
    this.container = container;
    this.drawerLayout = drawerLayout;
    this.fragmentContainer = fragmentContainer;
    this.navView = navView;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLandingPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLandingPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_landing_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLandingPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottom_navigation;
      BottomNavigationView bottomNavigation = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigation == null) {
        break missingId;
      }

      id = R.id.container;
      FrameLayout container = ViewBindings.findChildViewById(rootView, id);
      if (container == null) {
        break missingId;
      }

      id = R.id.drawer_layout;
      DrawerLayout drawerLayout = ViewBindings.findChildViewById(rootView, id);
      if (drawerLayout == null) {
        break missingId;
      }

      id = R.id.fragment_container;
      FrameLayout fragmentContainer = ViewBindings.findChildViewById(rootView, id);
      if (fragmentContainer == null) {
        break missingId;
      }

      id = R.id.nav_view;
      NavigationView navView = ViewBindings.findChildViewById(rootView, id);
      if (navView == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityLandingPageBinding((RelativeLayout) rootView, bottomNavigation, container,
          drawerLayout, fragmentContainer, navView, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
