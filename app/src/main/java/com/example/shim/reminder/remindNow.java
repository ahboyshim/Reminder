package com.example.shim.reminder;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by shim on 21/3/2015.
 */
public class remindNow extends Activity{

    final Context context = this;
    TextView info;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       AlertDialog alertDialog = new AlertDialog.Builder(this).create();


        alertDialog.setTitle("Remind Me");
        alertDialog.setMessage(MainActivity.EXTRA_MESSAGE);
        alertDialog.setIcon(R.drawable.ic_launcher);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which){



            }

        });

        alertDialog.show();

     }

}
