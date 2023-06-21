// Generated by view binder compiler. Do not edit!
package com.example.icare.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.icare.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RecyclerviewDoctorAppointmentItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView appointmentDateTv;

  @NonNull
  public final TextView appointmentTimeTv;

  @NonNull
  public final CardView doctorAppointmentCd;

  @NonNull
  public final TextView doctorNameTv;

  private RecyclerviewDoctorAppointmentItemBinding(@NonNull CardView rootView,
      @NonNull TextView appointmentDateTv, @NonNull TextView appointmentTimeTv,
      @NonNull CardView doctorAppointmentCd, @NonNull TextView doctorNameTv) {
    this.rootView = rootView;
    this.appointmentDateTv = appointmentDateTv;
    this.appointmentTimeTv = appointmentTimeTv;
    this.doctorAppointmentCd = doctorAppointmentCd;
    this.doctorNameTv = doctorNameTv;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static RecyclerviewDoctorAppointmentItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RecyclerviewDoctorAppointmentItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.recyclerview_doctor_appointment_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RecyclerviewDoctorAppointmentItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appointment_date_tv;
      TextView appointmentDateTv = ViewBindings.findChildViewById(rootView, id);
      if (appointmentDateTv == null) {
        break missingId;
      }

      id = R.id.appointment_time_tv;
      TextView appointmentTimeTv = ViewBindings.findChildViewById(rootView, id);
      if (appointmentTimeTv == null) {
        break missingId;
      }

      CardView doctorAppointmentCd = (CardView) rootView;

      id = R.id.doctor_name_tv;
      TextView doctorNameTv = ViewBindings.findChildViewById(rootView, id);
      if (doctorNameTv == null) {
        break missingId;
      }

      return new RecyclerviewDoctorAppointmentItemBinding((CardView) rootView, appointmentDateTv,
          appointmentTimeTv, doctorAppointmentCd, doctorNameTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
