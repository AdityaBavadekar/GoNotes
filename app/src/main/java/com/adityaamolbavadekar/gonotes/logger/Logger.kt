package com.adityaamolbavadekar.gonotes.logger

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.adityaamolbavadekar.gonotes.BuildConfig
import com.adityaamolbavadekar.gonotes.logger.Logger.warnLog

object Logger {

    var isDebug: Boolean = false

    /*INITIALISE*/
    fun Application.installLogger() {
        isDebug = BuildConfig.DEBUG
        if (isDebug) debugLog("GoNotes Logger initialised [isDebug=true]")
    }

    /*DEBUG LOGGING*/

    /**
     * Send a [.DEBUG] log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun d(tag: String?, msg: String) {
        if (isDebug) Log.d(tag, msg)
    }

    /**
     * Send a [.DEBUG] log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    fun d(tag: String?, msg: String, tr: Throwable?) {
        if (isDebug) Log.d(tag, msg, tr)
    }

    /**
     * See : [d]
     */
    fun Fragment.debugLog(msg: String) {
        if (isDebug) {
            val name = this.javaClass.simpleName
            d(name,msg)
        }
    }

    /**
     * See : [d]
     */
    fun Context.debugLog(msg: String){
        if (isDebug) {
            val name = this.javaClass.simpleName
            d(name,msg)
        }
    }

    /*INFO LOGGING*/

    /**
     * Send an [.INFO] log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun i(tag: String?, msg: String) {
        if (isDebug) Log.i(tag, msg)
    }

    /**
     * Send a [.INFO] log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    fun i(tag: String?, msg: String, tr: Throwable?) {
        if (isDebug) Log.i(tag, msg, tr)
    }

    /**
     * See : [i]
     */
    fun Fragment.infoLog(msg: String){
        if (isDebug) {
            val name = this.javaClass.simpleName
            i(name,msg)
        }
    }

    /**
     * See : [i]
     */
    fun Context.infoLog(msg: String) {
        if (isDebug) {
            val name = this.javaClass.simpleName
            i(name,msg)
        }
    }

    /*WARNING LOGGING*/

    /**
     * Send a [.WARN] log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun w(tag: String?, msg: String) {
        if (isDebug) Log.w(tag, msg)
    }

    /**
     * Send a [.WARN] log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    fun w(tag: String?, msg: String, tr: Throwable?) {
        if (isDebug) Log.w(tag, msg, tr)
    }

    /*
     * Send a {@link #WARN} log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param tr An exception to log
     */
    fun w(tag: String?, tr: Throwable?) {
        if (isDebug) Log.w(tag, tr)
    }

    /**
     * See : [w]
     */
    fun Fragment.warnLog(msg: String) {
        if (isDebug) {
             val name = this.javaClass.simpleName
            w(name,msg)
        }
    }

    /**
     * See : [w]
     */
    fun Context.warnLog(msg: String) {
        if (isDebug) {
            val name = this.javaClass.simpleName
            w(name,msg)
        }
    }

    /*ERROR LOGGING*/

    /**
     * Send an [.ERROR] log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun e(tag: String?, msg: String) {
        if (isDebug) Log.e(tag, msg)
    }

    /**
     * Send a [.ERROR] log message and log the exception.
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    fun e(tag: String?, msg: String, tr: Throwable?) {
        Log.e(tag, msg, tr)
    }

    /**
     * See : [e]
     */
    fun Fragment.errorLog(msg: String,tr: Throwable?) {
        if (isDebug) {
            val name = this.javaClass.simpleName
            e(name,msg,tr)
        }
    }

    /**
     * See : [e]
     */
    fun Context.errorLog(msg: String,tr: Throwable?) {
        if (isDebug) {
            val name = this.javaClass.simpleName
            e(name,msg,tr)
        }
    }


}