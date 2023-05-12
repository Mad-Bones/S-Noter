package com.bsn.s_notera.tools.buttons;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bsn.s_notera.R;

/** BOTONES CONTACTO
 * INFERIOR
 * ICONO */
public class AddContactButtons {
    Activity myActivity;
    TextView text_title;
    TextView button_mailto, button_whats, button_github;
    LinearLayout button_container;

    public AddContactButtons(
            Activity myActivity,
            String title,
            String mailAddres,
            String whatsNumber,
            String githubRepo) {
        this.myActivity = myActivity;

        this.button_container = (myActivity).findViewById(R.id.container_buttons);


        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout content_text = inflater.inflate(R.layout.component_contact_buttons, null).findViewById(R.id.button_comp);

        text_title = content_text.findViewById(R.id.title_buttons);
        text_title.setText(title);

        button_mailto = content_text.findViewById(R.id.button_mailto);
        button_mailto.setOnClickListener(view -> {
            //This does't work whit: Outlook Lite
            //Works whit: G-mail
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mailAddres});
            i.putExtra(Intent.EXTRA_SUBJECT, myActivity.getString(R.string.feedback));
            try {
                myActivity.startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(myActivity, R.string.no_email_apps_intalled, Toast.LENGTH_SHORT).show();
            }
        });
        button_whats = content_text.findViewById(R.id.button_whatsapp);
        button_whats.setOnClickListener(view -> {
            //If WhatsApp is not installed on the device, it will open a web browser to handle this
            String url = "https://api.whatsapp.com/send?phone=" + whatsNumber;
            Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
            whatsappIntent.setData(Uri.parse(url));
            myActivity.startActivity(whatsappIntent);
        });
        button_github = content_text.findViewById(R.id.button_github);
        button_github.setOnClickListener(view -> {
            //Allows you to choose between your web browsers
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubRepo));
            myActivity.startActivity(browserIntent);
        });

        button_container.addView(content_text);
    }

}
