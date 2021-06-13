using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using GoogleMobileAds.Api;

public class reklamscript : MonoBehaviour
{
    private InterstitialAd interstitial;

    private void Start()
    {

        MobileAds.Initialize(initStatus => { });
        //2.Aşama--------------
#if UNITY_ANDROID
        //string adUnitId = "ca-app-pub-4503862329401917/5916389434"; Gerçek İd
        string adUnitId = "ca-app-pub-3940256099942544/6300978111";
#elif UNITY_IPHONE
            string adUnitId = "ca-app-pub-3940256099942544/4411468910";
#else
            string adUnitId = "unexpected_platform";
#endif


        this.interstitial = new InterstitialAd(adUnitId);
    }

    void FixedUpdate()
    {
        
            
            //3.Aşama------------------------------------------------
            AdRequest request = new AdRequest.Builder().AddTestDevice(AdRequest.TestDeviceSimulator).AddTestDevice("2077ef9a63d2b398840261c8221a0c9b").Build();

            this.interstitial.LoadAd(request);
        
        
        
        

    }
    
   

     public void ReklamGoster()
    {
        if (this.interstitial.IsLoaded())
        {
            this.interstitial.Show();
            Debug.Log("Reklam Yüklendi");

        }
        else
        {
            Debug.Log("Reklam yüklenmedi");
        }
    }
}
