using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpinningObstacle : MonoBehaviour
{
    //Karekteri en başa almak için tanımlanan script
    private PlayerPositionController playerPos;

    private void Start()
    {
        //Script initialize edildi
        playerPos = GameObject.FindGameObjectWithTag("PlayerPos")
            .GetComponent<PlayerPositionController>();
    }

    //Çubuk kendi çevresinde dönüyor bu kod ile
    void FixedUpdate()
    {
        transform.Rotate(0, 5, 0);
    }

    //Çubuk player ile temas etti mi en başa ışınlanır
    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Player")
            playerPos.setPlayerPos();
    }
}
