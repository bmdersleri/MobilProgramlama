using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KeyController : MonoBehaviour
{
    //Karekter için yazılan scripte erişme
    private PlayerController playerCon;
    //Anahtardan uzaklaşınca basılma iptal olsun diye tanımlandı
    private float keyTime = 0;

    void Start()
    {
        //Karekter scripti initialize edildi
        playerCon = GameObject.FindGameObjectWithTag("Player").GetComponent<PlayerController>();
    }

    void FixedUpdate()
    {
        if (keyTime < 1f)//Anahtardan uzaklaşınca 1 saniye kadar sayar ve işlem yapar
        {
            keyTime += 0.1f;

            if (keyTime >= 1f)
                playerCon.unSetKey();
        }
    }

    //Karekter anahtara yaklaştı mı (çarpışma)
    private void OnTriggerStay(Collider other)
    {
        if (other.tag == "Player")
        {
            playerCon.setKey(gameObject);
            keyTime = 0;
        }
    }
}
