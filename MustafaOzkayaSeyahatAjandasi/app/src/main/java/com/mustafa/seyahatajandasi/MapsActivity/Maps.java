package com.mustafa.seyahatajandasi.MapsActivity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mustafa.seyahatajandasi.Details;
import com.mustafa.seyahatajandasi.Model.MapsModel;
import com.mustafa.seyahatajandasi.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Maps extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    LocationManager locationManager;
    LocationListener locationListener;

    Geocoder geocoder;

    SQLiteDatabase database;
    SQLiteStatement sqLiteStatement;

    String dosyaId = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Maps.this, Details.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("id", dosyaId);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        dosyaId = intent.getStringExtra("fileid");


        if (info.matches("new")) {

            locationManager = (LocationManager) Maps.this.getSystemService(LOCATION_SERVICE);


            locationListener = new LocationListener() {

                @Override
                public void onLocationChanged(@NonNull Location location) {

                    SharedPreferences sharedPreferences = Maps.this.getSharedPreferences("com.mustafa.seyahatajandasi.MapsActivity", MODE_PRIVATE);
                    boolean controll1 = sharedPreferences.getBoolean("trackBool", false);
                    if (!controll1) {
                        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(userLocation).title("Konumum"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
                        sharedPreferences.edit().putBoolean("trackBool", true).apply();
                    }

                }

            };

            // İzinler aldık
            if (ContextCompat.checkSelfPermission(Maps.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(Maps.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(Maps.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                if (locationManager != null) {
                    Location locationLast = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (locationLast != null) {
                        LatLng latLng = new LatLng(locationLast.getLatitude(), locationLast.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    }


                }


            }


        } else {

            // SQLite kaydedilen konumu getirilecek...
            Intent veriAlma = getIntent();
            MapsModel model = (MapsModel) veriAlma.getSerializableExtra("maps");
            double latitude = model.getLatitude();
            double longitude = model.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title(model.getAddress()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            if (requestCode == 1) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    Intent intent = getIntent();
                    String info = intent.getStringExtra("info");
                    if (info.matches("new")) {
                        if (locationManager != null) {
                            Location locationLast = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (locationLast != null) {
                                LatLng latLng = new LatLng(locationLast.getLatitude(), locationLast.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(latLng));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            }

                        }

                    } else {

                        // SQLite kaydedilen konumu getirilecek...

                        Intent veriAlma = getIntent();
                        MapsModel model = (MapsModel) veriAlma.getSerializableExtra("maps");
                        double latitude = model.getLatitude();
                        double longitude = model.getLongitude();
                        LatLng latLng = new LatLng(latitude, longitude);
                        mMap.addMarker(new MarkerOptions().position(latLng).title(model.getAddress()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


                    }

                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {


        String address = "";
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addressList != null) {
                if (addressList.size() > 0) {
                    if (addressList.get(0).getThoroughfare() != null) {
                        address += addressList.get(0).getThoroughfare() + " ";
                    }
                    if (addressList.get(0).getSubThoroughfare() != null) {
                        address += addressList.get(0).getSubThoroughfare() + " ";
                    }
                    if (addressList.get(0).getCountryName() != null) {
                        address += addressList.get(0).getCountryName() + " ";
                    }

                }
            } else {
                address = "Adres Bulunamadı";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title(address));

        double latitudeUser = latLng.latitude;
        double longitudeUser = latLng.longitude;
        MapsModel mapsModel = new MapsModel(address, latitudeUser, longitudeUser);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Emin Misiniz?");
        builder.setMessage(mapsModel.getAddress());
        builder.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dataSave(mapsModel);

            }
        });
        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(Maps.this, "İptal Edildi..", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

        // ***********************  Güncellemek isterse ********************************
        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        String ID = intent.getStringExtra("listId");
        if (info.matches("old")) {

            String addressOLD = "";
            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                if (addressList != null) {
                    if (addressList.size() > 0) {
                        if (addressList.get(0).getThoroughfare() != null) {
                            addressOLD += addressList.get(0).getThoroughfare() + " ";
                        }
                        if (addressList.get(0).getSubThoroughfare() != null) {
                            addressOLD += addressList.get(0).getSubThoroughfare() + " ";
                        }
                        if (addressList.get(0).getCountryName() != null) {
                            addressOLD += addressList.get(0).getCountryName() + " ";
                        }

                    }
                } else {
                    addressOLD = "Adres Bulunamadı";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng).title(address));

            double latitudeUser_old = latLng.latitude;
            double longitudeUser_old = latLng.longitude;
            MapsModel mapsModel_old = new MapsModel(addressOLD, latitudeUser_old, longitudeUser_old);

            AlertDialog.Builder builder_old = new AlertDialog.Builder(this);
            builder_old.setCancelable(true);
            builder_old.setTitle("Emin Misiniz?");
            builder_old.setMessage(mapsModel_old.getAddress());
            builder_old.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    mapUpdate(ID, mapsModel_old.getAddress(), String.valueOf(mapsModel_old.getLatitude()), String.valueOf(mapsModel_old.getLongitude()));
                    Toast.makeText(Maps.this, "Başarıyla Güncellendi..", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                    finish();
                }
            });
            builder_old.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Toast.makeText(Maps.this, "İptal Edildi..", Toast.LENGTH_SHORT).show();
                }
            });
            builder_old.show();

        }
        // ***********************  Güncellemek isterse ********************************


    }

    public void dataSave(MapsModel mapsModel) {

        try {
            database = Maps.this.openOrCreateDatabase("Location", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS gps(id VARCHAR, fileid VARCHAR, adres VARCHAR,latitude VARCHAR,longitude VARCHAR)");

            String sorgu = "INSERT INTO gps(id,fileid,adres,latitude,longitude) VALUES(?,?,?,?,?)";
            sqLiteStatement = database.compileStatement(sorgu);

            UUID uuid = UUID.randomUUID();
            String mapsId = uuid.toString();

            sqLiteStatement.bindString(1, mapsId);
            sqLiteStatement.bindString(2, dosyaId);
            sqLiteStatement.bindString(3, mapsModel.getAddress());
            sqLiteStatement.bindString(4, String.valueOf(mapsModel.getLatitude()));
            sqLiteStatement.bindString(5, String.valueOf(mapsModel.getLongitude()));

            sqLiteStatement.execute();
            Toast.makeText(this, "Kaydedildi...", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Veri tababında hata var veya Adres Ekle kısmında adresi ekleyin", Toast.LENGTH_SHORT).show();
        }

    }

    public void mapUpdate(String id, String address, String oldLatitude, String oldLongitude) {

        database = Maps.this.openOrCreateDatabase("Location", MODE_PRIVATE, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("adres", address);
        contentValues.put("latitude", oldLatitude);
        contentValues.put("longitude", oldLongitude);

        database.update("gps", contentValues, "id = ?", new String[]{id});

    }

}