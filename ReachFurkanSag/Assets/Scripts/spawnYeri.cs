using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class spawnYeri : MonoBehaviour
{
    public GameObject[] objeler;

    float spawnlamazamani = 0;
    
    Transform trns;
    topKontrol top;


    void Start()
    {
        top = GameObject.FindGameObjectWithTag("top").GetComponent<topKontrol>();
        trns = GetComponent<Transform>();
      
    }
    
    void Update()
    {

        spawnlamazamani += 1f*Time.deltaTime;

        
        
        if (spawnlamazamani>=top.spawnhiz && !oyunkontrol.instance.oyunbitti)
        {
            OgeSpawn();
            spawnlamazamani = 0;
        }

        
        
        
       

    }
    void OgeSpawn()
    {

        
        trns.transform.position = new Vector2(trns.transform.position.x, Random.Range(-2.48f, 5.96f));
        Instantiate(objeler[Random.Range(0, 10)], trns.transform.position, Quaternion.identity);
  
    }
    
  
    
}
