using System;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.Collections;
using Random = UnityEngine.Random;

public class oyunkontrol : MonoBehaviour
{
    public static oyunkontrol instance;
    
    Text puantxt;
    public Text Gerisayim;
    Text hedeftxt;
    public Text highScore;

    float zaman = 0;
    public float sayac = 0;
    public float gerisayimzaman = 0;
   

    public int spawnatilanobje;
    public int ToplanilanBayrak = 0;
    int hedefsayisi;
    public int topHareketdegeri;

    bool solbutonbool = true;
    public bool oyunbitti = false;
    public bool arttirma = false;
    public bool oyunBasladi = false;
    public bool isGamePause = false;


    
    public GameObject durdurmamenü;
    public GameObject Anamenü;
    public GameObject top;
    public GameObject[] aktifEdilecekobjeler;
    public Transform topTransform;
    

    topKontrol tophareketleri;
    
    
    AudioSource ses;
    reklamscript reklam;
    
    
    
    
    
    
    RectTransform solbtn;
    
    void Start()
    {
        
        reklam = GameObject.FindGameObjectWithTag("reklam").GetComponent<reklamscript>();
        hedeftxt = GameObject.FindGameObjectWithTag("hedeftext").GetComponent<Text>();
        Gerisayim = GameObject.FindGameObjectWithTag("Gerisayim").GetComponent<Text>();
        solbtn = GameObject.FindGameObjectWithTag("solbuton").GetComponent<RectTransform>();
        puantxt = GameObject.FindGameObjectWithTag("Player").GetComponent<Text>();
        puantxt.text = "0";
        hedefsayisi = Random.Range(-5, 5);
        hedeftxt.text = "REACH   " + hedefsayisi + " POINT";
        ses = GetComponent<AudioSource>();
        
    }
  


    private void Awake()
    {
        instance = this;
        
    }
    
  
    void FixedUpdate()
    {
      
        Gerisayim.text = "" + ToplanilanBayrak;
        
        
        if (solbutonbool&&oyunBasladi)
        {
            zaman += Time.deltaTime;
            if (zaman >= 5)
            {
                solbutonbool = false;
                solbtn.anchoredPosition = new Vector2(-451.8f, 155.8f);
                zaman = 0;
            }

        }
        

    }
    public void OyunBitti()
    {
        oyunbitti = true;
        oyunBasladi = false;
        ses.Stop();
        PlayGamesScript.AddScoreToLeaderBoard(GPGSIds.leaderboard_scoreboard, ToplanilanBayrak);
        
        

        for (int i = 0; i < aktifEdilecekobjeler.Length; i++)
        {
            aktifEdilecekobjeler[i].gameObject.SetActive(false);
        }


        if (ToplanilanBayrak >= PlayerPrefs.GetInt("ToplanilanBayrak")) 
        {
            PlayerPrefs.SetInt("ToplanilanBayrak", ToplanilanBayrak);
        }
        

        
        puantxt.text = "Last Score: " + ToplanilanBayrak;
        highScore.text = "High Score:" + PlayerPrefs.GetInt("ToplanilanBayrak");




        StartCoroutine(animasyonbekleme());
        

    }
    IEnumerator animasyonbekleme()
    {
        yield return new WaitForSeconds(3);
        
        solbtn.anchoredPosition = new Vector2(-498.5f, 163.4f);
        Anamenü.SetActive(true);
        reklam.ReklamGoster();
        Debug.Log("Çalıştı");


    }

    public void OyunDurdur()
    {
        Time.timeScale = 0;
        durdurmamenü.gameObject.SetActive(true);
        ses.Pause();

        
    }
    public void OyunaDevamEt()
    {
        Time.timeScale = 1;
        durdurmamenü.gameObject.SetActive(false);
        ses.Play();
        
    }
    public void TekrarBaslat()
    {

        OyunBitti();
        System.Threading.Thread.Sleep(300);
        OyunBasladi();

    }
    public void returnToMainMenu()
    {
        for (int i = 0; i < aktifEdilecekobjeler.Length; i++)
        {
            aktifEdilecekobjeler[i].gameObject.SetActive(false);
        }
        ses.Stop();
        oyunBasladi = false;
        oyunbitti = true;
        durdurmamenü.gameObject.SetActive(false);
        Anamenü.gameObject.SetActive(true);
        isGamePause = true;
        
        
    }
   
    public void Solbuton()
    {
            solbtn.anchoredPosition = new Vector2(-327.9f, 155.8f);

            solbutonbool = true;
        
    }
    public void AsamaKontrol()
    {
        if (Convert.ToInt32(puantxt.text)==hedefsayisi)
        {
            
            ToplanilanBayrak++;
            HedefBelirleme(ToplanilanBayrak);
            Solbuton();

        }
        else
        {
            OyunBitti();
        }
    }
    void HedefBelirleme(int bayraksayisi)
    {
        if (bayraksayisi%2==0 && bayraksayisi!=0)
        {
            hedefsayisi = Random.Range(-5 * (ToplanilanBayrak / 2), 5 * (ToplanilanBayrak / 2));
            hedeftxt.text = "REACH   " + hedefsayisi +"   POINT";
        }
        else if(bayraksayisi%2==1 && bayraksayisi!=0)
        {
            hedefsayisi = Random.Range(-5 * ((ToplanilanBayrak / 1) / 2), 5 * (ToplanilanBayrak / 1));
            hedeftxt.text = "REACH   " + hedefsayisi + "   POINT"; 
        }
        else
        {
            hedefsayisi = Random.Range(-5, 5);
            hedeftxt.text = "REACH   " + hedefsayisi + "   POINT"; 
            while(hedefsayisi==0)
            {
                hedefsayisi = Random.Range(-5, 5);
                hedeftxt.text = "REACH   " + hedefsayisi + "   POINT";
            }
        }
        
        
    }
    public void OyunBasladi()
    {
        isGamePause = false;
        reklam.ReklamGoster();
        top.GetComponent<CircleCollider2D>().isTrigger = false;
        ToplanilanBayrak = 0;
        HedefBelirleme(ToplanilanBayrak);
        oyunBasladi = true;
        solbtn.anchoredPosition = new Vector2(-327.9f, 155.8f);
        Gerisayim.text = "" + 0;
        oyunbitti = false;
        puantxt.text = "" + 0;
        Instantiate(top, new Vector2(-0.21f, 5.41f), Quaternion.identity);
        Anamenü.gameObject.SetActive(false);
        ses.Play();

        for (int i = 0; i < aktifEdilecekobjeler.Length; i++)
        {
            aktifEdilecekobjeler[i].gameObject.SetActive(true);
        }
        
    }
    public void buttonKontrol(int x)
    {
        tophareketleri = GameObject.FindGameObjectWithTag("top").GetComponent<topKontrol>();


            if (x == 1)
            {
            tophareketleri.TopHareketi(1);
            }
            else if(x==2)
            {
            tophareketleri.TopHareketi(2);
            }
            else
            {
            
            }


    }
    public void KayıtSil()
    {
        PlayerPrefs.DeleteKey("ToplanilanBayrak");
    }
   













}
