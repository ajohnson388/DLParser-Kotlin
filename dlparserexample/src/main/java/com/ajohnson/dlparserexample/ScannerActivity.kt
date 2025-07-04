package com.ajohnson.dlparserexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ajohnson.dlparserexample.ui.theme.DLParserKotlinTheme
import com.ajohnson.dlparserkotlin.parsers.DLParser
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class ScannerActivity : ComponentActivity() {

    private lateinit var scanner: GmsBarcodeScanner
    private val logTag = "DLParser Example Output"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_PDF417)
            .enableAutoZoom()
            .build()
        scanner = GmsBarcodeScanning.getClient(this, options)

        setContent {
            DLParserKotlinTheme {
                val result = remember { mutableStateOf("") }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = { scan() }) {
                            Text("Start scanner")
                        }
                    }
                }
            }
        }
    }

    fun scan() {
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                barcode.rawValue?.let {
                    val parser = DLParser(it)
                    val license = parser.parse()
                    val licenseString = license.toString()
                    Log.i(logTag, "License barcode data: $it")
                    Log.i(logTag, "License model: $licenseString")
                    Toast.makeText(this, "License found. Check logs.", LENGTH_LONG).show()
                }
            }
    }
}
