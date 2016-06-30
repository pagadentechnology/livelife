package id.tech.rcslive.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import id.tech.rcslive.activity.R;

/**
 * Created by macbook on 3/28/16.
 */
public class DialogConfirmation extends DialogFragment{
    private SharedPreferences sh;
    Context context;
    String text;
    int from;


    public void setSh(SharedPreferences sh) {
        this.sh = sh;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyLocationDialogTheme);
        builder.setMessage(text);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (from == 0) {
                    sh.edit().clear().commit();
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
//                    Intent intent = new Intent(context, Tech_Generators.class);

//                    startActivity(intent);

                }


            }
        });


        if(from == 0){
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            });

        }

        return builder.create();
    }
}
