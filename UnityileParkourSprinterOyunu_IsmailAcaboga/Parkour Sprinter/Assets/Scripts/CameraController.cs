using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour
{
    //Kamera döndürme sensivity ayarı
    private float mouseSensitivity = 100f;
    //Sağa sola döndürme için değişken
    private float xRotation = 0f;
    //Bilgisayar veya mobil kontrolü için mobil için false bilgisayar için true
    private bool pcControlState = false;

    [SerializeField]
    Transform playerBody;

    //Mobil veya pc kontrolün yapıldığı yer
    private void FixedUpdate()
    {
        if (pcControlState)
            pcControl();
        else
            mobileControl();
    }

    //Pc kontrol
    private void pcControl()
    {
        controlSettings();
    }

    //Mobil kontrol
    private void mobileControl()
    {
        if (Input.touchCount > 0 && Input.GetTouch(0).phase == TouchPhase.Moved)
        {
            controlSettings();
        }
    }

    //Mobil için kamera kontrolü. Mouse pozisyonuna göre camerayı ve karekteri döndürür
    private void controlSettings()
    {
        float mouseX = Input.GetAxis("Mouse X") * mouseSensitivity * Time.deltaTime;
        float mouseY = Input.GetAxis("Mouse Y") * mouseSensitivity * Time.deltaTime;

        xRotation -= mouseY;
        xRotation = Mathf.Clamp(xRotation, -55f, 55f);

        transform.localRotation = Quaternion.Euler(xRotation, 0f, 0f);
        playerBody.Rotate(Vector3.up * mouseX);
    }
}
