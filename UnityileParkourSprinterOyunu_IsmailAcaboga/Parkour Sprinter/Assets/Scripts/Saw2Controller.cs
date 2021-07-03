using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Saw2Controller : MonoBehaviour
{
    //Karekteri en başa almak için tanımlanan script
    private PlayerPositionController playerPos;

    void Start()
    {
        //Script initialize edildi
        playerPos = GameObject.FindGameObjectWithTag("PlayerPos")
            .GetComponent<PlayerPositionController>();
    }

    //Yukardan düşen testere player ile temas etti mi en başa ışınlanır
    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Player")
            playerPos.setPlayerPos();
    }
}
