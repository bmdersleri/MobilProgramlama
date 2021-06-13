using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HizArttirma : MonoBehaviour
{
    
    Rigidbody2D fizik;
    public float hiz = 0;
    topKontrol Top;
    Transform trns;
    
    void Start()
    {
        trns = GetComponent<Transform>();
        fizik=GetComponent<Rigidbody2D>();

        Top = GameObject.FindGameObjectWithTag("top").GetComponent<topKontrol>();
        
    }

    void FixedUpdate()
    {

        
            hiz = Top.nesnehiz;
            fizik.velocity = new Vector3(hiz, 0, 0);
            fizik.AddForce(new Vector3(hiz, 0, 0));
        
        
        
    }
   
    
    
}
