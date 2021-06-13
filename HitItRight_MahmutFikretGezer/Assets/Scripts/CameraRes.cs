using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Assertions.Must;

public class CameraRes : MonoBehaviour
{
    [SerializeField]
    private SpriteRenderer bgBlack;

    void Start()
    {
        float orthaSize = bgBlack.bounds.size.x * Screen.height / Screen.width * 0.5f;
        Camera.main.orthographicSize = orthaSize;
    }
}
