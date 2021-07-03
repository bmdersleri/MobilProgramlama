using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DoorController : MonoBehaviour
{
    //Karekter için yazılan scripte erişme
    private PlayerController playerCon;
    //Kapıdan uzaklaşınca basılma iptal olsun diye tanımlandı
    private float doorTime = 0;

    void Start()
    {
        //Karekter scripti initialize edildi
        playerCon = GameObject.FindGameObjectWithTag("Player")
            .GetComponent<PlayerController>();
    }

    void FixedUpdate()
    {
        if (doorTime < 1f)//Kapıdan uzaklaşınca 1 saniye kadar sayar ve işlem yapar
        {
            doorTime += 0.1f;

            if (doorTime >= 1f)
                playerCon.unSetDoor();
        }
    }

    //Karekter kapıya yaklaştı mı (çarpışma)
    private void OnTriggerStay(Collider other)
    {
        if (other.tag == "Player")
        {
            playerCon.setDoor();
            doorTime = 0;
        }
    }
}
