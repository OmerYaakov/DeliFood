package com.example.delifood


import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class FileHandler {

    fun saveImageToExternalStorage(context: Context, bitmap: Bitmap, filename: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            // Get the directory for the external storage
            val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            Log.d("FileHandler", "Directory: $directory")

            // Create a file object for the specified filename
            val file = File(directory, filename)
            Log.d("FileHandler", "File: $file")

            // Create a file output stream
            fileOutputStream = FileOutputStream(file)

            // Compress the bitmap to PNG format and write it to the file
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()

            // Optionally, you may want to notify the system that a new file was created
            MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), null, null)

            // Optionally, you may want to toast a message indicating the image was saved successfully
            Toast.makeText(context, "Image saved successfully", Toast.LENGTH_SHORT).show()
            Log.d("FileHandler", "Image saved successfully")
        } catch (e: IOException) {
            e.printStackTrace()
            // Handle IOException
        } finally {
            // Close the output stream
            try {
                fileOutputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}