package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.services

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.engineio.client.Transport
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.gmail.dudarenka.vitali.guestbooktestapp.R
import com.gmail.dudarenka.vitali.guestbooktestapp.presentation.screen.guestbook.GuestBookActivity
import org.json.JSONObject
import java.net.URISyntaxException
import java.util.*


class SocketService : Service() {
    private var userId: String? = null
    private var token: String? = null
    override fun onBind(intent: Intent?): IBinder {
        throw  UnsupportedOperationException("Not yet implemented")
    }

    lateinit var mSocket: Socket

    var that: SocketService = this
    lateinit var ctx: Context

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        token = intent!!.getStringExtra("token")
        userId = intent.getStringExtra("id")
        try {
            mSocket = IO.socket("http://pusher.cpl.by:6020")
            mSocket.connect()
            mSocket.on("public-push", onNewComment)
            mSocket.on("private-user.$userId", onTransport)


        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        ctx = applicationContext
    }

    private val onTransport = Emitter.Listener { args ->
        val transport = args[0] as Transport
        transport.on(Transport.EVENT_REQUEST_HEADERS) { args ->
            val headers = args[0] as MutableMap<String, List<String>>
            headers["Authorization"] = Arrays.asList(token) as List<String>
        }.on(Transport.EVENT_RESPONSE_HEADERS) { }
    }

    private fun createNotification(title: String, text: String) {
        val intent = Intent(this, GuestBookActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(0, mBuilder.build())

    }

    private val onNewComment = Emitter.Listener { args ->
        val data = args[0] as JSONObject

        that.createNotification("New message", data.toString())
    }


    override fun onDestroy() {
        mSocket.disconnect()
        mSocket.off("public-push", onNewComment)
        mSocket.off("private-push.$userId", onTransport)

    }
}
