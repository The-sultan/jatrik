package uy.edu.fing.tsi2.jatrik.android.extras;

import uy.edu.fing.tsi2.jatrik.android.main.DatosUsuario;
import uy.edu.fing.tsi2.jatrik.android.main.LoginActivity;
import uy.edu.fing.tsi2.jatrik.android.main.MainActivity;
import uy.edu.fing.tsi2.jatrik.android.main.R;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GcmIntentService extends IntentService {
	   
	  private NotificationManager mNotificationManager;
	  NotificationCompat.Builder builder;
	 
	  public GcmIntentService() {
	    super("GcmIntentService");
	  }
	  /**
	   * Metodo que recupera el mensaje de la notificación contenida en el intent
	   * para luego mostrar dicho mensaje en la barra de notificaciones del dispositivo
	   */
	  @Override
	  protected void onHandleIntent(Intent intent) {
	    GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
	 
	    String messageType = gcm.getMessageType(intent);
	    Bundle extras = intent.getExtras();
	 
	    if (!extras.isEmpty()) {
	      if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
	        //Se visualiza el mendaje en la barra de notificaciones
	        sendNotification(extras.getString("mensaje"));
	      }
	    }
	 
	    GcmBroadcastReceiver.completeWakefulIntent(intent);
	  }
	 
	  /**
	   * Este metodo lo que hace es visualizar una notificación en la barra de
	   * notificaciones con el mensaje pasado por parametro
	   *
	   * @param msg mensaje que se muestra en la notificación
	   */
	  private void sendNotification(String msg) {
	    mNotificationManager = (NotificationManager) this
	        .getSystemService(Context.NOTIFICATION_SERVICE);
	 
	    PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
	        new Intent(this, MainActivity.class), 0);
	 
	    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
	        this).setSmallIcon(R.drawable.jatrikchico)
	        .setContentTitle("Jatrik")
	        .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
	        .setContentText(msg);
	    mBuilder.setContentIntent(contentIntent);
	    
	    int idNotif = ((DatosUsuario)GcmIntentService.this.getApplication()).getUltimaNotificacion();
	    mNotificationManager.notify(idNotif, mBuilder.build());
	    ((DatosUsuario)GcmIntentService.this.getApplication()).setUltimaNotificacion(idNotif+1);
	  }
	 
	}