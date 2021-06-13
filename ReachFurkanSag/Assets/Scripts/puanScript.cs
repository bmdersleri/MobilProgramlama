using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class puanScript : MonoBehaviour
{
    
    Text puantxt;
    int puan;
    topKontrol top;
    
   
    
   
    void Start()
    {
        top = GameObject.FindGameObjectWithTag("top").GetComponent<topKontrol>();
        puantxt = GameObject.FindGameObjectWithTag("Player").GetComponent<Text>();

        puan = Convert.ToInt32(this.tag);
        
        

    }
   


    void Update()
    {
        if (oyunkontrol.instance.oyunbitti)
        {
            Destroy(gameObject);
        }
        
       
    }
    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.tag=="top")
        {
            puantxt.text = ""+(Convert.ToInt32(puantxt.text) + puan);
            Destroy(gameObject);
            top.voiceCall();
            
        }
        if (other.tag=="testere")
        {
            Destroy(gameObject);
        }
      
        if (other.gameObject.tag=="Finish")
        {
            Destroy(gameObject);
        }
        
       
    }
    private void OnTriggerExit2D(Collider2D other)
    {
        if (other.gameObject.tag == "yik")
        {
            Destroy(gameObject);
        }
    }
}
