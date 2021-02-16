package com.example.ledbluetooth;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import android.text.method.ScrollingMovementMethod;

public class LedControl extends AppCompatActivity {
    Button btnOn, btnOff, btnDis;
    TextView tvtMensaje;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    Handler bluetoothIn;
    final int handlerState = 0;
    private ConnectedThread MyConexionBT;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    Bundle bdl;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent newint = getIntent();
        bdl = getIntent().getExtras();
        setContentView(R.layout.activity_led_control);
        btnOn = findViewById(R.id.button2);
        btnOff = findViewById(R.id.button3);
        btnDis = findViewById(R.id.button4);
        tvtMensaje = findViewById(R.id.textView4);
        tvtMensaje.setMovementMethod(new ScrollingMovementMethod());  //Esta función nos permitirá hacer scroll para ver todos los datos
        address = bdl.getString("ADDRESS"); //recibimos la dirección MAC del dispositivo
        try
        {

            new ConnectBT().execute(); //Llamamos a la clase ConnectBT
        }
        catch (Exception e)
        {
            tvtMensaje.append("\n\n" + String.valueOf(e));
        }



        //commands to be sent to bluetooth
        btnOn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                turnOnLed();      //Llamamos al metodo para prender el LED
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                turnOffLed();   //Llamamos al metodo para apagar el LED
            }
        });

        btnDis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Disconnect(); //Cerramos la conexión
            }
        });

    }

    private void Disconnect()
    {
        if (btSocket!=null) //Si el socket ya esta creado
        {
            try
            {
                btSocket.close(); //Cerramos la conexión
            }
            catch (IOException e)
            { msg("Error");}
        }
        finish(); //Regresamos al MainActivity

    }
    //Metodo para prender el LED
    private void turnOffLed()
    {
        if (btSocket!=null)//Si el socket ya esta creado
        {
            MyConexionBT.write("0");//Enviamos un '0'
            tvtMensaje.setText("LED APAGADO");
        }
    }
    //Metodo para apagar el LED
    private void turnOnLed()
    {
        if (btSocket!=null)//Si el socket ya esta creado
        {
            MyConexionBT.write("1");//Enviamos un '1'
            tvtMensaje.setText("LED PRENDIDO");
        }
    }


    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_led_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true;

        @Override
        //Se invoca en el subproceso de la interfaz de usuario antes de que se ejecute la tarea
        //Metodo para mostrar una barra de progreso en la interfaz de usuario
        protected void onPreExecute()
        {
            try {
                progress = ProgressDialog.show(LedControl.this, "Conectando...", "Porfavor espere!");  //Muestra un cuadro de progreso
            }
            catch (Exception e)
            {
                tvtMensaje.append("\n\n"+ String.valueOf(e));
            }

        }
        //Mientras el cuadro de progreso esta en pantalla, la conexión es hecha en segundo plano.
        @Override
        protected Void doInBackground(Void... devices)
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//Obtiene el dispositivo bluetooth
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//Se coneta a la direccion MAC de dispositivo
                    btSocket = dispositivo.createRfcommSocketToServiceRecord(myUUID);//Creamos una conexión RFCOMM (SPP)
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (Exception e)
            {
                ConnectSuccess = false;//Si el intento de conexión fue fallida, no se cierra la aplicación
                tvtMensaje.append("\n\n"+String.valueOf(e));

            }

            try
            {
                MyConexionBT = new ConnectedThread(btSocket);
                MyConexionBT.start();
            }
            catch (Exception e)
            {
                tvtMensaje.append("\n\n"+ String.valueOf(e));
            }

            return null;
        }
        //Despues del metodo doInBackground, onPosExcute muestra los resultados
        //Si la conexion fue exitosa o no
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("¡Conexión Fallida! Intente de Nuevo");
                finish();
            }
            else
            {
                msg("¡Conexión Exitosa!");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
    //Crea la clase que permite crear el evento de conexion y enviar datos al dispositivo
    private class ConnectedThread extends Thread{
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket){
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e){
                tvtMensaje.append("\n\n" + String.valueOf(e) );
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;

        }
        public void run(){
            byte[] mmBuffer = new byte[256];
            int numBytes; //Bytes regresados de la lectura

            while (true){
                try{
                    numBytes = mmInStream.read(mmBuffer);
                    String readMessage = new String(mmBuffer, 0, numBytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, numBytes, -1, readMessage).sendToTarget();
                } catch (IOException e){
                    break;
                }
            }
        }
        //Envio de la Trama
        public void write(String input){
            try {
                mmOutStream.write(input.getBytes());
            } catch (IOException e){
                //Si es no posible enviar datos se cierra la conexión
                finish();
            }
        }
    }
}
