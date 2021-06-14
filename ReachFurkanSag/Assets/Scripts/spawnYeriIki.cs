using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class spawnYeriIki : MonoBehaviour
{
    public GameObject saw;
    public GameObject pair;
    public GameObject FinishBayrak;
    public GameObject[] hizObjeleri;
    
    Transform trns;
    float spawnlamazamaniiki = 0;
    float spawnlamazamani = 0;
    bool pairbool = false;
    topKontrol top;

    void Start()
    {
        trns = GetComponent<Transform>();
        top = GameObject.FindGameObjectWithTag("top").GetComponent<topKontrol>();
       
        


    }

    // Update is called once per frame
    void FixedUpdate()
    {
        spawnlamazamani += 0.1f*Time.deltaTime;
        spawnlamazamaniiki += 0.1f*Time.deltaTime;
        
        if (spawnlamazamani>1f && !oyunkontrol.instance.oyunbitti)
        {
            finishInstantiate();
            HizObjeleri(top.spawnhiz);
            spawnlamazamani = 0;
            pairbool = !pairbool;

        }
        if (spawnlamazamaniiki>=0.3f && !oyunkontrol.instance.oyunbitti)
        {
            sawInstantiate();
            spawnlamazamaniiki = 0;
        }
        


    }
    void finishInstantiate()
    {
        trns.transform.position = new Vector2(trns.transform.position.x, Random.Range(-2.48f, 5.96f));
        Instantiate(FinishBayrak, trns.transform.position, Quaternion.identity);
        if (pairbool)
        {
            trns.transform.position = new Vector2(trns.transform.position.x, Random.Range(-2.48f, 5.96f));
            Instantiate(pair, trns.transform.position, Quaternion.identity);
            
        }
    }
   
    void sawInstantiate()
    {
        trns.transform.position = new Vector2(trns.transform.position.x, Random.Range(-2.48f, 5.96f));
        Instantiate(saw, trns.transform.position, Quaternion.identity);
    }
    void HizObjeleri(float hiz)
    {
        if (hiz >= 1.5f)
        {
            trns.transform.position = new Vector2(trns.transform.position.x, Random.Range(-2.48f, 5.96f));
            Instantiate(hizObjeleri[0], trns.transform.position, Quaternion.identity);
        }
        else
        {
            trns.transform.position = new Vector2(trns.transform.position.x, Random.Range(-2.48f, 5.96f));
            Instantiate(hizObjeleri[Random.Range(0,2)], trns.transform.position, Quaternion.identity);
        }
    }
   
   
    
}
