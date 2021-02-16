package com.example.medidorcapacitor;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    //widgets
    Button btnPaired;
    ListView devicelist;
    TextView jtv;
    Bundle bdl;
    //Bluetooth
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Calling widgets
        btnPaired = (Button)findViewById(R.id.button);
        devicelist = (ListView)findViewById(R.id.listView);
        jtv = (TextView) findViewById(R.id.textView);

        //Verificamos si el celular tiene bluetooth
        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if(myBluetooth == null)
        {
            //Muestra un mensaje que el celular no tiene conexión a Bluetooth
            Toast.makeText(getApplicationContext(), "Conexión Bluetooh No Detectada", Toast.LENGTH_LONG).show();

            //finish apk
            finish();
        }
        else if(!myBluetooth.isEnabled())
        {
            //Pregunta al usuario para dar permiso a la conexión Bluetooth
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon,1);
        }
        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                pairedDevicesList();
            }
        });
    }
    //Metodo que muestra una lista de los dispositivos vinculados
    private void pairedDevicesList()
    {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        devicelist.setAdapter(adapter);
        //Llamada al metodo cuando se presiona en la lista de dispositivos vinculados
        devicelist.setOnItemClickListener(myListClickListener);

    }
    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            bdl = new Bundle();
            try
            {
                //Obtenemos la dirección MAC del dispositivo, las ultimos 17 caracteres
                String info = ((TextView) v).getText().toString();
                String address = info.substring(info.length() - 17);
                bdl.putString("ADDRESS", address);
                //Hacemos un intent para llamar a la siguiente actividad
                Intent i = new Intent(MainActivity.this, MedCapacitor.class);
                i.putExtras(bdl);
                //Pasamos la direccion MAC del dispositivo a la siguiente actividad y la iniciamos
                startActivity(i);
            }
            catch (Exception e)
            {
                jtv.setText(String.valueOf(e));
            }

        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflar el menú; esto agrega elementos a la barra de acción si está presente.
        getMenuInflater().inflate(R.menu.menu_device_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.string.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}