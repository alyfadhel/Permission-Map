package com.example.permissionmap


import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity :AppCompatActivity() {
    fun showDialoge(title:String?=null,
                    message:String,
                    posActionName:String?=null,
                    posAction: DialogInterface.OnClickListener?=null,
                    negActionName:String?=null,
                    negAction: DialogInterface.OnClickListener?=null){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setPositiveButton(posActionName,posAction)
        builder.setNegativeButton(negActionName,negAction)
        builder.show()
    }
    fun showDialoge(
        titleId:Int?=null,
        messageId: String,
        posActionName: String? =null,
        posAction: DialogInterface.OnClickListener?=null,
        negActionName:Int?=null,
        negAction: DialogInterface.OnClickListener?=null){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(messageId)

        if(titleId!=null)
            builder.setTitle(titleId)
        if (posActionName!=null)
            builder.setPositiveButton(posActionName,posAction)
        if (negActionName!=null)
            builder.setNegativeButton(negActionName,negAction)

        builder.show()
    }

//    fun makeSnackBar(
//        message: String,
//        actionSting:String?=null,
//        click: View.OnClickListener?=null){
//
//        val snackbar =
//            Snackbar.make(root,message, Snackbar.LENGTH_LONG)
//        if(actionSting!=null)
//            snackbar.setAction(actionSting,click)
//        snackbar.show()
//    }
//    fun makeSnackBar(messageId: Int,
//                     actionSting:Int?=null,
//                     click: View.OnClickListener?=null){
//
//        val snackbar = Snackbar.make(root,messageId, Snackbar.LENGTH_LONG)
//        if(actionSting!=null)
//            snackbar.setAction(actionSting,click)
//        snackbar.show()
//    }

    fun makeToast(message:String){
        Toast.makeText(this,
            message, Toast.LENGTH_LONG).show()
    }
    fun makeToast(messageId:Int){
        Toast.makeText(this,
            messageId, Toast.LENGTH_LONG).show()
    }

}