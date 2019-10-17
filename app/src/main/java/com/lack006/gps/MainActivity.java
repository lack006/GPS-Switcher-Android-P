package com.lack006.gps;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import com.topjohnwu.superuser.Shell;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CheckBox mCheckBox;
    TextView mUninstallTextView;
    Button mButton;
    Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCheckBox = findViewById(R.id.checkBox);
        mUninstallTextView = findViewById(R.id.uninstall_txv);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);


        mSwitch = findViewById(R.id.boot_switch);
        SharedPreferences sharedPreferences = this.getSharedPreferences("config", Context.MODE_PRIVATE);
        mSwitch.setChecked(sharedPreferences.getBoolean("need_boot_switch_off",false));
        mSwitch.setOnCheckedChangeListener((compoundButton, b) -> {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (b) {
                editor.putBoolean("need_boot_switch_off", true);
                editor.apply();
                ComponentName receiver = new ComponentName(this, BootReceiver.class);
                PackageManager pm = this.getPackageManager();
                pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
            } else {
                editor.putBoolean("need_boot_switch_off", false);
                editor.apply();
                ComponentName receiver = new ComponentName(this, BootReceiver.class);
                PackageManager pm = this.getPackageManager();
                pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);


            }
        });

        if (!Shell.rootAccess()) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setCancelable(false);
            alertDialog.setTitle(R.string.app_name);
            alertDialog.setMessage(R.string.need_root);
            alertDialog.setPositiveButton(R.string.exit,
                    (dialog, which) -> finish());

            alertDialog.show();
        }
        mCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                mUninstallTextView.setVisibility(View.VISIBLE);
            } else {
                mUninstallTextView.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button) {
            if (mCheckBox.isChecked()) {
                PackageManager packageManager = getPackageManager();
                packageManager.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            }
            finish();
        }
    }
}
