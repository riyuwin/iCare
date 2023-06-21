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

public final class ActivityPatientAppointment3Binding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button appointNowBtn;

  @NonNull
  public final TextView appointmentLabel;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView personalDetails;

  @NonNull
  public final EditText setdateAppointment;

  @NonNull
  public final Button setdateBtn;

  @NonNull
  public final TextView setdateLabel;

  @NonNull
  public final EditText settimeAppointment;

  @NonNull
  public final Button settimeBtn;

  @NonNull
  public final TextView settimeLabel;

  private ActivityPatientAppointment3Binding(@NonNull LinearLayout rootView,
      @NonNull Button appointNowBtn, @NonNull TextView appointmentLabel,
      @NonNull ImageView imageView, @NonNull TextView personalDetails,
      @NonNull EditText setdateAppointment, @NonNull Button setdateBtn,
      @NonNull TextView setdateLabel, @NonNull EditText settimeAppointment,
      @NonNull Button settimeBtn, @NonNull TextView settimeLabel) {
    this.rootView = rootView;
    this.appointNowBtn = appointNowBtn;
    this.appointmentLabel = appointmentLabel;
    this.imageView = imageView;
    this.personalDetails = personalDetails;
    this.setdateAppointment = setdateAppointment;
    this.setdateBtn = setdateBtn;
    this.setdateLabel = setdateLabel;
    this.settimeAppointment = settimeAppointment;
    this.settimeBtn = settimeBtn;
    this.settimeLabel = settimeLabel;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPatientAppointment3Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPatientAppointment3Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_patient_appointment3, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPatientAppointment3Binding bind(@NonNull View rootView) {
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

      id = R.id.personal_details;
      TextView personalDetails = ViewBindings.findChildViewById(rootView, id);
      if (personalDetails == null) {
        break missingId;
      }

      id = R.id.setdate_appointment;
      EditText setdateAppointment = ViewBindings.findChildViewById(rootView, id);
      if (setdateAppointment == null) {
        break missingId;
      }

      id = R.id.setdate_btn;
      Button setdateBtn = ViewBindings.findChildViewById(rootView, id);
      if (setdateBtn == null) {
        break missingId;
      }

      id = R.id.setdate_label;
      TextView setdateLabel = ViewBindings.findChildViewById(rootView, id);
      if (setdateLabel == null) {
        break missingId;
      }

      id = R.id.settime_appointment;
      EditText settimeAppointment = ViewBindings.findChildViewById(rootView, id);
      if (settimeAppointment == null) {
        break missingId;
      }

      id = R.id.settime_btn;
      Button settimeBtn = ViewBindings.findChildViewById(rootView, id);
      if (settimeBtn == null) {
        break missingId;
      }

      id = R.id.settime_label;
      TextView settimeLabel = ViewBindings.findChildViewById(rootView, id);
      if (settimeLabel == null) {
        break missingId;
      }

      return new ActivityPatientAppointment3Binding((LinearLayout) rootView, appointNowBtn,
          appointmentLabel, imageView, personalDetails, setdateAppointment, setdateBtn,
          setdateLabel, settimeAppointment, settimeBtn, settimeLabel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}