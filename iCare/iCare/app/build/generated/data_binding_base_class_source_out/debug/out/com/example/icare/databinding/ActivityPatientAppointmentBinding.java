// Generated by view binder compiler. Do not edit!
package com.example.icare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.icare.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPatientAppointmentBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button booknowBtn;

  @NonNull
  public final TextView drName;

  @NonNull
  public final ImageView drPicture;

  @NonNull
  public final TextView iCareText;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView specialtiesText;

  private ActivityPatientAppointmentBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button booknowBtn, @NonNull TextView drName, @NonNull ImageView drPicture,
      @NonNull TextView iCareText, @NonNull ImageView imageView,
      @NonNull TextView specialtiesText) {
    this.rootView = rootView;
    this.booknowBtn = booknowBtn;
    this.drName = drName;
    this.drPicture = drPicture;
    this.iCareText = iCareText;
    this.imageView = imageView;
    this.specialtiesText = specialtiesText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPatientAppointmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPatientAppointmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_patient_appointment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPatientAppointmentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.booknow_btn;
      Button booknowBtn = ViewBindings.findChildViewById(rootView, id);
      if (booknowBtn == null) {
        break missingId;
      }

      id = R.id.dr_name;
      TextView drName = ViewBindings.findChildViewById(rootView, id);
      if (drName == null) {
        break missingId;
      }

      id = R.id.dr_picture;
      ImageView drPicture = ViewBindings.findChildViewById(rootView, id);
      if (drPicture == null) {
        break missingId;
      }

      id = R.id.iCare_Text;
      TextView iCareText = ViewBindings.findChildViewById(rootView, id);
      if (iCareText == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.specialties_text;
      TextView specialtiesText = ViewBindings.findChildViewById(rootView, id);
      if (specialtiesText == null) {
        break missingId;
      }

      return new ActivityPatientAppointmentBinding((ConstraintLayout) rootView, booknowBtn, drName,
          drPicture, iCareText, imageView, specialtiesText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}