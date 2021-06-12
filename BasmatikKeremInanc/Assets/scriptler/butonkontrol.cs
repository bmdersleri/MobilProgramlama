using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class butonkontrol : MonoBehaviour
{

    public RectTransform mavi, kirmizi;
    public AudioSource mavises, kirmizises;


 
    public void mavibuton()
    {
        mavi.anchoredPosition = new Vector2(mavi.anchoredPosition.x+70,0);
        kirmizi.anchoredPosition = new Vector2(kirmizi.anchoredPosition.x + 70, 0);
        mavises.Play();       
    }
    public void kirmizibuton()
    {
        mavi.anchoredPosition = new Vector2(mavi.anchoredPosition.x - 70, 0);
        kirmizi.anchoredPosition = new Vector2(kirmizi.anchoredPosition.x - 70, 0);
        kirmizises.Play();
    }
}
