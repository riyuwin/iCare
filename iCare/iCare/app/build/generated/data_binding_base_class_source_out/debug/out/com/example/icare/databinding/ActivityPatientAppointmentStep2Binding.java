// Generated by view binder compiler. Do not edit!
package com.example.icare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.icare.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPatientAppointmentStep2Binding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button appointNowBtn;

  @NonNull
  public final TextView appointmentLabel;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final EditText messageEditText;

  @NonNull
  public final TextView setdateLabel;

  private ActivityPatientAppointmentStep2Binding(@NonNull LinearLayout rootView,
      @NonNull Button appointNowBtn, @NonNull TextView appointmentLabel,
      @NonNull ImageView imageView, @NonNull EditText messageEditText,
      @NonNull TextView setdateLabel) {
    this.rootView = rootView;
    this.appointNowBtn = appointNowBtn;
    this.appointmentLabel = appointmentLabel;
    this.imageView = imageView;
    this.messageEditText = messageEditText;
    this.setdateLabel = setdateLabel;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPatientAppointmentStep2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPatientAppointmentStep2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_patient_appointment_step2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPatientAppointmentStep2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appoint_now_btn;
      Button appointNowBtn = ViewBindings.findChildViewById(rootView, id);
      if (appointNowBtn == null) {
        break missingId;
      }

      id = R.id.appointment_label;
      TextView appointmentLabel = ViewBindings.findChildViewById(rootView, id);
      if (appointmentLabel == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.messageEditText;
      EditText messageEditText = ViewBindings.findChildViewById(rootView, id);
      if (messageEditText == null) {
        break missingId;
      }

      id = R.id.setdate_label;
      TextView setdateLabel = ViewBindings.findChildViewById(rootView, id);
      if (setdateLabel == null) {
        break missingId;
      }

      return new ActivityPatientAppointmentStep2Binding((LinearLayout) rootView, appointNowBtn,
          appointmentLabel, imageView, messageEditText, setdateLabel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
