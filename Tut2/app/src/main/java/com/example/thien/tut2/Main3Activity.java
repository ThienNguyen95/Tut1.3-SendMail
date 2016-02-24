package com.example.thien.tut2;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Session;


public class Main3Activity extends Activity implements OnClickListener{

    private static Button Send_Email;
    private static Button Reset;
    private String mFirstname,mLastname,mEmail,mPhone,mSalary;
    Context context=null;
    Session session =null;
    ProgressDialog pdialog =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mFirstname=getIntent().getStringExtra(Main2Activity.EXTRA_FNAME);
        mLastname=getIntent().getStringExtra(Main2Activity.EXTRA_LNAME);
        mEmail=getIntent().getStringExtra(Main2Activity.EXTRA_EMAIL);
        mPhone=getIntent().getStringExtra(Main2Activity.EXTRA_PHONE);
        mSalary=getIntent().getStringExtra(Main2Activity.EXTRA_SALARY);
        context = this;
        Send_Email= (Button)findViewById(R.id.BT3_1);
        Send_Email.setOnClickListener(this);
        Reset = (Button)findViewById(R.id.BT3_2);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Main3Activity.this,MainActivity.class);
                back.setFlags(back.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);

            }
        });
    }


    private void sendEmail() {
//        String bodyemail = mFirstname+"_"+mLastname+"\n"+mPhone+"\n"+mSalary+"dollars";
//        Intent i = new Intent(Intent.ACTION_SEND);
//        i.setData(Uri.parse("mailto:"));
//        i.putExtra(Intent.EXTRA_SUBJECT, "User's registration info");
//        i.putExtra(Intent.EXTRA_EMAIL,mEmail);
//        i.putExtra(Intent.EXTRA_TEXT, bodyemail);
//        i.setType("message/rfc822");
//        startActivity(Intent.createChooser(i, "Choose an Email client :"));

    }

    @Override
    public void onClick(View v) {
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("id user","pass user"); //Enter your ID and PASSWORD of gmail here
            }
        });
        pdialog = ProgressDialog.show(context, "", "Sending mail...", true);
        RetreiveFeedTask task=new RetreiveFeedTask();
        task.execute();
    }
    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                String bodyemail = mFirstname+"_"+mLastname+"\n"+mPhone+"\n"+mSalary+"dollars";
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("id user"));//Enter your ID
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("id user"));//Enter your id
                message.setSubject("Test");
                message.setContent(bodyemail, "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();

            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }
}
