using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ChestController : MonoBehaviour
{
    //Karekter için yazılan scripte erişme
    private PlayerController playerCon;
    //Chestten uzaklaşınca basılma iptal olsun diye tanımlandı
    private float chestTime = 0;
    //Chest açıldı mı durumu
    public bool chestOpenState = false;

    //Her chest için anahtar ve kapı
    [SerializeField]
    GameObject key, door;

    void Start()
    {
        //Karekter scripti initialize edildi
        playerCon = GameObject.FindGameObjectWithTag("Player").GetComponent<PlayerController>();
    }

    private void FixedUpdate()
    {
        if (chestTime < 1f)//Sandıktan uzaklaşınca 1 saniye kadar sayar ve işlem yapar
        {
            chestTime += 0.1f;

            if (chestTime >= 1f)
                playerCon.unSetChest();
        }
    }

    //Karekter sandığa yaklaştı mı (çarpışma)
    private void OnTriggerStay(Collider other)
    {
        if (other.tag == "Player" && !chestOpenState)
        {
            playerCon.setChest(gameObject, key, door);
            chestTime = 0;
        }
    }
}
