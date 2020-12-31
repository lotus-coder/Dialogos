package com.example.dialogos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class PestanasMain extends AppCompatActivity {


    private TextView num1,num2;
    private EditText resultado;
    private Button btnResuelve;
    private NotificationManager notificationManager;
    static final String CANAL_ID ="mi_canal";
    static final int NOTIFICACION_ID =1;
    int cont;
    private ListView listaWebs;
    private String [] websNombres = {"google","PC Componentes","PC Gamer",};
    private Webs [] datos = {new Webs("google","https://www.google.com",""),
            new Webs("PC Componentes","https://www.pccomponentes.com",""),
            new Webs("PC Gamer", "https://www.pcgamer.com","")};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pestanas_main);

        //Referencia al control principal TabHost y preparación para su configuración
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        //Objeto de tipo TabSpec para cada una de las pestañas
        //asignación layout correspondiente a la pestaña (setContent())
        //Indicaremos texto y/o icono de la pestaña (setIndicator(texto, icono))
        TabHost.TabSpec spec = tabs.newTabSpec("miTab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("TAB 1", getResources().getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("miTab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("TAB 2", getResources().getDrawable(android.R.drawable.ic_dialog_info));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("miTab3");
        spec.setContent(R.id.tab3);
        //spec.setIndicator("TAB 3", getResources().getDrawable(android.R.drawable.ic_menu_agenda));
        spec.setIndicator("", getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        //Pestaña activa al arrancar la aplicacion
        tabs.setCurrentTab(0);
        cont = 0 ;
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        resultado = findViewById(R.id.resultado);
        btnResuelve = findViewById(R.id.button);
        num1.setText(""+(dameAleatorio(30)));
        num2.setText(""+dameAleatorio(30));
        //Pestaña activa al arrancar la aplicacion
        tabs.setCurrentTab(0);
        ArrayAdapter<Webs> adaptador = new ArrayAdapter<Webs>(this,android.R.layout.simple_spinner_item, datos);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        listaWebs = (ListView) findViewById(R.id.lvLista);

        AdaptadorTitulares adaptadorListView = new AdaptadorTitulares(this, datos);
        listaWebs.setAdapter(adaptadorListView);
        eventos();
    }

    class AdaptadorTitulares extends ArrayAdapter <Webs>{
        public AdaptadorTitulares(@NonNull PestanasMain context, Webs [] dat ) {
            super(context, R.layout.listawebs, dat);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listawebs, null);

            TextView tvURL = (TextView)item.findViewById(R.id.urlWeb);
            tvURL.setText(datos[position].getUrl());

            TextView tvNombre = (TextView)item.findViewById(R.id.nombreWeb);
            tvNombre.setText(datos[position].getNombre());

            ImageView ivRuta = (ImageView) item.findViewById(R.id.imagenWeb);
            ivRuta.setImageResource(datos[position].getImagen());
            return (item);
        }
    }



    private int dameAleatorio(int max){
        return (int)(Math.random() * (max + 1));
    }

    private void eventos() {
        listaWebs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Webs w = (Webs) listaWebs.getItemAtPosition(position);
                Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(w.getUrl()));
                startActivity(intent);
            }
        });
        btnResuelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(resultado.getText().toString()) == Integer.parseInt(num1.getText().toString())+Integer.parseInt(num2.getText().toString())){
                    cont++;
                    System.out.println(cont+"");
                    if (cont == 10){
                        //Creamos notificacion
                        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                        //Creamos el canal. SOLO puede hacerse en dispositivos con ver.8 o más.
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel notificationChannel = new NotificationChannel(CANAL_ID, "Mis notificaciones", NotificationManager.IMPORTANCE_DEFAULT);
                            notificationChannel.setDescription("Descripción del canal");
                            notificationManager.createNotificationChannel(notificationChannel);
                        }
                        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(PestanasMain.this,CANAL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Has acertado").setContentText("10 veces");
                        notificationManager.notify(NOTIFICACION_ID, notificacion.build());
                        PendingIntent intencionPendiente = PendingIntent.getActivity(PestanasMain.this, 0,new Intent(PestanasMain.this, PestanasMain.class), 0);
                        notificacion.setContentIntent(intencionPendiente);
                    }
                }
                resultado.setText("");
                num1.setText(""+dameAleatorio(30));
                num2.setText(""+dameAleatorio(30));
            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Saliendo del programa")
                .setMessage("¿Estas seguro de que quieres salir?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PestanasMain.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}