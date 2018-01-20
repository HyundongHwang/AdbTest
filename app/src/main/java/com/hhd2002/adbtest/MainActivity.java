package com.hhd2002.adbtest;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hhd20 on 1/19/2018.
 */

public class MainActivity extends AppCompatActivity {
    private EditText _etLog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);

        this.findViewById(R.id.btn_copy_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("adbtest clip", _etLog.getText());
                clipboard.setPrimaryClip(clip);
            }
        });

        this.findViewById(R.id.btn_clear_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _etLog.setText("");
            }
        });

        _etLog = this.findViewById(R.id.et_log);

        this.findViewById(R.id.btn0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _execCmd("whoami");
            }
        });

        this.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _execCmd("ls");
            }
        });

        this.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _execCmd("/system/bin/setprop sys.usb.config adb");
                _execCmd("/system/bin/getprop");
            }
        });
    }

    private void _execCmd(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // Grab the results
            StringBuilder log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line + "\n");
            }

            String oldLog = _etLog.getText().toString();
            _etLog.setText(oldLog + "\n\n" + log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
