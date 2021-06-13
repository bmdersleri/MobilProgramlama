using System.Collections;
using UnityEngine;


public class topKontrol : MonoBehaviour
{
    
    Rigidbody2D fizik;

    
    oyunkontrol oyunkontrol1;
    public bool carpma = false;
    bool dondurme = false;
    

    public float nesnehiz = -1f;
    public float spawnhiz = 1f;
    AudioSource ses;
    public AudioClip[]  clipFiles;



    private void Awake()
    {
        ses = GetComponent<AudioSource>();
    }

    void Start()
    {
        
        
        fizik = GetComponent<Rigidbody2D>();
       
        oyunkontrol1 = GameObject.FindGameObjectWithTag("GameController").GetComponent<oyunkontrol>();

    }
    private void Update()
    {
        if (oyunkontrol.instance.oyunbitti)
        {
            fizik.velocity = new Vector2(0, fizik.velocity.y);
            StartCoroutine(TopDestroy());
            



        }
        if (oyunkontrol.instance.isGamePause)
        {
            Destroy(this.gameObject);
        }
       
    }
   


    private void OnTriggerEnter2D(Collider2D col)
    {

        if (col.gameObject.tag=="Pair")
        {
            dondurme = !dondurme;
            Destroy(col.gameObject);
            ses.clip = clipFiles[2];
            ses.Play();

        }
        
        if (col.gameObject.tag=="Finish")
        {

            oyunkontrol1.AsamaKontrol();
            if (oyunkontrol.instance.oyunbitti)
            {
                ses.clip = clipFiles[3];
                ses.Play();
            }
            else
            {
                ses.clip = clipFiles[0];
                ses.Play();
                nesnehiz -= 1f;
                spawnhiz -= 0.1f;
            }
           
            

        }
        
        if (col.gameObject.tag=="su")
        {
            Debug.Log("su");
            
            oyunkontrol.instance.OyunBitti();
            ses.clip = clipFiles[3];
            ses.Play();
        }
        if (col.gameObject.tag=="down")
        {

            nesnehiz -= 0.5f;
            spawnhiz -= 0.05f;
            Destroy(col.gameObject);
            ses.clip = clipFiles[2];
            ses.Play();


        }
        if (col.gameObject.tag == "up")
        {
            nesnehiz += 0.5f;
            spawnhiz += 0.05f;
            Destroy(col.gameObject);
            ses.clip = clipFiles[2];
            ses.Play();



        }
        if (col.gameObject.tag=="yik")
        {
            Debug.Log("yikildi");
            fizik.velocity = new Vector2(4, 1);
            fizik.AddForce(new Vector2(50, 50));
        }
        if (col.gameObject.tag=="yer")
        {
            fizik.velocity = new Vector2(0, 10);
            fizik.AddForce(new Vector2(50, 50));
        }
        if (col.gameObject.tag=="testere")
        {
            ses.clip = clipFiles[3];
            ses.Play();
        }

    }

    public void TopHareketi(int deger)
    {
        if (!dondurme)
        {
            if (deger == 1)
            {
                
                //int x = Random.Range(-2, 0);
                fizik.velocity = new Vector2(-2, 0);
                fizik.AddForce(new Vector2(-10, 150));

            }
            if (deger == 2)
            {
                
                //int y = Random.Range(0, 2);
                fizik.velocity = new Vector2(2, 0);
                fizik.AddForce(new Vector2(10, 150));

            }
        }
        else
        {
            if (deger == 2)
            {
                //int x = Random.Range(-2, 0);
                fizik.velocity = new Vector2(-2, 0);
                fizik.AddForce(new Vector2(-20, 200));

            }
            if (deger == 1)
            {
                //int y = Random.Range(0, 2);
                fizik.velocity = new Vector2(2, 0);
                fizik.AddForce(new Vector2(20, 200));

            }
        }

    }

    IEnumerator TopDestroy()
    {
        yield return new WaitForSeconds(3);

        Destroy(this.gameObject);
        Debug.Log("Çalıştı");


    }
    public void voiceCall()
    {
        
            ses.clip = clipFiles[1];
            ses.Play();
        
    }


}
