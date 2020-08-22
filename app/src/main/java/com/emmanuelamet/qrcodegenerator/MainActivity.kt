package com.emmanuelamet.qrcodegenerator

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.emmanuelamet.qrcodegenerator.qr.code.generator.QRCodeGen
import kotlinx.android.synthetic.main.activity_main.*

private const val PERMISSION_REQUEST = 10
class MainActivity : AppCompatActivity() {
    val qr = QRCodeGen()
    private var permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private lateinit var context:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        try{
            generateQRCode()
        }catch (e:Exception){
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun generateQRCode(){

        val bitmap = qr.encodeAsBitmap("ID: ${ID}\nNAME: ${NAME}\nCLOUD: ${CLOUD}",400, 400, context)
        Toast.makeText(this, getString(R.string.qr_code_generated_successful_message), Toast.LENGTH_LONG).show()
        iv_qr_code.setImageBitmap(bitmap)
        /*
            The code below will display the encoded credentials to a textView but in your production project do not show them.
         */
        encode_text.text = "Encoded credentials\n\nID: ${ID}\nNAME: ${NAME}\nCLOUD: ${CLOUD}\nLIBRARY: $LIBRARY\nDEVELOPER: $DEVELOPER"
    }

    companion object{
        val ID = "65823894032"
        val NAME = "Kotlin Android"
        val CLOUD = "GCP, AWS, Azure"
        val LIBRARY = "ZXing"
        val DEVELOPER = "Emmanuel"
    }

}