using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class yanObje : MonoBehaviour
{
   
    void Update()
    {
        if (oyunkontrol.instance.oyunbitti)
        {
            Destroy(gameObject);
        }
    }
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (this.gameObject.tag=="Pair" && collision.gameObject.tag=="Finish")
        {
            Destroy(gameObject);
        }
    }
}
