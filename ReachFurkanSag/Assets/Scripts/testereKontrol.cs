using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class testereKontrol : MonoBehaviour
{

     
     float rotateHizi = 0.5f;

    Transform trns;

    void Start()
    {
        Time.timeScale = 1;
        
        trns = GetComponent<Transform>();
        

    }
    
    void Update()
    {
        if (oyunkontrol.instance.oyunbitti)
        {
            Destroy(gameObject);
        }
        trns.transform.Rotate(new Vector3(0, 0, Time.timeScale==1? rotateHizi:0));
       
        
        if (Time.timeScale == 1)
        {
           
            rotateHizi = 0.5f;
        }

    }
    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.CompareTag("yik"))
        {
            Destroy(gameObject);
        }
        if (other.gameObject.CompareTag("top"))
        {
            
            Destroy(gameObject);
            oyunkontrol.instance.OyunBitti();

        }
    }
   
   
}
