// Generated by view binder compiler. Do not edit!
package com.example.icare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public final class ActivityDoctorFindingsBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView appointmentLabel;

  @NonNull
  public final Button confirmBtn;

  @NonNull
  public final TextView drName;

  @NonNull
  public final EditText drNameEdittext;

  @NonNull
  public final EditText findingsEdittext;

  @NonNull
  public final TextView recommendation;

  @NonNull
  public final EditText recommendationEdittext;

  @NonNull
  public final TextView setdateLabel;

  private ActivityDoctorFindingsBinding(@NonNull LinearLayout rootView,
      @NonNull TextView appointmentLabel, @NonNull Button confirmBtn, @NonNull TextView drName,
      @NonNull EditText drNameEdittext, @NonNull EditText findingsEdittext,
      @NonNull TextView recommendation, @NonNull EditText recommendationEdittext,
      @NonNull TextView setdateLabel) {
    this.rootView = rootView;
    this.appointmentLabel = appointmentLabel;
    this.confirmBtn = confirmBtn;
    this.drName = drName;
    this.drNameEdittext = drNameEdittext;
    this.findingsEdittext = findingsEdittext;
    this.recommendation = recommendation;
    this.recommendationEdittext = recommendationEdittext;
    this.setdateLabel = setdateLabel;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDoctorFindingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDoctorFindingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_doctor_findings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDoctorFindingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appointment_label;
      TextView appointmentLabel = ViewBindings.findChildViewById(rootView, id);
      if (appointmentLabel == null) {
        break missingId;
      }

      id = R.id.confirm_btn;
      Button confirmBtn = ViewBindings.findChildViewById(rootView, id);
      if (confirmBtn == null) {
        break missingId;
      }

      id = R.id.dr_name;
      TextView drName = ViewBindings.findChildViewById(rootView, id);
      if (drName == null) {
        break missingId;
      }

      id = R.id.dr_name_edittext;
      EditText drNameEdittext = ViewBindings.findChildViewById(rootView, id);
      if (drNameEdittext == null) {
        break missingId;
      }

      id = R.id.findings_edittext;
      EditText findingsEdittext = ViewBindings.findChildViewById(rootView, id);
      if (findingsEdittext == null) {
        break missingId;
      }

      id = R.id.recommendation;
      TextView recommendation = ViewBindings.findChildViewById(rootView, id);
      if (recommendation == null) {
        break missingId;
      }

      id = R.id.recommendation_edittext;
      EditText recommendationEdittext = ViewBindings.findChildViewById(rootView, id);
      if (recommendationEdittext == null) {
        break missingId;
      }

      id = R.id.setdate_label;
      TextView setdateLabel = ViewBindings.findChildViewById(rootView, id);
      if (setdateLabel == null) {
        break missingId;
      }

      return new ActivityDoctorFindingsBinding((LinearLayout) rootView, appointmentLabel,
          confirmBtn, drName, drNameEdittext, findingsEdittext, recommendation,
          recommendationEdittext, setdateLabel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}