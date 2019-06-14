package br.com.a10_12_06_2019

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val urlImagem = "https://www.pavablog.com/wp-content/uploads/2016/01/cat.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btNotificacao.setOnClickListener {
            enviarNotificacao("Samsumg Ocean A10", "Ocean")
        }

        btImagemPicasso.setOnClickListener {
            Picasso.get().load(urlImagem).into(imageView)
        }

        btImagemGlide.setOnClickListener {
            Glide.with(this).load(urlImagem).into(imageView)
        }

    }


    fun enviarNotificacao(texto:String, titulo:String){

        val CHANNEL_ID = "SAMSUMG_OCEAN"
        val ID_NOTIFICACAO = 1

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val canalNotificacao = NotificationChannel(CHANNEL_ID, "Samsumg Ocean", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Canal de notificação Samsumg Ocean"
            }


            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(canalNotificacao)

        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon( BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher) )
            .setContentText(texto)
            .setContentTitle(titulo)
            .setDefaults(Notification.DEFAULT_VIBRATE)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificacao = builder.build()

        val notificationManager = NotificationManagerCompat.from(this)

        notificationManager.notify(ID_NOTIFICACAO, notificacao)

    }

}
