using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Saw1Controller : MonoBehaviour
{
    //Karekteri en başa almak için tanımlanan script
    private PlayerPositionController playerPos;

    void Start()
    {
        //Script initialize edildi
        playerPos = GameObject.FindGameObjectWithTag("PlayerPos")
            .GetComponent<PlayerPositionController>();
    }

    //Testere kendi çevresinde dönüyor bu kod ile
    void FixedUpdate()
    {
        transform.Rotate(0, 0, 5);
    }

    //Testere player ile temas etti mi en başa ışınlanır
    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Player")
            playerPos.setPlayerPos();
    }
}
