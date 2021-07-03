using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerPositionController : MonoBehaviour
{
    //Karekterin transformu alındı
    [SerializeField]
    Transform playerTransform;

    //Karekterin pozisyonu kayıt etmek için tanımlandı
    private Vector3 playerStartPos;

    void Start()
    {
        //Karekterin başlangıç pozisyonu alınıp değişkene atıldı
        playerStartPos = playerTransform.position;
    }

    //Karekterin pozisyona en başa alınan metod
    public void setPlayerPos()
    {
        playerTransform.position = playerStartPos;
    }
}
