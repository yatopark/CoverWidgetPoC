package com.example.coverwidgetpoc

import android.app.ActivityOptions
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class FlipCoverProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { appWidgetId ->
            val applicationContext = context.applicationContext

            val remoteViews =
                RemoteViews(context.packageName, R.layout.flip_widget_init_layout).apply {
                    setOnClickPendingIntent(
                        R.id.startActivityButton,
                        PendingIntent.getActivity(
                            applicationContext,
                            0,
                            Intent(context, FlipCoverActivity::class.java),
                            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT,
                            ActivityOptions.makeBasic().apply {
                                launchDisplayId = 1
                            }.toBundle()
                        ),
                    )
                }

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }
}