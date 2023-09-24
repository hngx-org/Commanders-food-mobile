package com.example.freelunch;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class LoadingDialog extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstance){

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.custom_loading_dialog);
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }
}
